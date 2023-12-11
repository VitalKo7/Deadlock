public class DeadLock_Demo {
    private static final String monitorA = "A";
    private static final String monitorB = "B";

    public static void main(String[] args) {
        new DeadLock_Demo().deadLock();
    }

    private void deadLock() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitorA) {
                    System.out.println("Thread 1: holds Resource #1");

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1: waits Resource #2 to be free...");

                    synchronized (monitorB) {
                        System.out.println("Thread 1: holds Resource #2");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitorB) {
                    System.out.println("Thread 2: holds Resource #2");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 2: waits Resource #1 to be free...");

                    synchronized (monitorA) {
                        System.out.println("Thread 2: holds Resource #1");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}