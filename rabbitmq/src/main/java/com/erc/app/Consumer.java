package com.erc.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class Consumer {
	public static void main(String[] args) throws IOException, TimeoutException {


		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		com.rabbitmq.client.Channel channel = connection.createChannel();

		channel.queueDeclare("Hello", false, false, false, null);


		System.out.println("Consumer1 waiting a message...");

		channel.basicConsume("Hello", true, (consumerTag, message) -> {

			String m = new String(message.getBody(), "UTF-8");
			System.out.println("Consumer1  just received a message = " +m);
		}, consumerTag -> {	});

	}
	
}
