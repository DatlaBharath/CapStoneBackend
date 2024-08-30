package com.livingsync.annoucements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livingsync.annoucements.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long>{
	
}
