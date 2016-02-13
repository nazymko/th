package org.nazymko.controller.task;

import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 15.01.2016.
 */
@Controller
@RequestMapping(value = "task")
public class THParser {

    @Resource
    THLParserRunner parser;
    @Resource
    PageDao pageDao;

    @RequestMapping("activity")
    public String info(Model model, @RequestParam HashMap<String, String> params) {

        List<String> queUrls = parser.getStatusMessage();
        Integer size = Integer.valueOf(coalesce(params.get("size"), 15).toString());
        Integer page = Integer.valueOf(coalesce(params.get("page"), 0).toString());
        List<Page> list = pageDao.getList(size, page);
        model.addAttribute("tasks", queUrls);
        model.addAttribute("last", list);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "task/activity";
    }


    public static <T> T coalesce(T... items) {
        for (T i : items) if (i != null) return i;
        return null;
    }

}
