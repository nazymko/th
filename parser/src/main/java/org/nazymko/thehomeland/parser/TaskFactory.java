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

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private AttributeDao attributeDao;
    @Setter
    private List<AttrListener> listeners;

    public Runnable makeScheduledTask(String page, String type, Integer sourcePage, Integer sessionKey) {
//        pageDao.save(new Page(site, page, type, sourcePage));

        ParserTask task = new ParserTask(page);

        task.setListeners(listeners);
        task.setSessionKey(sessionKey);
        task.setType(type);
        task.setSourcePage(sourcePage);

        return task;
    }

    public Optional<Runnable> scheduleParsing(String page, String pageType, Integer sourcePage, Integer runId) {
        boolean visitedInSession = historyDao.isVisitedInSession(page, runId);
        if (!visitedInSession) {
            return Optional.of(makeScheduledTask(page, pageType, sourcePage, runId));
        } else {
            log.warn("page {} rejected, because it was visited", page);
        }

        return Optional.empty();
    }

    public TTaskRecord nextRecord() {
        TTaskRecord tTaskRecord = new TTaskRecord();
        taskDao.attach(tTaskRecord);

        tTaskRecord.setStartAt(TimeStampHelper.now());
        tTaskRecord.setIsEnabled(true);
        tTaskRecord.setMessage("Manual start");
        tTaskRecord.setScheduleSourceId(null);
        tTaskRecord.store();

        return tTaskRecord;
    }
}