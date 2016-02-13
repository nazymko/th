package org.nazymko.thehomeland.parser;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by nazymko
 */
@Log4j2(topic = THLParserRunner.THL_PARSER_MARKER)
public class THLParserRunner {
    @Resource
    Config config;

    @Getter
    @Setter
    public ThreadPoolExecutor threadPool;
    public static final String signature;

    static {
        signature = UUID.randomUUID().toString();
    }

    @Resource
    private TaskFactory taskFac;

    public static final String THL_PARSER_MARKER = "THL_PARSER";

    public void submit(Runnable task) {
        threadPool.submit(task);
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

    public void schedule(String site, String page, String pageType, Integer sourcePage, Integer runId) {
        taskFac.schedulePacing(site, page, pageType, sourcePage, runId);
    }

    static class Config {
        @Getter
        @Setter
        private boolean active = false;
    }

}
