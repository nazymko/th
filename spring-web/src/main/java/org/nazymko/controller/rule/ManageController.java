package org.nazymko.controller.rule;

import org.nazymko.th.parser.autodao.tables.records.ThRuleRecord;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Controller()
@RequestMapping("rule")
public class ManageController {
    @Resource
    RuleDao ruleDao;

    @RequestMapping("manage")
    public String managePage(Model model) {

        List<ThRuleRecord> all = ruleDao.getAllRules();
        model.addAttribute("rules", all);

        return "rule/manage";
    }
}
