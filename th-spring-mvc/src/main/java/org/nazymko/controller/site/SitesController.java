package org.nazymko.controller.site;

import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.nazymko.thehomeland.parser.db.model.Site;
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
    private SiteDao siteDao;

    @RequestMapping(method = RequestMethod.GET)
    public String root(Model model) {
//        List<Site> sites = siteDao.getList(10, 0);
        List<Site> sites = siteDao.getAll();

        model.addAttribute("sites", sites);

        return "site/sites";
    }

    @RequestMapping(value = "commands", method = RequestMethod.GET)
    public String sites(Model model) {

        List<Site> sites = siteDao.getList(10, 0);

        model.addAttribute("sites", sites);

        return "site/sites";
    }

    @Resource
    PageDao pageDao;

    @RequestMapping(value = "{id}/info", method = RequestMethod.GET)
    public String info(Model model, @PathVariable("id") Integer id) {


        Optional<Site> siteOptional = siteDao.get(id);
        if (siteOptional.isPresent()) {
            model.addAttribute("site", siteOptional.get());
            List<Page> latestVersion = pageDao.getLatestVersion(id);
            model.addAttribute("pages", latestVersion);
        }

        return "site/info";
    }
}