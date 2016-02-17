package org.nazymko.thehomeland.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2(topic = THLParserRunner.THL_PARSER_MARKER)
public class THLParserRunner {
    @Resource
    @Getter
    Config config;

    @Getter
    @Setter
    public ThreadPoolExecutor threadPool;
    public static final String signature;

    static {
        signature = UUID.randomUUID().toString();
    }

    @Resource
    @Getter
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

    public void schedule(String page, String pageType, Integer sourcePage, Integer runId) {
        Optional<Runnable> runnable = taskFac.scheduleParsing(page, pageType, sourcePage, runId);
        if (runnable.isPresent()) {
            Runnable task = runnable.get();
            threadPool.submit(task);
        }
    }

    @NoArgsConstructor
    public static class Config {

        @Getter
        @Setter
        private boolean active = false;
    }

}
