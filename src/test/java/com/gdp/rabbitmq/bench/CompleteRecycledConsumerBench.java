package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.consumer.GeneralConsumer;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author zerosign
 */
public class CompleteRecycledConsumerBench {


    public CompleteRecycledConsumerBench() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp()  {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBenchCompleteRecycledConsumer() throws IOException {
        
        GeneralConsumer consumer;
        consumer = new GeneralConsumer();
        consumer.setQueueName("BENCH");
        consumer.prepare();
        for(long ii = 0; ii < 100000000L; ii++) {
            consumer.run();
        }
        
    }

}
