package org.nazymko.scheduler.task;

import lombok.Setter;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class AbstractPost implements PostTask {
    @Setter String url;
    @Setter String accessToken;

    @Override
    public void doWork() {

    }
}
