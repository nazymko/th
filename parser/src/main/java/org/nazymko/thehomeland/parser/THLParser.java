package org.nazymko.thehomeland.parser;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.nazymko.thehomeland.parser.processors.AttrListener;
import org.nazymko.thehomeland.parser.processors.CssPageTask;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by nazymko
 */
@Log4j2(topic = THLParser.THL_PARSER_MARKER)
public class THLParser {

    @Getter
    @Setter
    public ThreadPoolExecutor threadPool;
    @Autowired
    History historyDao;
    @Autowired
    private RuleResolver resolver;
    @Autowired
    @Setter
    private PageDao pageDao;
    @Setter
    @Autowired
    private RuleDao ruleDao;
    @Setter
    @Autowired
    private SiteDao siteDao;
    @Setter
    @Autowired
    private AttributeDao attributeDao;
    @Getter
    @Setter
    private boolean active = false;

    public void setTimToCheck(Integer timToCheck) {
        this.timToCheck = timToCheck;
    }

    private Integer timToCheck = 1;
    private Integer timToCheckFormet = 7;

    public void setListeners(List<AttrListener> listeners) {
        this.listeners = listeners;
    }

    private List<AttrListener> listeners;

    public static final String THL_PARSER_MARKER = "THL_PARSER";

    public void init() {
        resolver.init();
    }

    public boolean schedule(String site, String page) {
        return schedule(site, page, "front_page", -1);
    }

    public boolean schedule(String site, String page, String type, Integer sourcePage) {

        if (isActive()) {
            if (!historyDao.visited(page) || needToRefresh(page)) {

                pageDao.save(new Page(site, page, type, sourcePage));

                CssPageTask task = new CssPageTask(page, listeners, historyDao);

                task.setRuleResolver(resolver);
                task.setPageDao(pageDao);
                task.setRuleDao(ruleDao);
                task.setSiteDao(siteDao);
                task.setAttributeDao(attributeDao);

                threadPool.submit(task);

                return true;
            } else {
                log.warn("Rejected schedule to add {}. time to live check return false", page);
                return false;
            }
        } else {
            log.warn("Rejected schedule to add {}. Parser is not active", page);
            return false;
        }
    }

    private boolean needToRefresh(String page) {
        return historyDao.lastVisit(page).toInstant().isBefore(Instant.now().minus(timToCheck, ChronoUnit.DAYS));
    }

    public int queSize() {
        BlockingQueue<Runnable> queue = threadPool.getQueue();
        return queue.size();
    }

    public List<String> getStatusMessage() {
        BlockingQueue<Runnable> queue = threadPool.getQueue();
        List<String> infoList = new ArrayList<>();
        infoList.add(String.format("There are %s tasks submitted", queue.size()));
        return infoList;


    }
}
