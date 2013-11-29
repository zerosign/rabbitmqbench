package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.consumer.SharedConnectionConsumer;
import com.gdp.rabbitmq.task.publsher.SharedConnectionPublisher;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author zerosign
 */
public class SharedConnectionBench {

    final ConnectionFactory factory = new ConnectionFactory();

    public SharedConnectionBench() {
        factory.setHost("127.0.0.1");

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void benchPublishRecycledLotOfMessages() throws InterruptedException, IOException {
        System.out.println("benchPublishRecycledLotOfMessages");
        final Connection connection = factory.newConnection();
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int ii = 0; ii < 100; ii++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        final SharedConnectionPublisher publisher = new SharedConnectionPublisher(connection);
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
    public void benchConsumeRecycledLotOfMessages() throws InterruptedException, IOException {
        System.out.println("benchConsumeRecycledLotOfMessages");
        
        final Connection connection = factory.newConnection();
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int ii = 0; ii < 100; ii++) {
            final int THREAD_NUMBER = ii;
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        final SharedConnectionConsumer consumer = new SharedConnectionConsumer(connection);
                        consumer.setQueueName("TASKS");
                        consumer.prepare();
                        for (long ii = 0; ii < 1000000000L; ii++) {
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
