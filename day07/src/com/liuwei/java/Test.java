//package com.liuwei.java;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author MikeCoder
// * @create 2022-10-3115:42
// */
//public class Test {
//    public static void main(String[] args) throws IOException {
//        DAO<User> u = new DAO<>();
//        u.save("1",new User(1,25,"liuwei"));
//        u.save("2",new User(2,23,"Make"));
//        u.save("3",new User(3,45,"liuwei1"));
//        u.save("4",new User(4,36,"liuwei2"));
//        u.save("5",new User(5,19,"liuwei3"));
//        User user = u.get("2");
//        System.out.println(user);
//        u.update("5",new User(6,52,"dashuwangzi"));
//        System.out.println(u.get("5"));
//        System.out.println("******************");
//        List<User> list = u.list();
//        System.out.println(list);
//        u.delete("4");
//        User user1 = u.get("4");
//        System.out.println(user1);
//       // List<User> values = (List<User>)u.m.values();
//       // System.out.println(values);
//        System.out.println();
//        File file = new File("D:\\1\\测试目录");
//        boolean mkdir = file.mkdir();
//        if(mkdir){
//            System.out.println("创建成功");
//        }
//        File file1 = new File(file, "hello.txt");
//        boolean newFile = file1.createNewFile();
//        if(newFile){
//            System.out.println("文件创建成功");
//        }
//        File file2 = new File(file,"子目录");
//        boolean mkdir1 = file2.mkdir();
//        if(mkdir1){
//            System.out.println("创建子目录成功");
//        }
//        File file3 = new File("D:\\360MoveData\\Users\\大树王子\\Desktop\\图片和文档");
//        String[] list1 = file3.list();
//        for(String s : list1){
//            if(s.endsWith(".jpg")){
//                System.out.println(s);
//            }
//        }
//        System.out.println("*********");
//
//    }
//}
