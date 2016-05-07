package org.nazymko.controller.rule;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.rule.ParsingRule;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created by nazymko.patronus@gmail.com. on 17.01.2016.
 */
@Log4j2
@Controller
@RequestMapping("rule")
public class AddController {
    @Resource
    RuleDao ruleDao;
    @Resource
    Gson gson;
    @Resource
    PageDao pageDao;

    @Resource
    RuleResolver ruleResolver;

    @RequestMapping(value = "addnew", method = RequestMethod.POST)
    public String addPost(@RequestParam HashMap<String, String> params, Model model) throws MalformedURLException, URISyntaxException {
        log.debug("params = {}", params);
        ParsingRule rule;
        try {
            rule = gson.fromJson(params.get("rule"), ParsingRule.class);
        } catch (Exception ex) {
            log.error(ex);
            model.addAttribute("ex", ex);
            return "error";
        }

        model.addAttribute("ex", params);
        ruleDao.save(rule);

        ruleResolver.init();

        return "redirect:/rule/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addGet(Model model) {
        return "rule/add";
    }

}
