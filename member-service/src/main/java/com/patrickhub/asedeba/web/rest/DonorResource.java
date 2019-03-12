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

import com.patrickhub.asedeba.domain.Donor;
import com.patrickhub.asedeba.service.DonorService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;


/**
 * Controller for managing Rest API request on Donor domain
 * @author PatrickHub
 *
 */
@RestController
@RequestMapping("/api")
public class DonorResource {
	
	
	private final Logger LOG = LoggerFactory.getLogger(DonorResource.class);
	private final String ENTITY_NAME = "member-service.Donor";
	private DonorService donorService;
	
	
	public DonorResource(DonorService donorService) {
		super();
		this.donorService = donorService;
	}
	
	
	/**
	 * POST /donors create a new donor
	 * 
	 * @param donor donor to save
	 * @return ResponseEntity with status 201 (Created) and with body the new donor or 400 (BadRequest) if donor has already an ID 
	 * @throws URISyntaxException if the location URI syntax is incorrect
	 */
	@PostMapping("/donors")
	public ResponseEntity<Donor> createDonor(@Valid @RequestBody Donor donor) throws URISyntaxException{
		
		LOG.debug("REST request to save new Donor: { }", donor);
		if(donor.getId() != null) {
			throw new BadRequestAlertException("a new donor can not already have an id", ENTITY_NAME, "idexists");
		}
		Donor result = donorService.save(donor);
		return ResponseEntity.created(new URI("/api/donors/" + result.getId()))
								.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
		
	}	
	
	
	/**
	 * PUT /donors : update an existing donor
	 * 
	 * @param donor donor to udpate
	 * @return ResponseEntity with status 200 (OK) and body the updated donor 
	 * or with status 400 (BadRequest) if the donor is invalid
	 * or with status 500 (Internal Server Error) if the value could not be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/donors")
	public ResponseEntity<Donor> updateDonor(@Valid @RequestBody Donor donor) throws URISyntaxException{
		
		LOG.debug("REST request to update Donor: { }", donor);
		if(donor.getId() == null) {
			throw new BadRequestAlertException("invalid id", ENTITY_NAME, "idnull");
		}
		Donor result = donorService.save(donor);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
									.body(result);
	}
	
	
	/**
	 * GET /donors:  get list of all donors
	 * 
	 * @param pageable pagination informations
	 * @return ResponseEntity with status 200 (OK) and the list of donors in body
	 */
	@GetMapping("/donors")
	public ResponseEntity<List<Donor>> getAllDonors(Pageable pageable){
		
		LOG.debug("REST request to get list of donor");
		Page<Donor> result = donorService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "api/donors");
		return ResponseEntity.ok().headers(headers).body(result.getContent());
	}
	
	
	/**
	 * GET /donors/:id : get an "id" donor.
	 * 
	 * @param id id of the donor to get
	 * @return ResponseEntity with status 200 (OK) or with status 404 (Not Found) if the id is not valid
	 */
	@GetMapping("/donors/{id}")
	public ResponseEntity<Donor> getDonor(@PathVariable Long id) {
		
		LOG.debug("REST request to get donor: { }", id);
		Optional<Donor> result = donorService.findOne(id);
		return ResponseUtil.wrapOrNotFound(result);	
	}
	
	
	/**
	 * DELETE /donor/:id delete "id" donor
	 * 
	 * @param id id of the donor
	 * @return ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/donors/{id}")
	public ResponseEntity<Void> deleteDonor(@PathVariable Long id){
		
		LOG.debug("REST request to delete Donor:{ }", id);
		donorService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
	
	

}
