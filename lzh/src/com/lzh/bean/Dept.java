package com.lzh.bean;

import java.util.Objects;

public class Dept {
    private String no = null;
    private String name = null;
    private String loc = null;

    public Dept() {
    }

    public Dept(String no, String name, String loc) {
        this.no = no;
        this.name = name;
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dept)) return false;
        Dept dept = (Dept) o;
        return Objects.equals(getNo(), dept.getNo()) && Objects.equals(getName(), dept.getName()) && Objects.equals(getLoc(), dept.getLoc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNo(), getName(), getLoc());
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
