package bean;

import java.util.Objects;

public class Student {
    private int no = -1;
    private String name = null;


    public Student(int no, String name) {
        this.no = no;
        this.name = name;
    }



    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals方法被执行了");
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getNo() == student.getNo() && Objects.equals(getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNo(), getName());
    }

    public Student() {
    }
}
