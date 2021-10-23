package com.kjtb.buildlife.Framework.juc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author Dr d
 * @Date 2021-07-11
 **/
public class JucTest {
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        JucTest t = new JucTest();
//        t.unsafeTest();
//        t.testInterrupt();

        t.completablFutureTest();
        System.out.println();
    }

    <T> T forkJoinTask() {

        return null;
    }

    void forkJoinAction() {

    }

    // function style
    void completablFutureTest() throws ExecutionException, InterruptedException, TimeoutException {

        //void accept(T)
        Consumer<Integer> c = (x) -> {
            System.out.println("x = " + x);
        };
        BiConsumer<Integer, Integer> bc = (x, y) -> {
            System.out.println("x,y = " + x + " , " + y);
        };

        // T get();
        Supplier<Integer> supplier = () -> {
            return (int) System.currentTimeMillis();
        };

        // R apply(T)
        Function<Integer, Integer> fn = (x) -> x * 2;
        BiFunction<Integer, Integer, Integer> bf = (x, y) -> x + y;

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(supplier);
        Integer now = cf.get(1, TimeUnit.SECONDS);
        System.out.println("now = " + now);
    }

    // interrupt 不是马上终止，而是一个中断位
    void testInterrupt() {
        Thread thread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                thread.interrupt();
                boolean interrupted = thread.isInterrupted();
                System.out.println("interrupted = " + interrupted);
            }
            System.out.println("i = " + i);

            if (!thread.isInterrupted()) {
                System.out.println("i = " + i);
            }
        }
    }

    private void unsafeTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 990; i++) {
            executorService.execute(() -> {
//                String str = format.format(new Date());
                Date parse = null;
                try {
                    parse = format.parse("2021-07-10");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("parse = " + parse);
            });
        }
        // 用完后要shutdown 池，释放资源池
        //关闭服务，会先完成已提交的任务，并不再接受新任务。
        executorService.shutdown();
    }


}
