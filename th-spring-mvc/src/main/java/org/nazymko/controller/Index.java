package org.nazymko.controller;

import org.nazymko.thehomeland.parser.THLParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user
 */
@Controller()
@RequestMapping("/")
public class Index {
    @Resource
    THLParser parser;

    @RequestMapping
    public String statusPage(Model model) {

        List<String> queUrls = parser.getStatusMessage();
        model.addAttribute("message", queUrls.get(0));


        boolean active = parser.isActive();
        model.addAttribute("isActive", active);


        return "root/status";
    }

}