package org.nazymko.thehomeland.parser.processors;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface AttrListener extends Listener{

    default String identifier() {
        return "AttrListener: ? ";
    }

}
