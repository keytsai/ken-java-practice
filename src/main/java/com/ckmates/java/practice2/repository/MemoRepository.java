package com.ckmates.java.practice2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ckmates.java.practice2.model.MemoImpl;

public interface MemoRepository extends CrudRepository<MemoImpl, Long> {
	List<MemoImpl> findAll();

	Optional<MemoImpl> findById(Long id);

	void deleteById(Long id);

	@Query(value = "SELECT * FROM MEMO as m WHERE m.ID BETWEEN ?1 AND ?2", nativeQuery = true)
	List<MemoImpl> findByPriorityBetween(Long number1, Long number2);

}
