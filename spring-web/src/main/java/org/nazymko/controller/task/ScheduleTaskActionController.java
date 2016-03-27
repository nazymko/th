package org.nazymko.controller.task;

import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;
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
    @Resource
    TaskDao taskDao;

    @RequestMapping(value = "schedule/{id}/suspend", method = RequestMethod.GET)
    public String suspend(Model model, @PathVariable("id") Integer id) {

        Optional<TaskScheduleRecord> tScheduleRecord = scheduleDao.getById(id);
        if (tScheduleRecord.isPresent()) {
            tScheduleRecord.get().setIsEnabled(false);
            tScheduleRecord.get().store();
        }

        putArgs(model);

        return "task/list-all";
    }

    private void putArgs(Model model) {
        List<TaskScheduleRecord> all = scheduleDao.getAll();

        model.addAttribute("list", all);
    }

    @RequestMapping(value = "schedule/{id}/activate", method = RequestMethod.GET)
    public String activate(Model model, @PathVariable("id") Integer id) {

        Optional<TaskScheduleRecord> tScheduleRecord = scheduleDao.getById(id);
        if (tScheduleRecord.isPresent()) {
            tScheduleRecord.get().setIsEnabled(true);
            tScheduleRecord.get().store();
        }
        putArgs(model);
        return "task/list-all";
    }

    @RequestMapping(value = "schedule/{id}/delete", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") Integer id) {
        Optional<TaskScheduleRecord> tScheduleRecord = scheduleDao.getById(id);
        if (tScheduleRecord.isPresent()) {
            TaskScheduleRecord record = tScheduleRecord.get();

            Optional<List<TaskRunRecord>> byScheduleId = taskDao.getByScheduleId(id);
            if (byScheduleId.isPresent()) {
                List<TaskRunRecord> tTaskRecords = byScheduleId.get();
                for (TaskRunRecord tTaskRecord : tTaskRecords) {
                    int delete = tTaskRecord.delete();
                    System.out.println("delete 1 = " + delete);
//                    tTaskRecord.store();
                }
            }

            int delete = scheduleDao.getById(id).get().delete();
            tScheduleRecord.get().store();
            System.out.println("delete = " + delete);
        }
        putArgs(model);
        return "task/list-all";
    }
}
