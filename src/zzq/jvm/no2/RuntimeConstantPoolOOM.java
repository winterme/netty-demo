package src.zzq.jvm.no2;

import java.util.ArrayList;

/**
 * 运行时常量池 oom ,该区属于方法区，jdk1.6之前属于永久区，在jdk1.7之后开始去永久代，所以该代码在 1.7之后就不会达到预期的效果了
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 * jdk1.8 之后该区域属于堆，所以改变该区域大小通过改变堆就可以达到
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add( String.valueOf(i+++"========================================================================================================================================================================================================================================================================================================================================================================================================================>").intern() );
        }

//        String str1 = new StringBuffer("计算机").append("软件").toString();
//        System.out.println( str1.intern() == str1 );
//
//        String str2 = new StringBuffer("ja").append("va").toString();
//        System.out.println( str1.intern() == str1 );



    }

}
