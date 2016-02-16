package org.nazymko.controller;

import org.nazymko.thehomeland.parser.THLParserRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nazymko
 */
@Controller()
@RequestMapping("/")
public class Index {
    @Resource
    THLParserRunner parser;

    @RequestMapping
    public String statusPage(Model model) {

        List<String> queUrls = parser.getStatusMessage();
        model.addAttribute("message", queUrls.get(0));


        boolean active = parser.getConfig().isActive();
        model.addAttribute("isActive", active);


        return "root/status";
    }

}