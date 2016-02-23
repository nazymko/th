package org.nazymko.controller;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Controller
public class RootController {
    @Resource
    THLParserRunner parser;
    @Resource
    TaskDao taskDao;

    @RequestMapping("/")
    public String statusPage(Model model) {

        List<String> queUrls = parser.getStatusMessage();
        model.addAttribute("message", queUrls.get(0));


        boolean active = parser.getConfig().isActive();
        model.addAttribute("isActive", active);

        Result<TTaskRecord> started = taskDao.getStarted();
        model.addAttribute("started", started);

        Result<TTaskRecord> finished = taskDao.getFinished(10);
        model.addAttribute("finished", finished);
        return "root/index";
    }

}