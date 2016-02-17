package org.nazymko.thehomeland.parser.topology;

import org.nazymko.thehomeland.parser.rule.JsonRule;

import java.util.Optional;
import java.util.Set;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface ProcessorRegister extends ProcessorResolver {

    boolean register(String site, JsonRule rule);

    Optional<Set<String>> availableTypes(String site);

    Optional<Set<String>> availableTypes(Integer site);

}
