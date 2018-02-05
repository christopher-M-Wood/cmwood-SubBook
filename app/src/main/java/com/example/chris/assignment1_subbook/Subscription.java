package com.example.chris.assignment1_subbook;

import java.io.Serializable;

/**
 * Created by critt on 2018-01-23.
 */

public class Subscription implements Serializable{
    private String name;
    private String startDate;
    private float chargeValue;
    private String comment;

    /* Constructors */
    public Subscription() throws InvalidSubscriptionException{
        throw new InvalidSubscriptionException();
    }

    public Subscription(String name, int chargeValue){
        this.name = name;
        this.chargeValue = chargeValue;
        startDate = "Feb 5, 2017";
    }
    public Subscription(String name, String startDate, int chargeValue){
        this.name = name;
        this.startDate = startDate;
        this.chargeValue = chargeValue;
        comment = "";
    }
    public Subscription(String name, String startDate, float chargeValue, String comment) throws InvalidSubscriptionException {
        if (name == "" || startDate == "" || chargeValue == 0.0 || name.length() > 20 ||
                startDate.length() != 10 || chargeValue < 0.0 || comment.length() > 30){
            throw new InvalidSubscriptionException();
        }
        this.name = name;
        this.startDate = startDate;
        this.chargeValue = chargeValue;
        this.comment = comment;
    }

    /* "Get" commands, all return Strings */
    public String getName(){
        return name;
    }

    public String getStartDate(){
        return startDate;
    }

    public String getChargeValue(){
        return String.valueOf(chargeValue)+"$ CAD";
    }

    public String getComment(){
        return comment;
    }

    /* "Set" commands */
    public void setName(String name){
        this.name = name;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public void setChargeValue(float chargeValue){
        this.chargeValue = chargeValue;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}
