package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.Adress;

/**
 * Service interface for managing adresses
 * @author PatrickHub
 *
 */
public interface AdressService {
	
	/**
	 * save an adress'member .
	 * 
	 * @param adress the entity to save
	 * @return the persisted entity
	 */
	Adress save(Adress adress);
	
	/**
	 * get all adress'members .
	 * 
	 * @param pageable the pagination information
	 * @return the list of the entity
	 */
	Page<Adress> findAll(Pageable pageable);
	
	/**
	 * get the "id" adress .
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<Adress> findOne(Long id);
	
	
	/**
	 * delete the "id" adress .
	 * 
	 * @param id id of the entity
	 */
	void delete(Long id);
}
