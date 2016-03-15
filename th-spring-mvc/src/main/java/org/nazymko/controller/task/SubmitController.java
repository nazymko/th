package org.nazymko.controller.task;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Created by nazymko.patronus@gmail.com. on 15.01.2016.
 */
@Controller
@RequestMapping(value = "task")
@Log4j2
public class SubmitController {
    @Autowired
    THLParserRunner parser;
    @Autowired
    SiteDao siteDao;

    @RequestMapping(value = "submit/{site}", method = RequestMethod.GET)
    public String submitTask(Model model, @PathVariable("site") Integer id) {

//        throw new NotImplementedException();
        SiteRecord site = siteDao.get(id).get();

        TTaskRecord tTaskRecord = parser.getTaskFac().nextRecord(id);

        parser.schedule(site.getUrl(), "front_page", -1, tTaskRecord.getId());

        model.addAttribute("site", site);


        log.debug("SCHEDULED: {}:{}", site.getName(), site.getUrl());

        return "/submit/info";
    }

    @RequestMapping(value = "submit}", method = RequestMethod.POST)
    public String submitPost(@RequestParam HashMap<String, String> params) {

        log.debug("params = {}", params);


        return "redirect:/task/submit";
    }
}
