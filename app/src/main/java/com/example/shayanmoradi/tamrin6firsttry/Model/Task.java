package com.example.shayanmoradi.tamrin6firsttry.Model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mTaskId;
    private String Title;
    private String mDescription;
    private Date mDate;
    private String simpleTime;
    private String simpleDate;
    private boolean mDoneOrUnDone;
    private boolean yesForEditNoForCreate;

    public boolean getYesForEditNoForCreate() {
        return yesForEditNoForCreate;
    }

    public void setYesForEditNoForCreate(boolean yesForEditNoForCreate) {
        this.yesForEditNoForCreate = yesForEditNoForCreate;
    }

    public Task() {
mTaskId= UUID.randomUUID();
mDate = new Date();
    }

    public String getSimpleTime() {
        return simpleTime;
    }

    public void setSimpleTime(String simpleTime) {
        this.simpleTime = simpleTime;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }

    public UUID getmTaskId() {
        return mTaskId;
    }

    public void setmTaskId(UUID mTaskId) {
        this.mTaskId = mTaskId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean getmDoneOrUnDone() {
        return mDoneOrUnDone;
    }

    public void setmDoneOrUnDone(boolean mDoneOrUnDone) {
        this.mDoneOrUnDone = mDoneOrUnDone;
    }
}
