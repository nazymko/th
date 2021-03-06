package org.nazymko.controller.task;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com. on 15.01.2016.
 */
@Controller
@RequestMapping(value = "task")
public class THParserController {

    @Resource
    THLParserRunner parser;
    @Resource
    PageDao pageDao;
    @Qualifier("taskDao")
    @Resource
    private TaskDao taskDao;


    /*WTF*/
    /*Ok, looks like this code return first not null value*/
    public static <T> T coalesce(T... items) {
        for (T i : items) if (i != null) return i;
        return null;
    }

    @RequestMapping("activity")
    public String info(Model model, @RequestParam HashMap<String, String> params) {
        Integer size = Integer.valueOf(coalesce(params.get("size"), 15).toString());
        Integer page = Integer.valueOf(coalesce(params.get("page"), 0).toString());
        String type = coalesce(params.get("type"), "all");

        List<String> queUrls = parser.getStatusMessage();
        Integer count = pageDao.countAll();

        List<String> types = pageDao.allTypes();
        types.add("all");//default filter

        Result<TaskRunRecord> active = taskDao.getActive();

        model.addAttribute("tasks", queUrls);
        model.addAttribute("count", count);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("active", active);
        model.addAttribute("type", type);
        model.addAttribute("types", types);
        List<ThPageRecord> list = null;

        if ("all".equals(type)) {
            list = pageDao.getList(size, page);
        } else {
            list = pageDao.getByType(size, page, type);


        }
        model.addAttribute("last", list);

        return "task/activity";
    }

}
