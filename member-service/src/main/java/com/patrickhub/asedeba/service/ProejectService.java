package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.PostOccupied;
import com.patrickhub.asedeba.domain.Project;


/**
 * service interface for managing projects
 * @author PatrickHub
 *
 */
public interface ProejectService {
	/**
	 * save a project.
	 * 
	 * @param postOccupied entity to save
	 * @return the persisted entity
	 */
	PostOccupied save(Project project);
	
	
	/**
	 * get all projects.
	 * 
	 * @param pegeable pegeable information
	 * @return the list of the entity
	 */
	Page<Project> findAll(Pageable pegeable);
	
	
	/**
	 * get an "id" project.
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<Project> findOne(Long id);
	
	/**
	 * delete an "id" project.
	 * 
	 * @param id id of the entity
	 */
	void deleteOne(Long id);
}
