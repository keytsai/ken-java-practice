package com.ckmates.java.practice2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.repository.MemoRepository;


@Controller
public class MemoController {

	@Autowired
	MemoRepository memoRepository;
	
	@GetMapping("/memo")
	public String index(Model model) {
		List<MemoImpl> memos = memoRepository.findAll();
		model.addAttribute("memos", memos);
		model.addAttribute("memoImpl", new MemoImpl());
		model.addAttribute("header", "Memos");
		model.addAttribute("isAdd", true);
		
		return "index";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute MemoImpl memoImpl, RedirectAttributes redirectAttributes, Model model) {
		MemoImpl dbMemoImpl = memoRepository.save(memoImpl);
		
		if(dbMemoImpl != null) {
			redirectAttributes.addFlashAttribute("successmessage", "Memo is saved successfully");
            return "redirect:/memo";
		} else {
			model.addAttribute("errormessage", "Memo is not save, Please try again");
            model.addAttribute("memoImpl", memoImpl);
            return "index";
		}
	}
	
	@GetMapping(value = "/getMemo/{id}")
	public String getMemo(@PathVariable Long id, Model model) {
//		Optional<MemoImpl> memo = memoRepository.findById(id);
		MemoImpl memoImpl = memoRepository.findById(id).get();
		List<MemoImpl> memos = memoRepository.findAll();
		model.addAttribute("memos", memos);
		model.addAttribute("memoImpl", memoImpl);
		model.addAttribute("header", "Edit Memos");
		model.addAttribute("isAdd", false);
		return "index";
	}
	
//	@PostMapping(value = "/update")
//    public String update(@ModelAttribute MemoImpl memoImpl, RedirectAttributes redirectAttributes, Model model) {
////		MemoImpl dbMemoImpl = memoRepository.sa
//
//        if (dbEmployee != null) {
//            redirectAttributes.addFlashAttribute("successmessage", "Employee is update successfully");
//            return "redirect:/todo";
//        } else {
//            model.addAttribute("errormessage", "Employee is not update, Please try again");
//            model.addAttribute("employee", employee);
//            return "view/employees";
//        }
//    }

    @GetMapping(value = "/deleteMemo/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        employeeService.delete(id);
        memoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successmessage", "Memo is Deleted successfully");
        return "redirect:/memo";
    }
    
    
	
//	@GetMapping("/build")
//	public String buildTableAndData() {
//
//		MemoImpl memo = new MemoImpl();
//		memo.setTitle("2號颱風「舒力基」明日離台最近！外圍水氣移入3地區有雨");
//		memo.setText(
//				"今年第2號颱風「舒力基」目前逐漸往北移動，氣象粉專「台灣颱風論壇｜天氣特急」指出，因為颱風外圍水氣移入台灣陸地，迎風面的東半部、基隆、恆春都會出現降雨。而中央氣象局也提醒，全台沿海都要留意長浪，特別是東半部及離島地區，浪高可能超過1層樓。");
//		memo.setLabel("氣象");
//		memo.setArchived(1);
//		memoRepository.save(memo);
//
//		memo = new MemoImpl();
//		memo.setTitle("美國會通過 「無遠弗屆法案」跨黨派對抗中國野心：應定期對台軍售");
//		memo.setText(
//				"為力抗中國的挑戰，美國會聯邦參議院外交委員會21日審查通過「2021年戰略競爭法案」（Strategic Competition Act），反制中國的經濟與影響力行為，包括中國對台野心、壓迫香港與新疆。另外，參議院民主黨領袖舒默（Chuck Schumer）和共和黨參議員楊恩（Todd Young）領銜，21日再度提出「無遠弗屆法案」（Endless Frontiers Act），針對新興科技投資超過1000億美元，包括打造具韌性的供應鏈，用以建立美中公平競爭的環境。");
//		memo.setLabel("國際政治");
//		memo.setArchived(0);
//		memoRepository.save(memo);
//
//		return "index";
//	}
//
//	@GetMapping()
//	public List<MemoImpl> getAll() {
//		return memoRepository.findAll();
//	}
//
//	@GetMapping("/{number1}/{number2}")
//	public List<MemoImpl> getByRange(@PathVariable("number1") Long number1, @PathVariable("number2") Long number2) {
//		return memoRepository.findByPriorityBetween(number1, number2);
//	}
//
//	@GetMapping("/{id}")
//	public Optional<MemoImpl> getById(@PathVariable("id") Long Id) {
//		return memoRepository.findById(Id);
//	}
//
//	@DeleteMapping("/{id}")
//	public String deleteById(@PathVariable("id") Long Id) {
//		memoRepository.deleteById(Id);
//
//		if (!memoRepository.findById(Id).isPresent())
//			return "index";
//
//		return "index";
//	}

}
