package me.javacore;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

    private int capacity;
    private AtomicInteger size = new AtomicInteger();
    private Deque<T> queue = new LinkedList<>();

    final ReentrantLock takeLock = new ReentrantLock();
    final Condition notEmpty = takeLock.newCondition();

    final ReentrantLock putLock = new ReentrantLock();
    final Condition notFull = putLock.newCondition();


    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public T take() throws InterruptedException {
        this.takeLock.lockInterruptibly();
        int c;
        T t;
        try {
            while (this.size.get() == 0) {
                this.notEmpty.await();
            }
            t = this.queue.pollLast();
            c = this.size.getAndDecrement();
            System.out.println("dequeued:" + t);
            if (c > 1) {
                this.notEmpty.signal();
            }
        } finally {
            this.takeLock.unlock();
        }
        if (c == this.capacity) {
            signalNotFull();
        }
        return t;
    }

    public void put(T t) throws InterruptedException {
        this.putLock.lockInterruptibly();
        int c;
        try {
            while (this.size.get() == this.capacity) {
                this.notFull.await();
            }
            this.queue.offerFirst(t);
            c = this.size.getAndIncrement();
            System.out.println("enqueued:" + t);
            if (c + 1 < capacity) {
                this.notFull.signal();
            }
        } finally {
            this.putLock.unlock();
        }
        if (c == 0) {
            signalNotEmpty();
        }
    }

    private void signalNotEmpty() {
        this.takeLock.lock();
        try {
            this.notEmpty.signal();
        } finally {
            this.takeLock.unlock();
        }
    }

    private void signalNotFull() {
        this.putLock.lock();
        try {
            this.notFull.signal();
        } finally {
            this.putLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(3);
        new Thread(() -> {
            try {
                queue.put(1);
                queue.put(2);
                queue.put(3);
                queue.put(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            try {
                queue.take();
                Thread.sleep(200);
                queue.take();
                queue.take();
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
