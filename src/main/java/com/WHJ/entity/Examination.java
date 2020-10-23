package com.WHJ.entity;

import java.util.Date;

public class Examination {
    private int id;
    private Date examTime;
    private String examName;
    private String chargeTeacher;
    private String examPlace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getChargeTeacher() {
        return chargeTeacher;
    }

    public void setChargeTeacher(String chargeTeacher) {
        this.chargeTeacher = chargeTeacher;
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", examTime=" + examTime +
                ", examName='" + examName + '\'' +
                ", chargeTeacher='" + chargeTeacher + '\'' +
                ", examPlace='" + examPlace + '\'' +
                '}';
    }
}
