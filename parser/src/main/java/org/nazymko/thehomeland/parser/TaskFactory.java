package org.nazymko.thehomeland.parser;

import lombok.Getter;
import lombok.Setter;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.processors.AttrListener;
import org.nazymko.thehomeland.parser.processors.ParserTask;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskFactory {

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

    public Runnable makeScheduletTastk(String page, String type, Integer sourcePage, Integer sessionKey) {
//        pageDao.save(new Page(site, page, type, sourcePage));

        ParserTask task = new ParserTask(page);

        task.setHistoryDao(historyDao);
        task.setListeners(listeners);
        task.setRuleResolver(resolver);
        task.setPageDao(pageDao);
        task.setRuleDao(ruleDao);
        task.setSiteDao(siteDao);
        task.setAttributeDao(attributeDao);

        return task;
    }

    public void schedulePacing(String site, String page, String pageType, Integer sourcePage, Integer runId) {
        boolean visitedInSession = historyDao.isVisitedInSession(page, runId);
        if (!visitedInSession) {
            new ParserTask(s)

        }
    }
}