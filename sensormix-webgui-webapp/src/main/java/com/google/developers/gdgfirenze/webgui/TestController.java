/*
 * Copyright 2013 Thales Italia spa.
 * 
 * This program is not yet licensed and this file may not be used under any
 * circumstance.
 */
package com.google.developers.gdgfirenze.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * The Class HomeController.
 */
@Controller
public class TestController {

  private static final Logger logger = Logger.getLogger(TestController.class.getName());

  @RequestMapping("/test.spring")
  public String testMvc() {
    logger.info("Accessing test MVC page");
    return "test.spring";
  }

  @RequestMapping("/test.jstl.spring")
  public String testMvcJstl() {
    logger.info("Accessing test MVC JSTL page");
    return "test.spring.jstl";
  }
}
