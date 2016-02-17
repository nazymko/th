package org.nazymko.controller.task;

import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
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
@RequestMapping("task")
public class ScheduleTaskActionController {

    @Resource
    ScheduleDao scheduleDao;

    @RequestMapping(value = "schedule/{id}/suspend", method = RequestMethod.GET)
    public String suspend(Model model, @PathVariable("id") Integer id) {

        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(id);
        if (tScheduleRecord.isPresent()) {
            tScheduleRecord.get().setIsEnabled(false);
            tScheduleRecord.get().store();
        }

        putArgs(model);

        return "task/list-all";
    }

    private void putArgs(Model model) {
        List<TScheduleRecord> all = scheduleDao.getAll();

        model.addAttribute("list", all);
    }

    @RequestMapping(value = "schedule/{id}/activate", method = RequestMethod.GET)
    public String activate(Model model, @PathVariable("id") Integer id) {

        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(id);
        if (tScheduleRecord.isPresent()) {
            tScheduleRecord.get().setIsEnabled(true);
            tScheduleRecord.get().store();
        }
        putArgs(model);
        return "task/list-all";
    }

    @Resource
    TaskDao taskDao;

    @RequestMapping(value = "schedule/{id}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") Integer id) {
        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(id);
        if (tScheduleRecord.isPresent()) {
            TScheduleRecord record = tScheduleRecord.get();

            Optional<List<TTaskRecord>> byScheduleId = taskDao.getByScheduleId(id);
            if (byScheduleId.isPresent()) {
                List<TTaskRecord> tTaskRecords = byScheduleId.get();
                for (TTaskRecord tTaskRecord : tTaskRecords) {
                    int delete = tTaskRecord.delete();
                    System.out.println("delete 1 = " + delete);
//                    tTaskRecord.store();
                }
            }

            int delete = scheduleDao.get(id).get().delete();
            tScheduleRecord.get().store();
            System.out.println("delete = " + delete);
        }
        putArgs(model);
        return "task/list-all";
    }
}
