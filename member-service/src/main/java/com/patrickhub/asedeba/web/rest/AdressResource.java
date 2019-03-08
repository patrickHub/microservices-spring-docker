package com.patrickhub.asedeba.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrickhub.asedeba.domain.Adress;
import com.patrickhub.asedeba.service.AdressService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;

/**
 * Controller for managing Rest API requests on adress domain
 * @author PatrickHub
 *
 */

@RestController
@RequestMapping("/api")
public class AdressResource {
	
	private final Logger LOG = LoggerFactory.getLogger(AdressResource.class);
	private AdressService adressService;
	public final String ENTITY_NAME = "member-service.Adress";
	
	public AdressResource(AdressService adressService) {
		super();
		this.adressService = adressService;
	}
	
	/**
	 * POST /adress : Create a new adress'member
	 * 
	 * @param adress the adress to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new adress, or with status 400 (Bad Request) if adress has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/adress")
	public ResponseEntity<Adress> createAdress(@Valid @RequestBody Adress adress) throws URISyntaxException{
		
		LOG.debug("REST request to save an Adress: { }", adress);
		if(adress.getId() != null) {
			throw new BadRequestAlertException("A new adress cannot already have an id", ENTITY_NAME, "idexists");
		}
		Adress result = adressService.save(adress);
		return ResponseEntity.created(new URI("/api/adress/" + result.getId()))
								.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
	}
	
	/**
	 * PUT /adress :  update an existing adress
	 * @param adress adress to update
	 * @return ResponseEntity with status 200 and body the updated adress
	 * or with status 400 (Bad Request) if the adress is not valid
	 * or with status 500 (Internal Server Error) is the adress could not be update
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/adress")
	public ResponseEntity<Adress> updateAdress(@Valid @RequestBody Adress adress) throws URISyntaxException{
		
		LOG.debug("REST request to update an Adress : { }", adress);
		
		if(adress.getId() == null) {
			throw new BadRequestAlertException("invalid id", ENTITY_NAME, "idnull");
		}
		
		Adress result = adressService.save(adress);
		return ResponseEntity.ok()
								.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
				
	}
	
	
	/**
	 * GET /adress : get all the adresses .
	 * 
	 * @param pageable the pagination information
	 * @return ResponseEntity with status 200 (OK) and the list of adresses in boby
	 */
	@GetMapping("/adress")
	public ResponseEntity<List<Adress>> getAllAdresses(Pageable pageable){
		
		LOG.debug("REST request to get a page of adresses");
		Page<Adress> result = adressService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "api/adress");
		return ResponseEntity.ok().headers(headers).body(result.getContent());
	}
	
	
	/**
	 * GET /adress/:id : get an "id" adress
	 * @param id id of the adress to retrieve
	 * @return ResponseEntity with status 200 (OK) and body the adress or with status 404 (Not Found) if the id is not valid
	 */
	@GetMapping("/adress/{id}")
	ResponseEntity<Adress> getAdress(@PathVariable Long id){
		
		LOG.debug("REST request to get an adress : { }", id);
		Optional<Adress> result = adressService.findOne(id);
		return ResponseUtil.wrapOrNotFound(result);
	}
	
	
	/**
	 * DELETE /adress/:id : delete an "id" adress
	 * @param id id of the adress to delete
	 * @return ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/adress/{id}")
	ResponseEntity<Void> deleteAdress(@PathVariable Long id){
		
		LOG.debug("REST request to delete an adress : { }", id);
		adressService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
	
}
