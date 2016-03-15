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
        log.debug("link = {}", link);
        Optional<Page> page = pageDao.get(link);
        log.debug("page = {} ", page);
        if (page.isPresent()) {
            return page.get().getVisited() != null;
        }
        return false;
    }

    @Override
    public void visitBySession(String link, Integer sessionKey) {
        PageRecord pageRecord = pageDao.getPageByUrlAndSession(link, sessionKey);

        if (pageRecord != null) {
            pageRecord.setVisitedAt(now());
            pageRecord.store();
        } else {
            log.debug("Page {} with session {} was not found.", link, sessionKey);
        }
    }

    @Override
    public boolean isVisitedInSession(String link, Integer sessionKey) {
        PageRecord pageByUrlAndSession = pageDao.getPageByUrlAndSession(link, sessionKey);
        return pageByUrlAndSession != null && pageByUrlAndSession.getVisitedAt() != null;
    }
}
