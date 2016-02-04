package org.nazymko.thehomeland.parser.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by nazymko
 * <p>
 * <code>
 * int siteId;<br>
 * int pageId;<br>
 * int ruleId;<br>
 * int attrIndex;<br>
 * String attrMeaning;<br>
 * String attrValue;<br>
 * String attrType;<br>
 * String attrFormat;<br>
 * </code>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    @NonNull
    int siteId, pageId, ruleId, attrIndex = -1;
    @NonNull
    String attrMeaning, attrValue, attrType;

    String attrFormat;
}
