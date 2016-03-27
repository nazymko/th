package org.nazymko.controller.rule;

import org.nazymko.th.parser.autodao.tables.records.ThRuleRecord;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static utils.support.rule.RuleStatus.ACTIVE;
import static utils.support.rule.RuleStatus.DISABLED;

/**
 * Created by nazymko
 */
@Controller
@RequestMapping("rule")
public class ManageDisableActivateController {
    @Resource
    RuleDao ruleDao;

    @RequestMapping("{id}/disable")
    public String disable(@PathVariable() Integer id, Model model) {
        Optional<ThRuleRecord> record = ruleDao.getById(id);

        if (record.isPresent()) {
            record.get().setStatus(DISABLED);
            record.get().store();
        }

        List<ThRuleRecord> all = ruleDao.getAllRules();
        model.addAttribute("rules", all);


        return "rule/manage";
    }

    @RequestMapping("{id}/activate")
    public String activate(@PathVariable() Integer id, Model model) {
        Optional<ThRuleRecord> record = ruleDao.getById(id);

        if (record.isPresent()) {
            record.get().setStatus(ACTIVE);
            record.get().store();
        }

        List<ThRuleRecord> all = ruleDao.getAllRules();
        model.addAttribute("rules", all);


        return "rule/manage";
    }
}
