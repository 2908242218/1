package java1;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author MikeCoder
 * @create 2022-11-039:06
 */
public class CharatcterNumberCounts {
    @Test
    public void test1() throws IOException {
        File file = new File("TestFile");
        FileReader reader = new FileReader(file);
        HashMap<Character, Integer> map = new HashMap<>();
        int len;
        while((len = reader.read()) != -1){
            char ch = (char)len;
            if(map.get(ch) == null){
                map.put(ch,1);
            }else{
                map.put(ch,map.get(ch)+1);
            }
        }
        FileWriter writer = new FileWriter("字数统计");
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        for(Map.Entry<Character,Integer> entry:entrySet){
           writer.write("字符”"+entry.getKey()+"“的个数为"+entry.getValue()+"\n");

        }
        reader.close();
        writer.close();
    }

}
