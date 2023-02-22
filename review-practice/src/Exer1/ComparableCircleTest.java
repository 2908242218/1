package Exer1;

/**
 * @author MikeCoder
 * @create 2023-01-1222:24
 */
public class ComparableCircleTest {
    public static void main(String[] args) {
        ComparableCircle circle = new ComparableCircle(56);
        ComparableCircle circle1 = new ComparableCircle(21);
        if(circle.compareTo(circle1) < 0){
            System.out.println("circle的半径小于circle1的半径");
        }
        else if(circle.compareTo(circle1) > 0){
            System.out.println("circle的半径大于circle1的半径");
        }else{
            System.out.println("circle的半径等于circle1的半径");
        }
    }

}
