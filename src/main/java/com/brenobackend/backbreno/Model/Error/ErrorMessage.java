package com.brenobackend.backbreno.Model.Error;

public class ErrorMessage {
    
    private String title;

    private Integer status;

    private String message;

    public ErrorMessage(String title, Integer status, String message) {
        this.title = title;
        this.status = status;
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
    
}
