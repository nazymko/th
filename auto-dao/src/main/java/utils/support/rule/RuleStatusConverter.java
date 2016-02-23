package utils.support.rule;

import org.jooq.impl.EnumConverter;

/**
 * Created by nazymko
 */
public class RuleStatusConverter extends EnumConverter<Integer, RuleStatus> {
    public RuleStatusConverter(Class<Integer> fromType, Class<RuleStatus> toType) {
        super(fromType, toType);
    }

    public RuleStatusConverter() {
        this(Integer.class, RuleStatus.class);
    }


}
