package org.nazymko.thehomeland.parser.topology;

import java.sql.Timestamp;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface History {
    boolean visited(String link);


    void visitBySession(String link, Integer sessionKey);

    boolean isVisitedInSession(String link, Integer sessionKey);

}
