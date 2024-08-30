package com.livingsync.annoucements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livingsync.annoucements.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
