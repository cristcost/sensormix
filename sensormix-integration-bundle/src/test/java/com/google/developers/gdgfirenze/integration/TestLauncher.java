package com.google.developers.gdgfirenze.integration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestLauncher {
  public static void main(String[] args) throws IOException {

    ConfigurableApplicationContext springContext =
        new ClassPathXmlApplicationContext("META-INF/spring/beans-routes.xml",
            "META-INF/spring/beans-test.xml");

    System.out.println("Press ENTER to exit");
    System.in.read();

    System.out.println("exit");
    springContext.close();
  }
}
