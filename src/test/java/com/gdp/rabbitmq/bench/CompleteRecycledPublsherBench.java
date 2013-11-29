package com.gdp.rabbitmq.bench;

import com.gdp.rabbitmq.task.publsher.CompleteRecycledPublisher;
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
public class CompleteRecycledPublsherBench {

    public CompleteRecycledPublsherBench() {
    }

    @BeforeClass
    protected static void setUpClass() {
    }

    @AfterClass
    protected static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBenchCompleteRecycledPublisher() throws IOException {
        CompleteRecycledPublisher publisher;
        publisher = new CompleteRecycledPublisher();
        publisher.setQueueName("BENCH");
        publisher.prepare();
        for (long ii = 0; ii < 1000000000L; ii++) {
            publisher.run();
        }

        publisher.drop();
    }

}
