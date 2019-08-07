package src.zzq.thread;

import java.util.Date;

public class GetToken {

    private String token;

    private boolean needLock = false;

    public String getToken() {
        if(needLock){
            synchronized (this){
                if (needLock) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.token;
    }

    public  String reflushToken() {
        synchronized (this){
            this.needLock = true;
            try {
                long t = (long) (Math.random() * 5000);
                System.out.println("休眠" + t + "ms" );
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.token = "zzq";
            this.notify();
            this.needLock = false;
        }
        return token;
    }

    public static void main(String[] args) throws InterruptedException {
        GetToken token = new GetToken();

        new Thread("t1") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        new Thread("t3") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        new Thread("t4") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        System.out.println("休眠5000ms");
        Thread.sleep(5000);
        new Thread("t5") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        new Thread("t6") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        new Thread("t7") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
        new Thread("t8") {
            @Override
            public void run() {
                String t = token.getToken();
                if (t == null) {

                    System.out.println(token.reflushToken() + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                } else {
                    System.out.println(t + "===>" + Thread.currentThread().getName() +"nowDate====>" + new Date());
                }
            }
        }.start();
    }


}
