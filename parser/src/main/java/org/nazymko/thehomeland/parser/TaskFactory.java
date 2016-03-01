package org.nazymko.thehomeland.parser;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.db.dao.*;
import org.nazymko.thehomeland.parser.processors.AttrListener;
import org.nazymko.thehomeland.parser.processors.ParserTask;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import utils.TimeStampHelper;
import utils.support.runtype.RunType;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static utils.support.runtype.RunType.MANUAL;

@Log4j2
public class TaskFactory {

    @Resource
    TaskDao taskDao;
    @Autowired
    History historyDao;
    @Autowired
    private RuleResolver resolver;
    @Autowired
    @Setter
    private PageDao pageDao;
    @Autowired
    private RuleDao ruleDao;
    @Setter
    @Autowired
    private SiteDao siteDao;
    @Setter
    private List<AttrListener> listeners;

    public Runnable makeScheduledTask(String page, String type, Integer sourcePage, Integer sessionKey) {
        ParserTask task = new ParserTask(page);
        injectDao(task);

        task.setListeners(listeners);
        task.setSessionKey(sessionKey);
        task.setType(type);
        task.setSourcePage(sourcePage);

        return task;
    }

    public synchronized Optional<Runnable> scheduleParsing(String page, String pageType, Integer sourcePage, Integer runId) {
        boolean visitedInSession = historyDao.isVisitedInSession(page, runId);
        if (!visitedInSession) {
            return Optional.of(makeScheduledTask(page, pageType, sourcePage, runId));
        } else {
            log.warn("page {} rejected, because it was visited on page {} at run {}", page,sourcePage,runId);
        }

        return Optional.empty();
    }

    public TTaskRecord nextRecord(Integer siteId) {
        TTaskRecord tTaskRecord = new TTaskRecord();
        taskDao.attach(tTaskRecord);

        tTaskRecord.setStartAt(TimeStampHelper.now());
        tTaskRecord.setIsEnabled(true);
        tTaskRecord.setMessage("Manual start");
        tTaskRecord.setRunType(MANUAL);
        tTaskRecord.setSiteId(siteId);
        tTaskRecord.setScheduleSourceId(null);
        tTaskRecord.store();

        return tTaskRecord;
    }

    public void injectDao(ParserTask dao) {
        dao.setHistoryDao(historyDao);
        dao.setPageDao(pageDao);
        dao.setSiteDao(siteDao);
        dao.setRuleDao(ruleDao);
        dao.setRuleResolver(resolver);
    }
}