package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/**从键盘随机输入10个整数保存到List中，并按倒序、从大到小的顺序显示出来。
 * @author MikeCoder
 * @create 2022-10-2916:38
 */
public class exer1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入十个数字：");
        ArrayList arrayList = new ArrayList();
        for(int i = 0;i < 10;i++){
            int a = scanner.nextInt();
            arrayList.add(a);
        }
        Comparator comparator = new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Integer && o2 instanceof Integer){
                    Integer o11 = (Integer) o1;
                    Integer o21 = (Integer) o2;
                    return -Integer.compare(o11,o21);
                }
                else
                    throw new RuntimeException("输入的信息不匹配");
            }
        };
        arrayList.sort(comparator);
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
