package com.erc.app;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {
	public static void main(String[] args) throws IOException, TimeoutException {


		ConnectionFactory factory = new ConnectionFactory();

		
		Connection connection = factory.newConnection();
		com.rabbitmq.client.Channel channel = connection.createChannel();
		channel.queueDeclare("Hello", false, false, false, null);

		System.out.println("Consumer2 waiting a message...");

		channel.basicConsume("Hello", true, (consumerTag, message) -> {

			String m = new String(message.getBody(), "UTF-8");
			System.out.println("Consumer2  just received a message = " +m);
		}, consumerTag -> {	});

	}
	
}
