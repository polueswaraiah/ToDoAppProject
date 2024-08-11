package com.todoapp.model;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable  {
	
    private int id;
    private String title;
    private Date createdDate;
    private int userId;

    public Project() {
    }

    public Project(int id, String title, Date createdDate, int userId) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date  getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "[" + id + ", " + title + ", " + createdDate + ", " + userId + "]";
	}
    
    
}
