package com.ckmates.java.practice2.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
@Table(name = "memo")
public class MemoImpl implements Memo {

  public MemoImpl() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String text;
  private LocalDateTime timestamp;

  @ElementCollection
  Set<String> labels = new TreeSet<>();

  boolean archived = false;

}
