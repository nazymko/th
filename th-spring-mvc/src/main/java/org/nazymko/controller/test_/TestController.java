package org.nazymko.controller.test_;

import org.nazymko.thehomeland.dao.SyncMainLogDao;
import org.nazymko.thehomeland.dao.SyncPageLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Qualifier("pageLogDao")
    @Autowired
    private SyncPageLogDao logDao;
    @Qualifier("mainLogDao")
    @Autowired
    private SyncMainLogDao mainLogDao;


    @ResponseBody
    @RequestMapping("latest-page/{consumer}")

    public Object getLatestId(@PathVariable("consumer") String consumer) {
        return mainLogDao.getLastPage(consumer);

    }
}
