package com.todoapp.model;

import java.io.Serializable;
import java.util.Date;

public class Todo  implements Serializable  {
   

    private int id;
    private String description;
    private String status;
    private Date createdDate;
    private Date updatedDate;
    private int projectId;

    public Todo() {
    }

    public Todo(int id, String description, String status, Date createdDate, Date updatedDate, int projectId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.projectId = projectId;
    }
    
    public Todo(String description, String status, Date createdDate, Date updatedDate, int projectId) {
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    @Override
   	public String toString() {
   		return "Todo [id=" + id + ", description=" + description + ", status=" + status + ", createdDate=" + createdDate
   				+ ", updatedDate=" + updatedDate + ", projectId=" + projectId + "]";
   	}

}