package com.ckmates.java.practice2.repository;

import com.ckmates.java.practice2.model.MemoImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends CrudRepository<MemoImpl, Long> {
  List<MemoImpl> findAll();

  Optional<MemoImpl> findById(Long id);

  void deleteById(Long id);

}
