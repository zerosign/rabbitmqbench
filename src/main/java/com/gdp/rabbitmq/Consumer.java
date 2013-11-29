package com.gdp.rabbitmq;

import com.gdp.rabbitmq.objects.Executable;
import com.gdp.rabbitmq.objects.Task;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 *
 * @author zerosign
 */
public abstract class Consumer extends Executable {

    protected Channel channel;
    protected String queueName;
    protected QueueingConsumer consumer;

    public Consumer() {
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
        consumer = new QueueingConsumer(channel);
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void prepare() throws IOException {

        channel.queueDeclare(queueName, true, false, false, null);
        consumer = new QueueingConsumer(channel);  
        channel.basicConsume(queueName, false, consumer);
        
    }

    public Task consume(Task.TaskType type) throws IOException, InterruptedException, ClassNotFoundException {
     
        Delivery delivery = consumer.nextDelivery();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(delivery.getBody());
        ObjectInput reader = new ObjectInputStream(inputStream);

        Task newTask = (Task) reader.readObject();
        reader.close();
        inputStream.close();
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        return newTask;
    }

}
