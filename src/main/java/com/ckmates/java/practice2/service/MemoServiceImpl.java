package com.ckmates.java.practice2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ckmates.java.practice2.model.Memo;
import com.ckmates.java.practice2.model.MemoImpl;
import com.ckmates.java.practice2.repository.MemoRepository;

@Service
@Transactional
public class MemoServiceImpl implements MemoService {

  @Autowired
  private MemoRepository memoRepository;

  @Override
  public List<MemoImpl> getAllMemo() {
    return memoRepository.findAll();
  }

  @Override
  public Memo save(MemoImpl memoImpl) {
    memoImpl.setTimestamp(LocalDateTime.now());
    return memoRepository.save(memoImpl);
  }

  @Override
  public Memo findById(Long id) {
    Optional<MemoImpl> memoImpl = memoRepository.findById(id);
    return memoImpl.orElse(null);
  }

  @Override
  public Set<MemoImpl> findByLabeIsIn(Set<String> labels) {
    System.out.println("Labels =" + labels);
    Set<MemoImpl> memoImpl = memoRepository.findByLabeIsIn(labels);
    return memoImpl;
  }

  @Override
  public Memo update(MemoImpl memoImpl) {
    memoImpl.setTimestamp(LocalDateTime.now());
    return memoRepository.save(memoImpl);
  }

  @Override
  public void delete(Long id) {
    memoRepository.deleteById(id);

  }

}
