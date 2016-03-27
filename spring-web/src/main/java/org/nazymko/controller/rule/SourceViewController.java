package org.nazymko.controller.rule;

import com.google.gson.Gson;
import org.nazymko.th.parser.autodao.tables.records.ThRuleRecord;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
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
public class SourceViewController {

    @Resource RuleDao ruleDao;
    @Resource Gson gson;

    @RequestMapping("source/{id}")
    public String viewSource(@PathVariable Integer id, Model model) {

        Optional<ThRuleRecord> byId = ruleDao.getById(id);
        if (!byId.isPresent()) {
            model.addAttribute("warn", "Rule with id " + id + " not found");
        } else {
            model.addAttribute("gson", gson);
            model.addAttribute("rule", byId.get());
        }

        return "rule/source";
    }
}
