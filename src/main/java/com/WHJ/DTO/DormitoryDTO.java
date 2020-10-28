package com.WHJ.DTO;

import com.WHJ.entity.Dormitory;
import com.WHJ.entity.Student;

import java.util.List;

public class DormitoryDTO {
    private int code;
    private String msg;
    private int count;
    private List<Dormitory> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Dormitory> getData() {
        return data;
    }

    public void setData(List<Dormitory> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DormitoryDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
