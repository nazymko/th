package org.nazymko.thehomeland.parser.processors;

/**
 * Created by nazymko
 */
public interface Processor {

    long timeout();

    void setTimeout(long timeout);

    void process(String value);

    Runnable schedule(String value);
}
