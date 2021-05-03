package com.ckmates.java.practice2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.service.MemoService;

@Controller
public class MemoController {

    @Autowired
    private MemoService memoService;

    @GetMapping("/memo")
    public String index(Model model) {
        List<MemoImpl> memos = memoService.getAllMemo();
        model.addAttribute("memos", memos);
        model.addAttribute("memoImpl", new MemoImpl());
        return "memo-index";
    }

    @GetMapping(value = "/memo/{id}")
    public String getMemo(@PathVariable Long id, Model model) {
        Memo memoImpl = memoService.findById(id);
        List<MemoImpl> memos = memoService.getAllMemo();
        model.addAttribute("memos", memos);
        model.addAttribute("memoImpl", memoImpl);
        return "memo-details";
    }

    @PostMapping(value = "/memo")
    public String save(@ModelAttribute MemoImpl memoImpl) {
        memoService.save(memoImpl);
        return "redirect:/memo";
    }

    @PutMapping(value = "/memo")
    public String update(@ModelAttribute MemoImpl memoImpl) {
        memoService.update(memoImpl);
        return "redirect:/memo";
    }

    @DeleteMapping(value = "/memo/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        memoService.delete(id);
        return "redirect:/memo";
    }

}
