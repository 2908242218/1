package exercise1;

import java.util.Scanner;

/**回形数练习，用户输入一个n，然后打印出n*n的回形数。第一种思路：使用一行一列剥离的方式
 * 每填充完一行或一列，就使用++或--的方式将其剥离出去。
 * @author MikeCoder
 * @create 2022-10-158:22
 */
public class Huixingshu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];
        int up = 0;
        int down = n-1;
        int left = 0;
        int right = n-1;
        int value = 1;
        while(true) {
            //填充上行数组
            for (int i = left; i < right + 1; i++) {
                arr[up][i] = value;
                value++;
            }
            up++;//3
            if (up > down) {
                break;
            }
            //填充右列数组
            for (int i = up; i < down+1; i++) {
                arr[i][right] = value;
                value++;
            }
            right--;
            if(right < left){
                break;
            }
            //填充下行数组
            for(int i = right;i >= left;i--){
                arr[down][i] = value;
                value++;
            }
            down--;
            if(down < up){
                break;
            }
            //填充左行数组
            for(int i = down;i >= up;i--){
                arr[i][left] = value;
                value++;
            }
            left++;
            if(left > right){
                break;
            }

        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
