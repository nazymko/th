package org.nazymko.thehomeland.parser;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
import org.nazymko.thehomeland.parser.processors.AttrListener;
import org.nazymko.thehomeland.parser.processors.ParsingTask;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import utils.TimeStampHelper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static utils.support.runtype.RunType.MANUAL;

@Log4j2
public class TaskFactory {

    @Resource
    TaskDao taskDao;
    @Resource
    History historyDao;
    @Resource
    private RuleResolver resolver;
    @Resource
    @Setter
    private PageDao pageDao;
    @Resource
    private RuleDao ruleDao;
    @Setter
    @Resource
    private SiteDao siteDao;
    @Setter
    private List<AttrListener> listeners;

    public Runnable makeScheduledTask(String page, String type, Integer sourcePage, Integer sessionKey) {
        log.info("Registered for executing: page = {},type = {},source = {}, session = {}", page, type, sourcePage, sessionKey);
        ParsingTask task = new ParsingTask(page);
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
            log.warn("page {} rejected, because it was visited on page {} at run {}", page, sourcePage, runId);
        }

        return Optional.empty();
    }

    public TaskRunRecord nextRecord(Integer siteId) {
        TaskRunRecord tTaskRecord = new TaskRunRecord();
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

    public void injectDao(ParsingTask dao) {
        dao.setHistoryDao(historyDao);
        dao.setPageDao(pageDao);
        dao.setSiteDao(siteDao);
        dao.setRuleDao(ruleDao);
        dao.setRuleResolver(resolver);
    }
}