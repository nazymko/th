package org.nazymko.thehomeland.parser.topology;

import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.model.Page;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

/**
 * <p> Created by nazymko</p>
 * In memory implementation
 */
@Log4j2
public class HistoryHolder implements History {
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
}
