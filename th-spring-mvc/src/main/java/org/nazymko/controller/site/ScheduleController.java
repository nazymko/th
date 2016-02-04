package org.nazymko.controller.site;

import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nazymko
 */
@Controller
@RequestMapping("/site")
@Log4j2
public class ScheduleController {

    @Resource
    SiteDao siteDao;

    @RequestMapping("schedule")
    String schedule(Model model) {
        List<Site> all = siteDao.getAll();

        model.addAttribute("sites", all);

        return "site/schedule";
    }


    @RequestMapping(value = "schedule", method = RequestMethod.POST)
    String schedulePost(Model model, @RequestParam HashMap<String, String> params) {

        String cron = params.get("cron");
        log.info(cron);

        CronTrigger cronTrigger = new CronTrigger(cron);

        Date date = cronTrigger.nextExecutionTime(new SimpleTriggerContext());
        System.out.println("next date  = " + date);

        return schedule(model);
    }


}
