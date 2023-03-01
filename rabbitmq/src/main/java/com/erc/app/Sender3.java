package com.erc.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Sender3 {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            Map<String, Object> lngt = new HashMap<String, Object>();
            lngt.put("x-max-length", 5);
            lngt.put("queue-version", 2);
            //lngt.put("max-length-bytes", 5000);


            channel.queueDeclare("temperature", true, false, false, lngt);

            int i = 0;
            int mCount = 1;
            while (i < 10) {


                String message = String.format("{ name:'Hasan', message:%s}", i);
                channel.basicPublish("", "temperature", false, null, message.getBytes());
                i++;
                mCount++;
            }

            connection.close();
            System.out.println("message has been sent");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
