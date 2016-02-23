package utils.support.runtype;

import org.jooq.impl.EnumConverter;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class RunTypeConverter extends EnumConverter<Integer, RunType> {
    public RunTypeConverter(Class<Integer> fromType, Class<RunType> toType) {
        super(fromType, toType);
    }

    public RunTypeConverter() {
        this(Integer.class, RunType.class);
    }


}
