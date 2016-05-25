package org.nazymko.controller.test;

import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.dao.SyncPageLogDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
@Controller
@RequestMapping("test")
public class TestController {

    @Qualifier("syncPageLogDao")
    @Resource
    private SyncPageLogDao logDao;


    @ResponseBody
    @RequestMapping("latest-page/{consumer}")
    public Object getLatestId(@PathVariable("consumer") String consumer) {
        return null;

    }

    @ExceptionHandler
    void onError(Exception ex) {
        log.error(ex);
    }

}
