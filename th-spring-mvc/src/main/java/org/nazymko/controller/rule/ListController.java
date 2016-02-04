package org.nazymko.controller.rule;

import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by user on 17.01.2016.
 */
@Controller
@RequestMapping("rule")
public class ListController  {


    @Qualifier("ruleDao")
    @Autowired
    private RuleDao ruleDao;

    @RequestMapping("list")
    public String list(Model model) {
        List<JsonRule> all = ruleDao.getAll();
        model.addAttribute("rules", all);
        return "rule/list";
    }
}
