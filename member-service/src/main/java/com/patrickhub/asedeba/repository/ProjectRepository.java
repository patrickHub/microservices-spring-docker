package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.Project;


/**
 * project repository for managing project entity
 * @author PatrickHub
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
