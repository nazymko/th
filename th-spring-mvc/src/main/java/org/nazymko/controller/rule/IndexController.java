package org.nazymko.controller.rule;

import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com. on 17.01.2016.
 */
@Controller
@RequestMapping("rule")
public class IndexController {
    @Resource
    RuleDao ruleDao;

    @RequestMapping()
    public String index(Model model) {
        List<JsonRule> all = ruleDao.getAll();

        model.addAttribute("rules", all);
        return "rule/small-list";
    }

}
