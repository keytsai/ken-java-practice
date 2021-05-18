package com.ckmates.java.practice2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {

  @GetMapping
  String index() {
    return "redirect:/memos";
  }
}
