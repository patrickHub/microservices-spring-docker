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

import com.patrickhub.asedeba.domain.Partner;
import com.patrickhub.asedeba.service.PartnerService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;


/**
 * Controller for managing REST API request on Partner domain
 * @author Patrick-PC
 *
 */
@RestController
@RequestMapping("/api")
public class PartnerResource {
	
	private final Logger LOG = LoggerFactory.getLogger(PartnerResource.class);
	private final String ENTITY_NAME = "member-service.Partner";
	private PartnerService partnerService;
	
	
	public PartnerResource(PartnerService partnerService) {
		super();
		this.partnerService = partnerService;
	}
	
	
	/**
	 * POST /partner create a new Partner.
	 * 
	 * @param partner partner to create
	 * @return ResponseEntity with status 201 (Created) and body the new partner or status 400 (BadRequest) if the the partner already has an ID 
	 * @throws URISyntaxException if the Location URL is incorrect
	 */
	@PostMapping("/partners")
	public ResponseEntity<Partner> create(@Valid @RequestBody Partner partner) throws URISyntaxException{
		
		LOG.debug("REST request to save new Partner: { }", partner);
		if(partner.getId() != null) {
			throw new BadRequestAlertException("a new partner could not already have an id!", ENTITY_NAME, "idexists");
		}
		
		Partner result = partnerService.save(partner);
		return ResponseEntity.created(new URI("/api/partners/" + result.getId()))
					.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
					.body(result);
	}
	
	 /**
	  * PUT /partner: update an existing Partner.
	  * 
	  * @param partner partner to update
	  * @return ResponseEntity with status 200 (OK)and body the updated partner 
	  * or status 400(BadRequest) if the partner is invalid
	  * or with status 500 (Internal Server Error) if the value could not be updated
	  * @throws URISyntaxException if the Location URL is incorrect
	  */
	@PutMapping("/partners")
	public ResponseEntity<Partner> updatePartner(@Valid @RequestBody Partner partner) throws URISyntaxException{
		
		LOG.debug("REST request to update Partner: { }", partner);
		if(partner.getId() == null) {
			throw new BadRequestAlertException("invalid id", ENTITY_NAME, "idnull");
		}
		Partner result = partnerService.save(partner);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	/**
	 * GET /partners: get the list of all Partners.
	 * 
	 * @param pageable the pagination information
	 * @return ResponseEntity with status 200 (OK) and the body the list of partners
	 */
	@GetMapping("/partners")
	public ResponseEntity<List<Partner>> getPartners(Pageable pageable){
		
		LOG.debug("REST request to get all Partner");
		Page<Partner> result = partnerService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/partner");
		return ResponseEntity.ok().headers(headers).body(result.getContent());
		
	}
	
	
	/**
	 * GET /partner/:id get an "id" partner.
	 * 
	 * @param id id of the partner to get
	 * @return ResponseEntity with status 200 (OK) or status 404 (Not Found) if the id do no exist
	 */
	@GetMapping("/partner/{id}")
	public ResponseEntity<Partner> getPartner(@PathVariable Long id){
		
		LOG.debug("REST request to get Partner: { }", id);
		Optional<Partner> result = partnerService.findOne(id);
		return ResponseUtil.wrapOrNotFound(result);
	}
	
	
	/**
	 * DELETE /partners/:id : delete an "id" Partner.
	 * 
	 * @param id id of the partner
	 * @return ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/partners/{id}")
	public ResponseEntity<Void> deletePartner(@PathVariable Long id){
		
		LOG.debug("REST request to delete Partner: { }", id);
		partnerService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
	
}
