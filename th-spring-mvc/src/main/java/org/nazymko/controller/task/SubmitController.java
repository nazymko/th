package org.nazymko.controller.task;

import lombok.extern.log4j.Log4j2;
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

import java.util.HashMap;

/**
 * Created by user on 15.01.2016.
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


        Site site = siteDao.get(id).get();

        parser.create(site.getName(), site.getUrl());

        model.addAttribute("site", site);


        log.info("SCHEDULED: {}:{}", site.getName(), site.getUrl());

        return "/submit/info";
    }

    @RequestMapping(value = "submit}", method = RequestMethod.POST)
    public String submitPost(@RequestParam HashMap<String, String> params) {

        log.info("params = {}",params);


        return "redirect:/task/submit";
    }
}
