package org.nazymko.thehomeland.parser.db.model;

import lombok.*;

/**
 * Created by nazymko.patronus@gmail.com.
 * <p>
 * <code>
 * int siteId;<br>
 * int pageId;<br>
 * int ruleId;<br>
 * int attrIndex;<br>
 * String attrMeaning;<br>
 * String attrValue;<br>
 * String attrTag;<br>
 * String attrFormat;<br>
 * </code>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attribute {
    @NonNull
    int siteId, pageId, ruleId, attrIndex = -1;
    @NonNull
    String attrMeaning, attrValue, attrTag;

    boolean persistable = true;

    String attrFormat;
}
