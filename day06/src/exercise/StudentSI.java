package exercise;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author MikeCoder
 * @create 2022-10-2917:00
 */
public class StudentSI {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        boolean b1 = set.add(new Student("Tom",69,1));
        boolean b2 = set.add(new Student("Jeryy",86,2));
        boolean b3 = set.add(new Student("Jack",98,3));
        boolean b4 = set.add(new Student("Mike",54,4));
        boolean b5 = set.add(new Student("Rose",78,5));
        Iterator iterator = set.iterator();
        for(int i = 0; i < 3;i++){
            System.out.println(iterator.next());
        }
    }


}
class Student implements Comparable{
    private String name;
    private double score;
    private int id;

    public Student() {
    }

    public Student(String name, double score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (Double.compare(student.score, score) != 0) return false;
        if (id != student.id) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + id;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Student){
            Student o1 = (Student) o;
            return -Double.compare(this.score, o1.score);
        }
           else
               throw new RuntimeException("输入的信息不匹配");
    }
}
