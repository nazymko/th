package org.nazymko.controller.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.log4j.Log4j2;
import org.jooq.Record7;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.pojos.ConnectorConsumer;
import org.nazymko.th.parser.autodao.tables.pojos.ConnectorRules;
import org.nazymko.th.parser.autodao.tables.pojos.ConnectorsSendHeaders;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.dao.SyncConsumerDao;
import org.nazymko.thehomeland.dao.SyncRuleDao;
import org.nazymko.thehomeland.parser.Repository;
import org.nazymko.thehomeland.parser.ThRecordConverter;
import org.nazymko.thehomeland.parser.db.dao.*;
import org.nazymko.thehomeland.scheduler.HourlyScheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.nazymko.controller.utils.MessagingUtils.UI.warn;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Log4j2
@Controller
@RequestMapping("connector")
public class SyncController {
    @Resource
    SyncConsumerDao syncConsumerDao;
    @Resource
    ConnectorConsumerDao connectorConsumerDao;
    @Resource
    ConnectorSendHeaderDao connectorSendHeaderDao;
    @Resource
    ConnectorRuleDao connectorRuleDao;
    @Resource
    @Qualifier("connectorSyncPageLogDao")
    ConnectorSyncPageLogDao connectorSyncPageLogDao;
    @Resource
    Repository repository;
    @Resource
    ObjectMapper mapper;
    @Resource
    private HourlyScheduler scheduler;
    @Resource
    private SiteDao siteDao;
    @Resource
    private ThRecordConverter converter;
    @Resource
    private SyncRuleDao syncRuleDao;

    @RequestMapping(value = "consumers/add", method = RequestMethod.POST)
    public String add(@RequestParam HashMap<String, String> params, Model model) {
        connectorConsumerDao.create(params.get("domain"));
        scheduler.doIt(params.get("domain"));
        return showAll(model);
    }

    @RequestMapping("consumers")
    public String showAll(Model model) {
        List<ConnectorConsumer> all = syncConsumerDao.findAll();
        model.addAttribute("items", all);
        return "consumers/all";
    }

    @RequestMapping(value = "consumers/start", method = RequestMethod.POST)
    public String commandPanelStart(@RequestParam HashMap<String, String> params) {
        scheduler.doIt(params.get("domain"));
        return "consumers/all";
    }

    @RequestMapping(value = "consumers/{consumerId}/headers/view", method = RequestMethod.GET)
    public String showHeaders(@PathVariable("consumerId") Integer consumerId,
                              Model model) {

        List<ConnectorsSendHeaders> heades = connectorSendHeaderDao.fetchByConsumerId(consumerId);
        ConnectorConsumer connectorConsumer = connectorConsumerDao.fetchOneById(consumerId);
        model.addAttribute("headers", heades);
        model.addAttribute("consumer", connectorConsumer);

        return "consumers/headers";
    }

    @RequestMapping(value = "consumers/{consumerId}/mapping/view", method = RequestMethod.GET)
    public String showMapping(@PathVariable("consumerId") Integer consumerId,
                              Model model) {
        List<ConnectorRules> attributeValue = connectorRuleDao.fetchByConsumerId(consumerId);
        HashMap<Integer, ThSiteRecord> sites = fetchSites(attributeValue);

        model.addAttribute("mapping", attributeValue);
        model.addAttribute("consumerId", consumerId);
        model.addAttribute("sites", sites);
        return "consumers/mapping";
    }

    private HashMap<Integer, ThSiteRecord> fetchSites(List<ConnectorRules> attributeValue) {
        Integer[] sites = new Integer[attributeValue.size()];

        for (int i = 0; i < attributeValue.size(); i++) {
            sites[i] = attributeValue.get(i).getSiteId();
        }

        Result<ThSiteRecord> byId = siteDao.getById(sites);
        HashMap<Integer, ThSiteRecord> recors = new HashMap<>();
        for (ThSiteRecord record : byId) {
            recors.put(record.getId(), record);
        }

        return recors;
    }

    @RequestMapping(value = "consumers/{consumerId}/mapping/test/page/{pageId}", method = RequestMethod.GET)
    public String testMapping(@RequestParam HashMap<String, String> params,
                              @PathVariable("consumerId") Integer consumerId,
                              Model model, @PathVariable("pageId") Integer pageId) {
        return "consumers/mapping";
    }

    @RequestMapping(value = "consumers/add", method = RequestMethod.GET)
    public String add() {
        return "consumers/add";
    }

    @RequestMapping(value = "consumers/{consumerId}/headers/add", method = RequestMethod.GET)
    public String addHeaderPage(@PathVariable("consumerId") Integer consumerId, Model model) {
        ConnectorConsumer connectorConsumer = connectorConsumerDao.fetchOneById(consumerId);
        model.addAttribute("consumer", connectorConsumer);

        return "consumers/header/add";
    }

    @RequestMapping(value = "consumers/{consumerId}/headers/add", method = RequestMethod.POST)
    public String addHeaderPost(@PathVariable("consumerId") Integer consumerId, Model model, @RequestParam HashMap<String, String> params) {
        ConnectorConsumer connectorConsumer = connectorConsumerDao.fetchOneById(consumerId);
        if (connectorConsumer != null) {
            connectorSendHeaderDao.addHeader(consumerId, params.get("header"), params.get("value"));
        } else {
            warn(model, "Cant add header: <br>Can't find a consumer");
        }


        return showHeaders(consumerId, model);
    }

    @RequestMapping(value = "consumers/headers/{headerId}/edit", method = RequestMethod.GET)
    public String editHeaderPage(Model model,
                                 @RequestParam HashMap<String, String> params,
                                 @PathVariable("headerId") Integer headerId) {

        ConnectorsSendHeaders header = connectorSendHeaderDao.fetchOneById(headerId);
        if (header == null) {
            warn(model, "Cant find header with id %s", headerId);
        } else {
            model.addAttribute("headers", header);
        }
        return "consumers/header/edit";
    }

    @RequestMapping(value = "consumers/{consumerId}/headers/{headerId}/update", method = RequestMethod.POST)
    public String updateHeader(Model model,
                               @RequestParam HashMap<String, String> params,
                               @PathVariable("headerId") Integer headerId,
                               @PathVariable("consumerId") Integer consumerId) {

        ConnectorsSendHeaders header = connectorSendHeaderDao.fetchOneById(headerId);
        if (header == null) {
            warn(model, "Cant find header with id %s", headerId);
        } else {
            header.setHeader(params.get("header"));
            header.setValue(params.get("value"));
            connectorSendHeaderDao.update(header);
        }
        return showHeaders(consumerId, model);
    }

    @RequestMapping(value = "consumers/{consumerId}/headers/{headerId}/delete", method = RequestMethod.GET)
    public String deleteHeader(Model model,
                               @PathVariable("headerId") Integer headerId,
                               @PathVariable("consumerId") Integer consumerId) {

        connectorSendHeaderDao.deleteById(headerId);
        return showHeaders(consumerId, model);
    }

    @RequestMapping(value = "/consumers/{consumerId}/rule/{ruleId}/edit", method = RequestMethod.GET)
    public String editRule(Model model,
                           @PathVariable("consumerId") Integer consumerId,
                           @PathVariable("ruleId") Integer ruleId) {

        ConnectorRules rules = connectorRuleDao.findById(ruleId);
        if (rules != null) {
            model.addAttribute("rule", rules);
        } else {
            warn(model, "Can't find rule with id %s", ruleId);
        }
        return "consumers/rule/edit";
    }

    @RequestMapping(value = "/consumers/{consumerId}/rule/{ruleId}/update", method = RequestMethod.POST)
    public String updateRule(Model model,
                             @RequestParam HashMap<String, String> params,
                             @PathVariable("consumerId") Integer consumerId,
                             @PathVariable("ruleId") Integer ruleId) {

        ConnectorRules rules = connectorRuleDao.findById(ruleId);
        if (rules != null) {
            rules.setRule(params.get("rule"));
            connectorRuleDao.update(rules);
        } else {
            warn(model, "Can't find rule with id %s", ruleId);
        }
        return showMapping(consumerId, model);
    }

    @RequestMapping(value = "/consumers/{consumerId}/rule/{ruleId}/delete", method = RequestMethod.POST)
    public String deleteRule(Model model,
                             @RequestParam HashMap<String, String> params,
                             @PathVariable("consumerId") Integer consumerId,
                             @PathVariable("ruleId") Integer ruleId) {

        connectorRuleDao.deleteById(ruleId);

        return showMapping(consumerId, model);
    }

    @RequestMapping(value = "/consumers/{consumerId}/mapping/add", method = RequestMethod.GET)
    public String addMappingPage(Model model,
                                 @PathVariable("consumerId") Integer consumerId) {
        ConnectorConsumer consumer = connectorConsumerDao.fetchOneById(consumerId);
        List<ThSiteRecord> sites = siteDao.getAll();

        if (consumer != null) {
            model.addAttribute("consumer", consumer);
            model.addAttribute("sites", sites);
        } else {
            warn(model, "Cant find consumer with id %s", consumerId);
        }

        return "consumers/rule/add";
    }

    @RequestMapping(value = "/consumers/{consumerId}/mapping/add", method = RequestMethod.POST)
    public String addMappingPage(Model model,
                                 @PathVariable("consumerId") Integer consumerId,
                                 @RequestParam HashMap<String, String> params) {
        ConnectorConsumer consumer = connectorConsumerDao.fetchOneById(consumerId);
        if (consumer != null) {
            model.addAttribute("consumer", consumer);


            String rule = params.get("rule");
            String siteId = params.get("siteId");

            log.debug("Adding mapping rule: siteId={},rule=\n{}", siteId, rule);
            connectorRuleDao.add(consumerId, Integer.valueOf(siteId), rule);
        } else {
            warn(model, "Cant find consumer with id %s", consumerId);
        }

        return showAll(model);
    }

    @RequestMapping(value = "consumers/{id}/manual", method = RequestMethod.GET)
    public String manualStart(Model model
            , @PathVariable("id") Integer id
            , @RequestParam(value = "dirty", defaultValue = "false") Boolean dirty
            , @RequestParam(value = "source", defaultValue = "all") String source

    ) {
        ConnectorConsumer connectorConsumer = connectorConsumerDao.fetchOneById(id);
        model.addAttribute("dirty", dirty);
        model.addAttribute("current", id);
        if (connectorConsumer != null) {
            Result<ThPageRecord> latest = repository.dirty(connectorConsumer.getDomain(), dirty);
            model.addAttribute("latest", latest);
        } else {
            warn(model, "Consumern with id %s not found", id);
        }
        return "consumers/manual";
    }

    @RequestMapping(value = "consumers/{id}/history", method = RequestMethod.GET)
    public String history(Model model, @PathVariable("id") Integer id) {

        Result<Record7<Integer, String, Integer, String, String, Timestamp, Integer>> history = connectorSyncPageLogDao.fetchByConsumerIdJoinUrl(id);

        model.addAttribute("history", history);
        return "consumers/history";
    }

    @PostConstruct
    public void postConstruct() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @RequestMapping(value = "consumers/manual/page/{pageId}/preview", method = RequestMethod.GET)
    public String manualStart(Model model,
                              @RequestParam(value = "dirty", defaultValue = "false") Boolean dirty,
                              @PathVariable("pageId") Integer pageId) throws JsonProcessingException {

        converter.init(syncRuleDao.all());

        Optional<Map> convert = converter.convert(pageId);

        if (convert.isPresent()) {
            model.addAttribute("preview", mapper.writeValueAsString(convert.get()));
        } else {
            warn(model, "Could not find page with id = %s", pageId);
        }


        return "consumers/preview";
    }

}
