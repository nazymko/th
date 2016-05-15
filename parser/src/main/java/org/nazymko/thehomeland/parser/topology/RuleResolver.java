package org.nazymko.thehomeland.parser.topology;

import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.nazymko.thehomeland.parser.rule.PageItem;
import org.nazymko.thehomeland.parser.rule.ParsingRule;
import org.nazymko.thehomeland.parser.rule.RuleMeta;
import org.nazymko.thehomeland.parser.utils.UrlSimplifier;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * Created by nazymko.patronus@gmail.com.
 */

@Log4j2
public class RuleResolver implements ProcessorRegister {
    @Resource
    UrlSimplifier simplifier;
    @Resource
    private RuleDao ruleDao;
    private HashMap<String, HashMap<String, PageItem>> catalogue = new HashMap<>();
    private HashMap<String, RuleMeta> meta = new HashMap<>();

    private boolean initialized = false;

    public void setRuleDao(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    @Override
    public Optional<HashMap<String, PageItem>> resolveBySite(String site) {
        lazyInit();
        return Optional.ofNullable(catalogue.get(site));
    }

    private void lazyInit() {
        if (!initialized) {
            init();
        }
    }


    public void init() {

        List<ParsingRule> all = ruleDao.getLatestParsingRules();
        catalogue.clear();

        for (JsonRule jsonRule : all) {
            HashMap<String, PageItem> stringPageItemHashMap = catalogue.get(simplifier.simplify(jsonRule.getUrl()));
            if (stringPageItemHashMap == null) {
                stringPageItemHashMap = new HashMap<>();
                catalogue.put(simplifier.authority(jsonRule.getUrl()), stringPageItemHashMap);
            }

            List<PageItem> page = jsonRule.getPage();
            for (PageItem item : page) {
                stringPageItemHashMap.put(item.getType(), item);
            }
        }

        initialized = true;

    }


    @Override
    public Optional<PageItem> resolveByTypeForSite(String site, String type) {
        lazyInit();
        log.debug("site = {}, type = {}", site, type);
        if (!catalogue.containsKey(site)) {
            log.warn("Not found {} in {}", site, catalogue);
        }
        return Optional.ofNullable(catalogue.get(site).get(type));
    }

    @Override
    public Optional<PageItem> resolveByAttr(String site, String attr) {
        lazyInit();
        PageItem item;
        HashMap<String, PageItem> stringPageItemHashMap = catalogue.get(site);
        if (stringPageItemHashMap.containsKey(attr)) {

            item = stringPageItemHashMap.get(attr);
        } else {
            return Optional.empty();
        }
        return Optional.ofNullable(item);
    }


    @Override
    public boolean register(String site, JsonRule rule) {
        lazyInit();
        try {

            if (!catalogue.containsKey(site)) {
                catalogue.put(site, new LinkedHashMap<>(1));
            }

            HashMap<String, PageItem> pageItemHashMap = catalogue.get(site);
            for (PageItem pageItem : rule.getPage()) {
                pageItemHashMap.put(pageItem.getType(), pageItem);
            }

            RuleMeta ruleMeta = new RuleMeta();
            ruleMeta.setName(rule.getName());
            ruleMeta.setSelector(rule.getSelector());
            ruleMeta.setUrl(rule.getUrl());
            meta.put(site, ruleMeta);

        } catch (Exception ex) {
            System.err.println(String.format("Exception occur, catalogue will be cleaned: %s", ex));

            catalogue.remove(site);

            for (PageItem item : rule.getPage()) {
                meta.remove(item.getType());
            }

            return false;
        }

        return true;
    }

    @Override
    public Optional<Set<String>> availableTypes(String site) {
        lazyInit();

        Optional<ParsingRule> jsonRuleOptional = ruleDao.getById(site);
        if (jsonRuleOptional.isPresent()) {
            HashSet<String> types = getTypes(jsonRuleOptional);

            return Optional.of(types);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Set<String>> availableTypes(Integer site) {
        lazyInit();

        Optional<ParsingRule> jsonRuleOptional = ruleDao.getJsonById(site);
        if (jsonRuleOptional.isPresent()) {
            HashSet<String> types = getTypes(jsonRuleOptional);

            return Optional.of(types);
        }
        return Optional.empty();
    }

    private HashSet<String> getTypes(Optional<ParsingRule> jsonRuleOptional) {
        HashSet<String> types = new HashSet<>();
        JsonRule rule = jsonRuleOptional.get();

        for (PageItem item : rule.getPage()) {
            types.add(item.getType());
        }
        return types;
    }

    public void refresh() {
        init();
    }
}
