package main.java.hw.cycle_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

class H2OCyclicBarrier {
    private final CyclicBarrier cb;
    private final Semaphore oxygenSemaphore;
    private final Semaphore hydrogenSemaphore;

    public H2OCyclicBarrier(){
        cb = new CyclicBarrier(3);
        oxygenSemaphore = new Semaphore(1);
        hydrogenSemaphore = new Semaphore(2);
    }

    public void hydrogen(Runnable runHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        try {
            cb.await();
        } catch (BrokenBarrierException e1) {
            e1.printStackTrace();
        }
        runHydrogen.run();
        hydrogenSemaphore.release();

    }

    public void oxygen(Runnable runOxygen) throws InterruptedException {
        oxygenSemaphore.acquire();
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        runOxygen.run();
        oxygenSemaphore.release();
    }
}
