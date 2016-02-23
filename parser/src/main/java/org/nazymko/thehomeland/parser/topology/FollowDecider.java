package org.nazymko.thehomeland.parser.topology;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.PageRecord;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.model.Page;
import utils.TimeStampHelper;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

import static utils.TimeStampHelper.now;

/**
 * <p> Created by nazymko.patronus@gmail.com.</p>
 * In memory implementation
 */
@Log4j2
public class FollowDecider implements History {
    @Resource
    PageDao pageDao;

    @Override
    public boolean visited(String link) {
        log.info("link = {}", link);
        Optional<Page> page = pageDao.get(link);
        log.info("page = {} ", page);
        if (page.isPresent()) {
            return page.get().getVisited() != null;
        }
        return false;
    }

    @Override
    public Timestamp lastVisit(String link) {
        Optional<Page> page = pageDao.get(link);
        if (!page.isPresent()) {
            return Timestamp.from(Instant.EPOCH);//not visited
        }
        return page.get().getVisited();
    }

    @Override
    public void visit(String link) {
        if (!pageDao.get(link).isPresent()) {
            throw new IllegalArgumentException("Page was not found in database :" + link);
        }

        pageDao.visit(link);

    }

    @Override
    public void visitBySession(String link, Integer sessionKey) {
        PageRecord pageRecord = pageDao.getPageByUrlAndSession(link, sessionKey);

        if (pageRecord != null) {
            pageRecord.setVisitedAt(now());
            pageRecord.store();
        } else {
            log.info("Page {} with session {} was not found.", link, sessionKey);
        }
    }

    @Override
    public boolean isVisitedInSession(String link, Integer sessionKey) {
        PageRecord pageByUrlAndSession = pageDao.getPageByUrlAndSession(link, sessionKey);
        return pageByUrlAndSession != null && pageByUrlAndSession.getVisitedAt() != null;
    }
}