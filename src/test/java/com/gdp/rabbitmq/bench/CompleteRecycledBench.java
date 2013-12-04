package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.consumer.GeneralConsumer;
import com.gdp.rabbitmq.task.publsher.CompleteRecycledPublisher;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

/**
 *
 * @author zerosign
 */
public class CompleteRecycledBench {
    
    public CompleteRecycledBench() {
    }
    
    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    @Test
    public void benchPublishRecycledLotOfMessages() throws InterruptedException {
        System.out.println("benchPublishRecycledLotOfMessages");
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int ii = 0; ii < 100; ii++) {
            executor.execute(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        final CompleteRecycledPublisher publisher = new CompleteRecycledPublisher();
                        publisher.setQueueName("TASKS");
                        publisher.prepare();
                        for (long ii = 0; ii < 1000000000L; ii++) {
                            publisher.run();
                            
                            System.out.println("Publishing : " + ii);
                        }
                    } catch (IOException ex) {
                    }
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        
    }
    
    @Test
    public void benchConsumeRecycledLotOfMessages() throws InterruptedException {
        System.out.println("benchConsumeRecycledLotOfMessages");
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int ii = 0; ii < 100; ii++) {
            final int THREAD_NUMBER = ii;
            executor.execute(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        final GeneralConsumer consumer = new GeneralConsumer();
                        consumer.setAutoAck(false);
                        consumer.setQueueName("TASKS");
                        consumer.prepare();
                        for (long ii = 0; ii < 100000L; ii++) {
                            consumer.run();
                            System.out.println("Consuming : " + ii + " in " + THREAD_NUMBER);
                        }
                    } catch (IOException ex) {
                    }
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }
    
}
