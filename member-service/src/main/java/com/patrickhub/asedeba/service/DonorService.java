package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.Donor;

/**
 * Service interface to manage donors
 * @author PatrickHub
 *
 */
public interface DonorService {
	
	/**
	 * save a new donor .
	 * 
	 * @param donor the entity to save
	 * @return persisted entity
	 */
	Donor save(Donor donor);
	
	
	/**
	 * get all donors .
	 * 
	 * @param pageable the pagination information
	 * @return the list of the entity
	 */
	Page<Donor> findAll(Pageable pageable);
	
	
	/**
	 * get the "id" donor .
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<Donor> findOne(Long id);
	
	
	/**
	 * Delete the "id" donor
	 * @param id id of the entity
	 */
	void delete(Long id);
	
}
