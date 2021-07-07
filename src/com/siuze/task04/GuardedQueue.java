package com.siuze.task04;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GuardedQueue {
    private final Queue<Integer> sourceList;

    public GuardedQueue() {
        this.sourceList = new LinkedBlockingQueue<>();
    }

    public synchronized Integer get() {
        while (sourceList.isEmpty()) {
            try {
                wait();    // <--- 如果队列为null，等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sourceList.peek();
    }

    public synchronized void put(Integer e) {
        sourceList.add(e);
        notifyAll();  //<--- 通知，继续执行    }
    }

    public static void main(String[] args) throws InterruptedException {
        GuardedQueue guardedQueue = new GuardedQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
                    guardedQueue.get();
                }
        );
        Thread.sleep(2000);
        executorService.execute(() -> {
                    guardedQueue.put(20);
                }
        );
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }

}