package src.zzq.jvm.no2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 直接内存 oom
 * 直接内存，独立于运行时数据区之外，是 nio 使用的
 * DirectMemory 容量可以通过 -XX:MaxDirectMemorySize 指定，如果不指定，默认与Java堆最大值(-Xmx指定)一样
 * -Xmx10m -XX:MaxDirectMemorySize=10m
 *
 * 由DirectMemory 导致的oom 一个明显的特征就是在 Heap Dump 文件中看不到明显的异常 ，而且 dump 文件还非常小
 */
public class DirectMemoryOOM {
    private static final int _1m = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true){
            unsafe.allocateMemory(_1m);
        }
    }
}
