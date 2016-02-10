package org.nazymko.controller.task;

import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by nazymko
 */
@Controller
@RequestMapping("task")
public class ScheduleTaskActionController {

    @Resource
    ScheduleDao scheduleDao;

    @RequestMapping(value = "schedule/{id}/suspend", method = RequestMethod.GET)
    public String suspend(Model model, @PathVariable("id") Integer id) {

        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(id);
        if (tScheduleRecord.isPresent()) {
            tScheduleRecord.get().setIsEnabled(false);
        }
        return "task/list-all";
    }

    @RequestMapping(value = "schedule/{id}/activate", method = RequestMethod.GET)
    public String activate(Model model, @PathVariable("id") Integer id) {

        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(id);
        if (tScheduleRecord.isPresent()) {
            tScheduleRecord.get().setIsEnabled(true);
        }
        return "task/list-all";
    }

    @RequestMapping(value = "schedule/{id}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") Integer id) {
        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(id);
        if (tScheduleRecord.isPresent()) {
            int delete = tScheduleRecord.get().delete();
            System.out.println("delete = " + delete);
        }
        return "task/list-all";
    }
}
