package org.nazymko.thehomeland.parser.processors;

import org.nazymko.thehomeland.parser.db.model.Attribute;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface AttrListener extends Listener{

    default String identifier() {
        return "AttrListener: ? ";
    }

}
