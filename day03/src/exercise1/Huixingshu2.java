package exercise1;

import java.util.Scanner;

/**1.思路1：剥离，每填完一行或者一列，就把这一行或这一列剥离出去。
 * @author MikeCoder
 * @create 2022-10-1522:44
 */
public class Huixingshu2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];
        int value = 1;
        int count = (n/2 == 0)?n/2:(n+1)/2;
        for(int i = 0;i < count;i++){
            //上行填充
            for(int top = i;top < n-i ;top++){
                arr[i][top] = value;
                value++;
            }
            //右列填充
            for(int right = i+1;right < n-i;right++){
                arr[right][n-i-1] = value;
                value++;
            }
            //下行填充
            for(int down = n-i-1;down >= i;down--){
                arr[n-i-1][down] = value;
                value++;
            }
            //左列填充
            for(int left = n-i-1;left > i;left--){
                arr[left][i] = value;
                value++;
            }
        }
        for(int a = 0;a < arr.length;a++){
            for(int b = 0;b < arr[a].length;b++){
                System.out.print(arr[a][b]+"\t");
            }
            System.out.println();
        }

    }

}
