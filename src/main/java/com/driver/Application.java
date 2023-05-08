package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

//		User user1 = new User("Rohit","123");
//		User user2 = new User("Mohit","456");
//		User user3 = new User("Rahul","789");
//		WhatsappController whatsappController = new WhatsappController();
//		//System.out.println(whatsappController.createUser("Saloni","147"));
//		List<User> al = new ArrayList<>();
//		al.add(user1);
//		al.add(user2);
//		al.add(user3);
//
//		Group group = whatsappController.createGroup(al);
//		System.out.println(group);
//
//		String content = "Something special";
//		System.out.println(whatsappController.createMessage(content));
//
//		Message message = new Message(1,content);
//
//		//System.out.println(message);
//
//		int ans = whatsappController.sendMessage(message,user1,group);
//		System.out.println(ans);
	}
}
