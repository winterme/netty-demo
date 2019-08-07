package src.zzq.jvm.no3;

public class ReferenceCountingGC {

    private Object instance = null;

    /**
     * 这两个成员就是用来占内存，方便在gc日志中看清楚是否被回收过
     */
    private static final int _50MB = 1024*1024*50;
    private byte[] bigSize = new byte[2*_50MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这发生 GC， objA 和 objB 是否能被回收？
        System.gc();

    }

}
