package org.nazymko.controller.task;

import lombok.extern.log4j.Log4j2;
import org.jooq.util.derby.sys.Sys;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nazymko
 */
@Controller
@RequestMapping("/task")
@Log4j2
public class ScheduleController {

    @Resource
    SiteDao siteDao;
    @Resource
    ScheduleDao scheduleDao;
    @Resource
    TaskDao taskDao;

    @RequestMapping("schedule")
    String schedule(Model model) {
        List<Site> all = siteDao.getAll();

        model.addAttribute("sites", all);

        return "task/schedule";
    }


    @RequestMapping(value = "schedule", method = RequestMethod.POST)
    String schedulePost(Model model, @RequestParam HashMap<String, String> params) {

        String cron = params.get("cron");
        Integer siteId = Integer.valueOf(params.get("site_id"));
        log.info(cron);

        CronTrigger cronTrigger = new CronTrigger(cron);

        Date date = cronTrigger.nextExecutionTime(new SimpleTriggerContext());


        TScheduleRecord record = new TScheduleRecord();

        record.setCron(cron);
        record.setIsEnabled(true);
        record.setPageType("front_page");
        record.setSiteid(siteId);
        record.setStartPage(siteDao.get(siteId).get().getUrl());
        record.setStartAt(new Timestamp(System.currentTimeMillis()));

        scheduleDao.save(record);

        log.info(record);

        System.out.println("next date  = " + date);

        return schedule(model);
    }


}
