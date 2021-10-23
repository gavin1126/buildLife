package com.kjtb.buildlife.Framework.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * consumer-producer 的5种写法
 * await: （Block until signalled or interrupted.）
 * 使当前线程处于wait挂起状态，直到被signal唤醒，或者被中断才会改变状态；
 * signal: 唤醒某个处于await状态的线程，解除阻塞
 * <p>
 * wait/notify
 * await/signal
 * <p>
 * https://www.jianshu.com/p/7cbb6b0bbabc
 * https://juejin.cn/post/6844903486895865864
 *
 * @Author Dr d
 * @Date 2021-07-08
 **/
public class ConsumerProducer {
    final ReentrantLock lock = new ReentrantLock();
    //当pool满时，需要等待的条件是：非满状态
    final Condition notFull = lock.newCondition();
    //当pool空时，需要等待的条件：非空状态
    final Condition notEmpty = lock.newCondition();

    final Object[] objects = new Object[20];
    final int maxCap = 20;
    AtomicInteger putIdx = new AtomicInteger(0);
    AtomicInteger takeIdx = new AtomicInteger(0);

    //v1
    void put(Object obj) throws InterruptedException {
        lock.lock();
        try {
            while (putIdx.get() == maxCap) {
                //已经满了，需要挂起，直到被通知非满状态
                notFull.await();
            }
            objects[putIdx.get()] = obj;
            if (putIdx.get() == maxCap - 1) {
                putIdx.set(0);
            } else {
                putIdx.getAndIncrement();
            }
            //pool里已经有数据，是非空状态，所以通知客户消费，来消费我吧
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    Object take() throws InterruptedException {
        lock.lock();
        try {
            while (objects.length == 0) {
                notEmpty.await();
            }
            Object object = objects[takeIdx.get()];
            if (takeIdx.get() == maxCap - 1) {
                takeIdx.set(0);
            } else {
                takeIdx.getAndIncrement();
            }
            notFull.signal();
            return object;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConsumerProducer t = new ConsumerProducer();

        t.test(t);
    }

    private void test(ConsumerProducer t) throws InterruptedException {
        ExecutorService p = Executors.newFixedThreadPool(10);
        ExecutorService c = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            p.execute(() -> {
                try {
                    Thread thread = Thread.currentThread();
                    t.put(finalI);
                    System.out.println(thread.getName() + " put " + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 30; i++) {
            c.execute(() -> {
                try {
                    Thread thread = Thread.currentThread();
                    Object take = t.take();
                    System.out.println(thread.getName() + " took " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println();
    }

}
