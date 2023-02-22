package exercise2;

/**
 * 模拟一个trim方法,去除字符串两端的空格。
 *
 * @author MikeCoder
 * @create 2022-10-1610:33
 */
public class ImitateTrim {
    public static void main(String[] args) {
        ImitateTrim im = new ImitateTrim();
        String str = im.Imitrim("   liu wei    ");
        System.out.println(str + "d");
        String str1 = im.reverseString("abcdefghigkmn", 2, 11);
        System.out.println(str1);
        int count = im.charNumber("abkkcadkabkebfkabkskab", "ab");
        System.out.println(count);

    }

    /**
     * 模拟一个trim方法，去除字符串两端的空格。
     *
     * @param str
     * @return
     */
    public String Imitrim(String str) {
        char[] ch = str.toCharArray();
        int front = 0;
        int back = ch.length - 1;
        for (int i = 0; i < ch.length; i++) {
            if (' ' != ch[i]) {
                front = i;
                break;
            }
        }
        for (int i = ch.length - 1; i >= 0; i--) {
            if (' ' != ch[i]) {
                back = i;
                break;
            }
        }
        String substring = str.substring(front, back + 1);
        return substring;
    }

    /**
     * 将一个字符串进行反转，将字符串指定部分进行反转
     *
     * @param str
     * @param beginIndex//要反转的字符开始索引
     * @param endIndex//要反转的字符结束索引
     * @return
     */
    public String reverseString(String str, int beginIndex, int endIndex) {
        char[] ch = str.toCharArray();
        char c;
        for (int i = beginIndex; i <= (beginIndex + endIndex) / 2; i++) {
            c = ch[i];
            ch[i] = ch[(endIndex + beginIndex) - i];
            ch[(endIndex + beginIndex) - i] = c;
        }
        return new String(ch);
    }

    /**
     * 获取一个字符串在另一个字符串中出现的次数
     *
     * @param seekString
     * @param needSeekString
     * @return
     */
    public int charNumber(String seekString, String needSeekString) {
        int number = 0;
        char[] c1 = seekString.toCharArray();
        char[] c2 = needSeekString.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            boolean flag = false;
            for (int j = 0; j < c2.length; j++) {
                if (c2[j] != c1[i+j]) {
                    break;
                }
                if (j == c2.length - 1) {
                    flag = true;
                }
            }
            if (flag) {
                number++;
            }
        }
        return number;
    }

    /**
     * 获取两个字符串中最大相同子串
     * @param compareString1
     * @param compareString2
     * @return
     */
}

