package exercise1;

import java.util.Scanner;

/**用户输入一个n，打印一个n*n的回形数组，思路二：使用声明一个控制方向的变量的方式，控制数组填充方向。(k=1向右；k=2向下；k=3向左；k=4向上。
 *@author MikeCoder
 * @create 2022-10-159:14
 */
public class Huixingshu1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];
        int mindex = 0;
        int maxdex = n-1;
        int max = n*n;

        int k = 1;
        int i = 0;
        int j = 0;
        for(int m = 1;m <= max;m++){
            //向右填充
            if(k == 1){
                if(j < n && arr[i][j] == 0){
                    arr[i][j++] = m;
                }else{
                    k = 2;
                    j--;
                    i++;
                    m--;
                }
            }
            //向下填充
            else if(k == 2){
                if(i < n && arr[i][j] == 0){
                    arr[i++][j] = m;
                }else{
                    k = 3;
                    i--;
                    j--;
                    m--;
                }
            }
            //向左填充
            else if(k == 3){
                if(j >= 0 && arr[i][j] == 0){
                    arr[i][j--] = m;
                }else{
                    k = 4;
                    j++;
                    i--;
                    m--;
                }
            }
            //向上填充
            else if(k == 4){
                if(i >= 0 && arr[i][j] == 0){
                    arr[i--][j] = m;
                }else{
                    k = 1;
                    i++;
                    j++;
                    m--;
                }
            }
        }
        for(int a = 0;a < arr.length;a++){
            for(int b = 0;b < arr[i].length;b++){
                System.out.print(arr[a][b]+"\t");
            }
            System.out.println();
        }
    }
}





