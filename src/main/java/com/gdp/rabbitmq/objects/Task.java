package com.gdp.rabbitmq.objects;

import java.io.Serializable;

/**
 *
 * @author zerosign
 */
public final class Task implements Serializable {
    
    public enum TaskType {
        CRAWL, FILTER, SCRAP, LEARN, INDEX
    }
    
    private final int id;
    private TaskType type;
    private Task next;
    private long did;
    
    public Task(int id, TaskType type) {
        this.id = id;
        this.type = type;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Task next() {
        return next;
    }

    public void setNext(Task next) {
        this.next = next;
    }


    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }
    
    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", type=" + type + ", next=" + next + '}';
    }
}
