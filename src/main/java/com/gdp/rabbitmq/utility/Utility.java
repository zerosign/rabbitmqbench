package com.gdp.rabbitmq.utility;

import com.gdp.rabbitmq.objects.Task;
import java.security.SecureRandom;

/**
 *
 * @author zerosign
 */
public class Utility {

    private final static SecureRandom RANDOM = new SecureRandom();
    private final static Task.TaskType[] TYPES = Task.TaskType.values();

    public final static Task GenerateRandomTask() {
        final Task newTask = new Task(Math.abs(RANDOM.nextInt()),
                GenerateRandomTaskType());
        
        return newTask;
    }

    public final static Task.TaskType GenerateRandomTaskType() {
        return TYPES[Math.abs(RANDOM.nextInt()) % TYPES.length];
    }

}
