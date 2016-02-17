package org.nazymko.thehomeland.parser.topology;

import org.nazymko.thehomeland.parser.rule.PageItem;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface ProcessorResolver {

    Optional<HashMap<String, PageItem>> resolveBySite(String site);

    Optional<PageItem> resolveByAttr(String site, String attr);

    Optional<PageItem> resolveByTypeForSite(String site, String type);

}
