package org.nazymko.thehomeland.parser.topology;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface History {
    boolean visited(String link);


    void visitBySession(String link, Integer sessionKey);

    boolean isVisitedInSession(String link, Integer sessionKey);

}
