package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.Partner;


/**
 * service interface to manage partners
 * @author PatrickHub
 *
 */
public interface PartnerService {
	
	/**
	 * save a new partner.
	 * 
	 * @param partner the entity to save
	 * @return persisted entity
	 */
	Partner save(Partner partner);
	
	/**
	 * Get all partners.
	 * 
	 * @param pageable pagination information
	 * @return the list of the entity
	 */
	Page<Partner> findAll(Pageable pageable);
	
	/**
	 * get the "id" partner.
	 * 
	 * @param id id of the entity
	 * @return the entity
	 */
	Optional<Partner> findOne(Long id);
	
	
	/**
	 * delete the "id" partner.
	 * 
	 * @param id id of the entity
	 */
	void delete(Long id);
	

}
