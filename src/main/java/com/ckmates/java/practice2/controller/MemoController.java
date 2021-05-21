package com.ckmates.java.practice2.controller;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/memos")
@Controller
public class MemoController {

  @Autowired
  private MemoService memoService;

  @GetMapping
  public String index(Model model) {
    List<MemoImpl> memoList = memoService.getAllMemo();
    model.addAttribute("memoList", memoList);
    model.addAttribute("memoImpl", new MemoImpl());
    return "memo-index";

  }

  @GetMapping("/{id}")
  public String getMemo(@PathVariable Long id, Model model) {
    Memo memoImpl = memoService.findById(id);
    List<MemoImpl> memoList = memoService.getAllMemo();
    model.addAttribute("memoList", memoList);
    model.addAttribute("memoImpl", memoImpl);
    return "memo-details";
  }

  @GetMapping("/archived")
  public String indexArchived(Model model) {
    List<MemoImpl> memoList = memoService.getAllMemo();
    model.addAttribute("memoList", memoList);
    model.addAttribute("memoImpl", new MemoImpl());
    return "memo-archived";
  }

  @PostMapping
  public String save(@ModelAttribute MemoImpl memoImpl) {
    memoService.save(memoImpl);
    return "redirect:/memos";
  }

  @PutMapping
  public String update(@ModelAttribute MemoImpl memoImpl) {
    memoService.update(memoImpl);
    return "redirect:/memos";
  }

  @PutMapping("/archived")
  public String updateArchived(@ModelAttribute MemoImpl memoImpl) {
    memoService.update(memoImpl);
    return "redirect:/memos/archived";
  }

  @DeleteMapping("{id}")
  public String delete(@PathVariable Long id) {
    memoService.delete(id);
    return "redirect:/memos";
  }

  @DeleteMapping("/archived/{id}")
  public String deleteArchived(@PathVariable Long id) {
    memoService.delete(id);
    return "redirect:/memos/archived";
  }

}
