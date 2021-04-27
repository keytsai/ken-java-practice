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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("header", "Memos");
		model.addAttribute("isAdd", true);
		
		return "index";
	}
	
	@PostMapping(value = "/")
	public String save(@ModelAttribute MemoImpl memoImpl, RedirectAttributes redirectAttributes, Model model) {
		Memo dbMemoImpl = memoService.save(memoImpl);
		
		if(dbMemoImpl != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Memo is saved successfully");
            return "redirect:/memo";
		} else {
			model.addAttribute("errormessage", "Memo is not save, Please try again");
            model.addAttribute("memoImpl", memoImpl);
            return "index";
		}
	}
	
	@GetMapping(value = "/{id}")
	public String getMemo(@PathVariable Long id, Model model) {
		Memo memoImpl = memoService.findById(id);
		List<MemoImpl> memos = memoService.getAllMemo();
		model.addAttribute("memos", memos);
		model.addAttribute("memoImpl", memoImpl);
		model.addAttribute("header", "Edit Memos");
		model.addAttribute("isAdd", false);
		return "index";
	}
	
	@PutMapping(value = "/")
	public String update(@ModelAttribute MemoImpl memoImpl, RedirectAttributes redirectAttributes, Model model) {
		Memo dbMemoImpl = memoService.update(memoImpl);
		
		if(dbMemoImpl != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Memo is update successfully");
            return "redirect:/memo";
		} else {
			model.addAttribute("errormessage", "Memo is not update, Please try again");
            model.addAttribute("memoImpl", memoImpl);
            return "index";
		}
	}
	
    @DeleteMapping(value = "/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    	memoService.delete(id);
        redirectAttributes.addFlashAttribute("successmessage", "Memo is Deleted successfully");
        return "redirect:/memo";
    }

}
