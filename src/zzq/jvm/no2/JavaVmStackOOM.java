package src.zzq.jvm.no2;

/**
 * -Xss1024m 设置虚拟机栈内存为 1024m
 *  理论上来说，堆栈内存设置的越大，所能产生的线程就越少
 */
public class JavaVmStackOOM {

    private void dontStop(){
        while (true){}
    }

    private void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVmStackOOM oom = new JavaVmStackOOM();
        oom.stackLeakByThread();
    }

}
