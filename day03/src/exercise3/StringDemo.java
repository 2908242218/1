package exercise3;

import org.junit.Test;

/**
 * @author MikeCoder
 * @create 2022-10-1716:55
 */
public class StringDemo {
    /*
    将一个字符串进行反转，将字符串中指定部分进行反转。
     */
    //方法一
    public String reverse(String str,int startIndex,int endIndex){
        if(str != null) {
            char[] arr = str.toCharArray();
            for (int x = startIndex, y = endIndex; x < y; x++, y--) {
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
            return new String(arr);
        }
        return null;
    }
    //方法二,使用String拼接
    public String reverse1(String str,int startIndex,int endIndex){
        String s = str.substring(0,startIndex);
        for(int i = endIndex;i >= startIndex;i--){
            s+=str.charAt(i);
        }
        s+=str.substring(endIndex+1);
        return s;
    }
    //方法三，使用StringBuffer/StringBuilder替换String
    public String reverse2(String str,int startIndex,int endIndex) {
        StringBuilder builder = new StringBuilder(str.length());
        builder.append(str.substring(0,startIndex));
        for(int i = endIndex;i >= startIndex;i--){
            builder.append(str.charAt(i));
        }
        builder.append(str.substring(endIndex+1));
        return new String(builder);
    }
    @Test
    public void testReverse(){
        String str =  "abcdefg";
        String reverse = reverse(str,2,5);
        System.out.println(reverse);
        String reverse1 = reverse1(str,2,5);
        System.out.println(reverse1);
        String reverse2 = reverse2(str,2,5);
        System.out.println(reverse2);
    }


}
