package com.ckmates.java.practice2.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.repository.MemoRepository;

public class MemoServiceImpl implements MemoService {

//	@Autowired
	private MemoRepository memoRepository;

	@Override
	public List<MemoImpl> getAllMemo() {
		return (List<MemoImpl>) memoRepository.findAll();
	}

	@Override
	public Memo save(MemoImpl memoImpl) {
//		memoImpl.setTimestamp(LocalDateTime.now());
		return memoRepository.save(memoImpl);
	}

	@Override
	public Memo findById(Long id) {

		Optional<MemoImpl> memoImpl = memoRepository.findById(id);

		if (memoImpl.isPresent()) {
			return memoImpl.get();
		} else {
			return null;
		}
	}

	@Override
	public Memo update(MemoImpl memoImpl) {
		return memoRepository.save(memoImpl);
	}

	@Override
	public void delete(Long id) {
		memoRepository.deleteById(id);

	}

}