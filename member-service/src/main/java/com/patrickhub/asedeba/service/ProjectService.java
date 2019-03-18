package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.Project;


/**
 * service interface for managing Project
 * @author PatrickHub
 *
 */
public interface ProjectService {
	/**
	 * save an project .
	 * 
	 * @param project the entity to save
	 * @return the persisted entity
	 */
	Project save(Project project);
	
	/**
	 * get all project .
	 * 
	 * @param pageable the pagination information
	 * @return the list of the entity
	 */
	Page<Project> findAll(Pageable pageable);
	
	/**
	 * get the "id" project .
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<Project> findOne(Long id);
	
	
	/**
	 * delete the "id" project .
	 * 
	 * @param id id of the entity
	 */
	void delete(Long id);
}
