package org.nazymko.controller.site;

import org.nazymko.th.parser.autodao.tables.pojos.ThPage;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.dao.ThPageDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Controller
@RequestMapping(value = "/site")
public class SitesController {

    @Resource
    PageDao pageDao;
    @Resource
    @Qualifier("thPageDao")
    ThPageDao thPageDao;
    @Resource
    private SiteDao siteDao;

    @RequestMapping(method = RequestMethod.GET)
    public String root(Model model) {
        List<ThSiteRecord> sites = siteDao.getAll();

        model.addAttribute("sites", sites);

        return "site/sites";
    }

    @RequestMapping(value = "commands", method = RequestMethod.GET)
    public String sites(Model model) {

        List<ThSiteRecord> sites = siteDao.getList(10, 0);

        model.addAttribute("sites", sites);

        return "site/sites";
    }

    @RequestMapping(value = "{id}/info", method = RequestMethod.GET)
    public String info(Model model, @PathVariable("id") Integer id) {


        Optional<ThSiteRecord> siteOptional = siteDao.getById(id);
        if (siteOptional.isPresent()) {
            model.addAttribute("site", siteOptional.get());
            List<ThPage> latestVersion = thPageDao.getVisited(id);
            model.addAttribute("pages", latestVersion);
        }

        return "site/info";
    }
}