package org.nazymko.thehomeland.parser.rule;

import lombok.Data;

/**
 * Created by nazymko.patronus@gmail.com.
 */

@Data
public class ParsingRule extends JsonRule {
    transient int id;
    transient int version;
}
