package org.nazymko.thehomeland.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.processors.InfoSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class THLParserRunner {
    public static final String signature;

    static {
        signature = UUID.randomUUID().toString();
    }

    @Getter
    @Setter
    public ThreadPoolExecutor threadPool;
    @Resource
    @Getter
    Config config;
    @Resource
    LinkedBlockingDeque<Runnable> deque;
    @Resource
    @Getter
    private TaskFactory taskFac;

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

    public Integer tasks(Integer session) {
        int count = 0;
        for (Runnable task : deque) {
            if (task instanceof InfoSource) {
                if (session.equals(((InfoSource) task).runId())) {
                    count++;
                }
            }
        }

        return count;
    }

    @NoArgsConstructor
    public static class Config {

        @Getter
        @Setter
        private boolean active = false;
    }

}
