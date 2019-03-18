package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.PostOccupied;


/**
 * Service interface for managing post occupied
 * @author PatrickHub
 *
 */
public interface PostOccupiedService {
	
	/**
	 * save an occupied post.
	 * 
	 * @param postOccupied entity to save
	 * @return the persisted entity
	 */
	PostOccupied save(PostOccupied postOccupied);
	
	
	/**
	 * get all occupied post.
	 * 
	 * @param pegeable pegeable information
	 * @return the list of the entity
	 */
	Page<PostOccupied> findAll(Pageable pegeable);
	
	
	/**
	 * get an "id" occcupied post.
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<PostOccupied> findOne(Long id);
	
	/**
	 * delete an "id" occupied post.
	 * 
	 * @param id id of the entity
	 */
	void delete(Long id);
}
