package org.nazymko.controller.rule;

import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Controller
@RequestMapping("rule")
public class ViewController {
    @Resource
    RuleDao ruleDao;

    @RequestMapping("view/{id}")
    public String viewRule(@PathVariable("id") Integer id, Model model) {


        Optional<JsonRule> byId = ruleDao.getJsonById(id);
        if(byId.isPresent()){
            JsonRule jsonRule = byId.get();
            model.addAttribute("rule",jsonRule);
        }

        return "rule/view";

    }
}
