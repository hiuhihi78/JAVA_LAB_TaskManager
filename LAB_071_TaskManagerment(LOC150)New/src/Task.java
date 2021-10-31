
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
class Task implements Serializable{
    private int id;
    private String name;
    private String taskType;
    private String date;
    private double from;
    private double to;
    private String assignee;
    private String reveiwer;

    public Task() {
    }

    public Task(int id, String name, String taskType, String date, double from, double to, String assignee, String reveiwer) {
        this.id = id;
        this.name = name;
        this.taskType = taskType;
        this.date = date;
        this.from = from;
        this.to = to;
        this.assignee = assignee;
        this.reveiwer = reveiwer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReveiwer() {
        return reveiwer;
    }

    public void setReveiwer(String reveiwer) {
        this.reveiwer = reveiwer;
    }
    
    @Override
    public String toString() {
        return String.format("%-5d%-20s%-15s%-15s%-10.1f%-15s%-15s",
                id, name, taskType, date, (to - from), assignee, reveiwer);   
    }

//    @Override
//    public String toString() {
//        return id + name + taskType + date + to + from + assignee + reveiwer;
//    }
    
}
