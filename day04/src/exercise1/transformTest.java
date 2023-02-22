package exercise1;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;

/**将字符串“2017-08-16”转换为对应的java.sql.Date类的对象
 * @author MikeCoder
 * @create 2022-10-236:19
 */
public class transformTest {
    @Test
    public void transform() throws ParseException {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
        Date parse = simpleDateFormat.parse("2017-08-16,05:45:32");
        java.sql.Date date1 = new java.sql.Date(parse.getTime());
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        System.out.println(parse);
        System.out.println(date1);
        //使用DateTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,hh:mm:ss");
        TemporalAccessor parse1 = dateTimeFormatter.parse("2017-08-16,08:16:45");
        System.out.println(parse1);

    }
    @Test
    public void testPerson(){
        Person[] P = new Person[5];
        P[0] = new Person("liuwei",23);
        P[1]= new Person("wangzhihao",26);
        P[2] = new Person("maokuangcheng",22);
        P[3] = new Person("wuzhihao",24);
        P[4] = new Person("lifabing",21);
        Arrays.sort(P);
        System.out.println(Arrays.toString(P));

    }

}
class Person implements Comparable{
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            Person p = (Person)o;
            if(this.age < p.age){
                return 1;
            }else if(this.age > p.age){
                return -1;
            }else{
                return 0;
            }
        }
        throw new RuntimeException("输入的类型不符合");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
