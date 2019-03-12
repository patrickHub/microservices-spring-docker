package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.Post;


/**
 * interface service for manage posts;
 * @author PatrickHub
 *
 */
public interface PostService {
	
	/**
	 * save a new post.
	 * 
	 * @param post entity to save
	 * @return persisted entity
	 */
	Post save(Post post);
	
	/**
	 * get all posts.
	 * 
	 * @param pegeable pegeable information
	 * @return list of entities
	 */
	Page<Post> findAll(Pageable pegeable);
	
	
	/**
	 * get an "id" post.
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<Post> findOne(Long id);
	
	
	/**
	 * delete an "id" entity.
	 * 
	 * @param id id if the entity
	 */
	void delete(Long id);
}
