package com.livingsync.annoucements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livingsync.annoucements.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{

}
