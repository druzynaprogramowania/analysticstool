package com.projectmgr.managecompany.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ItemIdExceptionResponse {

    private String projectIdentifier;

    //when we creating the exception we gonna pass the message and that message going to return projectIdentifier object
    public ItemIdExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
