package review1;

/**
 * @author MikeCoder
 * @create 2023-01-1111:36
 */
public  class JunitTest {
    public static void main(String[] args) {
        junit j = new junit();
        j.walk();
        int i = j.show3();
        System.out.println(i);
    }


}
abstract class Junit {
    public  int show3(){
        return 10;
    }
    public abstract void walk();
}
class junit extends Junit{

    @Override
    public void walk() {
        System.out.println("人走路");
    }
}