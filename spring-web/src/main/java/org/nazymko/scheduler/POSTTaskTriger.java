package org.nazymko.scheduler;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.scheduler.task.PostTask;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class POSTTaskTriger {
    @Setter private List<PostTask> postTaskList;

    @Scheduled(cron = "0 */1 * * * *")
    void doWork() {

        for (PostTask postTask : postTaskList) {
            postTask.doWork();
        }
    }
}
