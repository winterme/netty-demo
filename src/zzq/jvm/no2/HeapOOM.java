package src.zzq.jvm.no2;

import java.util.ArrayList;

/**
 * -Xms20m 0Xmx20m -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=D:// (指定打印出来的 dump 文件的路径)
 * 设置 最小堆内存为20m，最大也为 20m，打印 oom 时候的内存堆转储快照
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add(new OOMObject());
        }
    }
}
