package org.nazymko.thehomeland.parser.topology;

import java.sql.Timestamp;

/**
 * Created by nazymko
 */
public interface History {
    boolean visited(String link);

    Timestamp lastVisit(String link);

    void visit(String link);

    void visitBySession(String link, Integer sessionKey);

    boolean isVisitedInSession(String link, Integer sessionKey);

}
