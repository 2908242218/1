package review1;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author MikeCoder
 * @create 2023-02-0317:39
 * @description:review
 * @verson:
 */
public class reflectionTest {
    @Test
     public void test1() throws Exception{
      //利用反射调用构造器，创建对象
      Class clazz = Person.class;
      Constructor con = clazz.getDeclaredConstructor(String.class, int.class);
        Object o = con.newInstance("Tom", 20);
        Person p = (Person)o;
      //通过反射调用对象的内部public属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,23);
        System.out.println(p);
      //通过反射调用对象的public方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);
    }
    //通过反射调用对象的私有结构
    @Test
    public void test2() throws Exception{
        //调用私有构造器创建对象
        Class clazz = Person.class;
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Object o = cons1.newInstance("Totto");
        Person p = (Person)o;
        System.out.println(p);
        //调用私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p,"huangxiaoming");
        System.out.println(p.toString());
        //调用私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p,"中国");
        System.out.println(nation);


    }
    //运用反射，体现反射动态性
    @Test
    public void test3(){
        for(int j = 0;j<100;j++){
            int i = new Random().nextInt(3);
            String classPath = "";
            switch(i){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "review1.Person";
                    break;
            }
            Object instance = null;
            try {
                instance = getInstance(classPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(instance);
        }
        }

    public Object getInstance(String classPath) throws Exception{
        Class<?> clazz = Class.forName(classPath);
        Object o = clazz.newInstance();
        return o;
    }



}
