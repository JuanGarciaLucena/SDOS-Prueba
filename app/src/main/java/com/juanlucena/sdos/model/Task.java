package com.juanlucena.sdos.model;

import com.orm.SugarRecord;

public class Task extends SugarRecord{

    private int taskTime, taskUserAsigned;
    private String taskName, taskDescription;
    private boolean isFinished;

    public Task(){}

    public Task(int taskTime, int taskUserAsigned, String taskName, String taskDescription, boolean isFinished) {
        this.taskTime = taskTime;
        this.taskUserAsigned = taskUserAsigned;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isFinished = isFinished;
    }

    public int getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(int taskTime) {
        this.taskTime = taskTime;
    }

    public int getTaskUserAsigned() {
        return taskUserAsigned;
    }

    public void setTaskUserAsigned(int taskUserAsigned) {
        this.taskUserAsigned = taskUserAsigned;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
