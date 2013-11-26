package com.google.developers.gdgfirenze.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * The Class HomeController.
 */
@Controller
public class AppController {

  private static final Logger logger = Logger.getLogger(AppController.class.getName());

  @RequestMapping("/demo.spring")
  public String demo() {
    logger.info("Accessing test GWT page");
    return "demo";
  }
  
  @RequestMapping("/gwt.spring")
  public String gwt() {
    logger.info("Accessing test GWT page");
    return "gwt";
  }

}
