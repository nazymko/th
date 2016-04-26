package org.nazymko.thehomeland.integration;

import org.springframework.messaging.Message;

/**
 * Created by nazymko.patronus@gmail.com
 */
public interface PostMessageChannel {

    void send(Message<String> msg);
}
