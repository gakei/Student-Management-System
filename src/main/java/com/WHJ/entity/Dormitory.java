package com.WHJ.entity;

public class Dormitory {
    private int id;
    private String dormitoryNo;
    private int dormitoryMemNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

    public int getDormitoryMemNum() {
        return dormitoryMemNum;
    }

    public void setDormitoryMemNum(int dormitoryMemNum) {
        this.dormitoryMemNum = dormitoryMemNum;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "id=" + id +
                ", dormitoryNo='" + dormitoryNo + '\'' +
                ", dormitoryMemNum=" + dormitoryMemNum +
                '}';
    }
}
