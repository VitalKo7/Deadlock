public class Deadlock_Example {
    public static void main(String[] args) {
        final Object monitor1 = new Object();
        final Object monitor2 = new Object();

        // Thread 1
        Thread thread1 = new Thread(() -> {
            synchronized (monitor1) {
                System.out.println("Thread 1: keeps Resource1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: waits Resource2");

                synchronized (monitor2) {
                    System.out.println("Thread 1: keeps Resource2");
                }
            }
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            synchronized (monitor2) {
                System.out.println("Thread 2: keeps Resource2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: waits Resource1");

                synchronized (monitor1) {
                    System.out.println("Thread 2: keeps Resource1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

//  два Thread'а блокируются, ждущие доступа к ресурсам Resource1 и Resource2.

//  Thread 1 блокирует Resource1, а затем пытается получить доступ к Resource2.
//  Thread 2 блокирует Resource2, а затем пытается получить доступ к Resource1.

//  Оба Threadа будут ожидать друг друга для освобождения ресурсов, что приведет к взаимной блокировке (deadlock).