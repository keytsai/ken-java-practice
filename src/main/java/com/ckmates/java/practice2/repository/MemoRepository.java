package com.ckmates.java.practice2.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ckmates.java.practice2.model.MemoImpl;

public interface MemoRepository extends JpaRepository<MemoImpl, Long> {

  List<MemoImpl> findAll();

  @Query("from MemoImpl t where t.labels =:label")
  Set<MemoImpl> findByLabeIsIn(Set<String> label);

  Optional<MemoImpl> findById(Long id);

  void deleteById(Long id);

}
