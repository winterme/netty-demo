package src.zzq.jvm.no2;

/**
 * -Xss128k 设置虚拟机栈内存为 128k
 */
public class StackOverflow {

    int stackLength = 0;

    public void stackLeak(){
        this.stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverflow overflow = new StackOverflow();
        try {
            overflow.stackLeak();
        } catch (Throwable t) {
            System.out.println("stack length is " + overflow.stackLength);
            //throw t;
        }
    }

}
