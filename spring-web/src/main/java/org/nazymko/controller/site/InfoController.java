package org.nazymko.controller.site;

import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by nazymko.patronus@gmail.com. on 17.01.2016.
 */
@Controller
@RequestMapping("site")
public class InfoController {

    @Resource
    SiteDao siteDao;
    @Resource
    RuleResolver resolver;

    @RequestMapping("{id}/supportedtypes")
    public String info(Model model, @PathVariable("id") Integer site) {


        Set<String> types = resolver.availableTypes(site).get();
        model.addAttribute("types", types);

        return "site/supported-types";
    }
}
