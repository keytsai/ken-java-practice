package com.ckmates.java.practice2.service;

import java.util.List;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;

public interface MemoService {
	
	List<MemoImpl> getAllMemo();
	
	Memo save(MemoImpl memoImpl);
	
	Memo findById(Long id);
	
	Memo update(MemoImpl memoImpl);
	
	void delete(Long id);

}
