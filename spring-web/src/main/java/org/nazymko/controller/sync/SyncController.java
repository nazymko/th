package org.nazymko.controller.sync;

import org.nazymko.th.parser.autodao.tables.pojos.ConnectorConsumer;
import org.nazymko.thehomeland.dao.SyncConsumerDao;
import org.nazymko.thehomeland.scheduler.HourlyScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Controller
@RequestMapping("connector")
public class SyncController {
    @Resource
    SyncConsumerDao dao;
    @Resource
    private HourlyScheduler scheduler;


    @RequestMapping("consumers")
    public String commandPanel(Model model) {
        List<ConnectorConsumer> all = dao.findAll();
        model.addAttribute("items", all);
        return "consumers/consumer-list";
    }

    @RequestMapping(value = "consumers/start", method = RequestMethod.POST)
    public String commandPanelStart(@RequestParam HashMap<String, String> params) {
        scheduler.doIt(params.get("domain"));
        return "consumers/consumer-list";
    }
}
