package org.nazymko.thehomeland.services;

import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.dao.SyncPageLogDao;
import org.springframework.messaging.Message;

import javax.annotation.Resource;

/**
 * Created by nazymko.patronus@gmail.com
 */

@Log4j2
public class SendingLogService {
    @Resource
    SyncPageLogDao logDao;

    public void failedToSend(Integer pageId, Integer status, String consumer) {
        logDao.save(pageId, status, consumer);
    }

    public void afterSend(Message<String> message) {
        Integer pageId = headerInt(message, "X-Original-Page-Id");
        logDao.save(pageId,
                message.getPayload(),
                headerInt(message, "http_statusCode"),
                "http://thehomeland.com.ua",
                headerLong(message, "timestamp"),
                headerString(message, "consumer_url"));
        log.info(message);
    }

    private String headerString(Message<String> message, String header) {
        return message.getHeaders().get(header).toString();
    }

    private Integer headerInt(Message<String> message, String http_statusCode) {
        return Integer.valueOf(message.getHeaders().get(http_statusCode).toString());
    }

    private Long headerLong(Message<String> message, String header) {
        return Long.valueOf(headerString(message, header));
    }
}
