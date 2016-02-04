package org.nazymko.controller.rule;

import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nazymko
 */
@Controller("rule")
public class ManageController {
    @Resource
    RuleDao ruleDao;

    @RequestMapping("manage")
    public String managePage(Model model) {

        List<JsonRule> all = ruleDao.getAll();
        model.addAttribute("rules", all);
        return "rule/manage";
    }
}
