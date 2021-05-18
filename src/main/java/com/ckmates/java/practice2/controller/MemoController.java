package com.ckmates.java.practice2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.service.MemoService;

@Controller
public class MemoController {

  @Autowired
  private MemoService memoService;

  @GetMapping("/memos")
  public String index(Model model) {
    List<MemoImpl> memos = memoService.getAllMemo();
    model.addAttribute("memos", memos);
    model.addAttribute("memoImpl", new MemoImpl());
    return "memo-index";
  }

  @GetMapping("/memos/{id}")
  public String getMemo(@PathVariable Long id, Model model) {
    Memo memoImpl = memoService.findById(id);
    List<MemoImpl> memos = memoService.getAllMemo();
    model.addAttribute("memos", memos);
    model.addAttribute("memoImpl", memoImpl);
    return "memo-details";
  }

  @PostMapping("/memos")
  public String save(@ModelAttribute MemoImpl memoImpl) {
    memoService.save(memoImpl);
    return "redirect:/memos";
  }

  @PutMapping("/memos")
  public String update(@ModelAttribute MemoImpl memoImpl) {
    memoService.update(memoImpl);
    return "redirect:/memos";
  }

  @DeleteMapping("/memos/{id}")
  public String deleteEmployee(@PathVariable Long id) {
    memoService.delete(id);
    return "redirect:/memos";
  }

}
