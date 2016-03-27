package org.nazymko.controller.task;

import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com. on 10.02.2016.
 */
@Controller
@RequestMapping("task")
public class ScheduleListAllController {
    @Resource
    ScheduleDao scheduleDao;

    @RequestMapping("list-all")
    public String list(Model model) {

        List<TaskScheduleRecord> all = scheduleDao.getAll();

        model.addAttribute("list", all);


        return "task/list-all";
    }

}
