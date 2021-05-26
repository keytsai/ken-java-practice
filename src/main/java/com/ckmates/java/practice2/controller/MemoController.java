package com.ckmates.java.practice2.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.service.MemoService;

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

  @GetMapping("/filter")
  public String indexFilter(Model model) {
    List<MemoImpl> memoList = memoService.getAllMemo();
    model.addAttribute("memoList", memoList);
    model.addAttribute("memoImpl", new MemoImpl());
    return "memo-filter";
  }

  @GetMapping("/filtered")
  public @ResponseBody String filterCondition(Model model,
      @RequestParam("filterCondition") Set<String> labels) {
    Set<MemoImpl> memoList = memoService.findByLabeIsIn(labels);
    model.addAttribute("memoList", memoList);
    model.addAttribute("memoImpl", new MemoImpl());
    System.out.println("memoList=" + memoList);
    return "memo-filter";
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
