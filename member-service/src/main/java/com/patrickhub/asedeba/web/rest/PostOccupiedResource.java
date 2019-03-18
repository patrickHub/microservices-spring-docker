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

import com.patrickhub.asedeba.domain.PostOccupied;
import com.patrickhub.asedeba.service.PostOccupiedService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;

/**
 * Controller for managing Rest API requests on occupied post domain
 * @author PatrickHub
 *
 */
@RestController
@RequestMapping("/api")
public class PostOccupiedResource {
	private final Logger LOG = LoggerFactory.getLogger(AdressResource.class);
	private PostOccupiedService postOccupiedService;
	public final String ENTITY_NAME = "member-service.PostOccupied";
	
	public PostOccupiedResource(PostOccupiedService postOccupiedService) {
		super();
		this.postOccupiedService = postOccupiedService;
	}
	
	/**
	 * POST /postoccupied : Create a new postoccupied
	 * 
	 * @param postoccupied the postoccupied to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new postoccupied, or with status 400 (Bad Request) if postoccupied has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/postoccupied")
	public ResponseEntity<PostOccupied> createPostOccupied(@Valid @RequestBody PostOccupied postOccupied) throws URISyntaxException{
		
		LOG.debug("REST request to save a Postoccupied: { }", postOccupied);
		if(postOccupied.getId() != null) {
			throw new BadRequestAlertException("A new postoccupied cannot already have an id", ENTITY_NAME, "idexists");
		}
		PostOccupied result = postOccupiedService.save(postOccupied);
		return ResponseEntity.created(new URI("/api/postoccupied/" + result.getId()))
								.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
	}
	
	/**
	 * PUT /postoccupied :  update an existing postoccupied
	 * @param postOccupied postoccupied to update
	 * @return ResponseEntity with status 200 and body the updated postoccupied
	 * or with status 400 (Bad Request) if the postoccupied is not valid
	 * or with status 500 (Internal Server Error) is the postoccupied could not be update
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/postoccupied")
	public ResponseEntity<PostOccupied> updatePostOccupied(@Valid @RequestBody PostOccupied postOccupied) throws URISyntaxException{
		
		LOG.debug("REST request to update a postoccupied : { }", postOccupied);
		
		if(postOccupied.getId() == null) {
			throw new BadRequestAlertException("invalid id", ENTITY_NAME, "idnull");
		}
		
		PostOccupied result = postOccupiedService.save(postOccupied);
		return ResponseEntity.ok()
								.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
				
	}
	
	
	/**
	 * GET /postoccupied : get all the postoccupied .
	 * 
	 * @param pageable the pagination information
	 * @return ResponseEntity with status 200 (OK) and the list of postoccupied in boby
	 */
	@GetMapping("/postoccupied")
	public ResponseEntity<List<PostOccupied>> getAllPostOccupied(Pageable pageable){
		
		LOG.debug("REST request to get a page of postoccupied");
		Page<PostOccupied> result = postOccupiedService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "api/postoccupied");
		return ResponseEntity.ok().headers(headers).body(result.getContent());
	}
	
	
	/**
	 * GET /postoccupied/:id : get an "id" postoccupied
	 * @param id id of the postoccupied to retrieve
	 * @return ResponseEntity with status 200 (OK) and body the postoccupied or with status 404 (Not Found) if the id is not valid
	 */
	@GetMapping("/postoccupied/{id}")
	ResponseEntity<PostOccupied> getAdress(@PathVariable Long id){
		
		LOG.debug("REST request to get a postoccupied : { }", id);
		Optional<PostOccupied> result = postOccupiedService.findOne(id);
		return ResponseUtil.wrapOrNotFound(result);
	}
	
	
	/**
	 * DELETE /postoccupied/:id : delete an "id" postoccupied
	 * @param id id of the postoccupied to delete
	 * @return ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/postoccupied/{id}")
	ResponseEntity<Void> deletePostOccupied(@PathVariable Long id){
		
		LOG.debug("REST request to delete a postoccupied : { }", id);
		postOccupiedService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
