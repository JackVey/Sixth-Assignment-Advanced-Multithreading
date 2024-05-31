package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    private Semaphore semaphore;
    String name;
    public Operator(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.printf("Operator %s accessed to the Resources.%n", name);
            for (int i = 0; i < 10; i++)
            {
                Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("Operator %s released the Resources.%n", name);
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
