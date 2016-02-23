package utils.support.task;

import org.jooq.impl.EnumConverter;

/**
 * Created by nazymko
 */
public class TaskStatusConverter extends EnumConverter<Integer, TaskStatus> {
    public TaskStatusConverter(Class<Integer> fromType, Class<TaskStatus> toType) {
        super(fromType, toType);
    }
    public TaskStatusConverter(){
        this(Integer.class,TaskStatus.class);
    }


}
