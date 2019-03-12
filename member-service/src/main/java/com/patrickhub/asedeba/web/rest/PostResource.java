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

import com.patrickhub.asedeba.domain.Post;
import com.patrickhub.asedeba.service.PostService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;

/**
 * class controller to managing REST API request on Post domain
 * @author PatrickHub
 *
 */

@RestController
@RequestMapping("/api")
public class PostResource {
	
	private final Logger LOG = LoggerFactory.getLogger(PostResource.class);
	private final String ENTITY_NAME = "member-service.Post";
	private PostService postService;
	
	
	public PostResource(PostService postService) {
		super();
		this.postService = postService;
	}
	
	
	/**
	 * POST /posts create a new Post.
	 * 
	 * @param post post to create
	 * @return ResponseEntity with status 201 (Created) and body the created post or with status 400 (Bad Request) if the post already has an ID
	 * @throws URISyntaxException if Location URI is incorrect
	 */
	@PostMapping("/posts")
	public ResponseEntity<Post> save(@Valid @RequestBody Post post) throws URISyntaxException{
		
		LOG.debug("REST request to create new Post: { }", post);
		
		if(post.getId() != null) {
			throw new BadRequestAlertException("a new post can not already has an id", ENTITY_NAME, "idexists");
		}
		Post result = postService.save(post);
		return ResponseEntity.created(new URI("/api/posts/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	/**
	 * PUT /post update existing Post.
	 * 
	 * @param post post to update
	 * @return ResponseEntity with status 200 (OK) and body the updated post
	 * or with status 400 (Bad Request) if the post is invalid
	 * or with status 500 (Internal Server Error) if the post could not be updated
	 * @throws URISyntaxException if the Location URI is incorrect
	 */
	@PutMapping("/posts")
	public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) throws URISyntaxException{
		
		LOG.debug("REST request to update Post: { }", post);
		if(post.getId() == null) {
			throw new BadRequestAlertException("invalid id", ENTITY_NAME, "idnull");
		}
		Post result = postService.save(post);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	
	/**
	 * GET /posts get the list of all posts.
	 * 
	 * @param pageable pagination information
	 * @return ResponseEntity with status 200 (OK) with body the list of Posts
	 */
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAll(Pageable pageable){
		
		LOG.debug("REST request to get all Posts");
		Page<Post> result = postService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/posts");
		return ResponseEntity.ok().headers(headers).body(result.getContent());
		
	}
	
	/**
	 * GET /posts/:id get an "id" Post.
	 * 
	 * @param id id of the post
	 * @return ResponseEntity with status 200 (OK) or with status 404 (Not Found) if the id do not exist
	 */
	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPost(@PathVariable Long id) {
		
		LOG.debug("REST request to get Post: { }", id);
		Optional<Post> result = postService.findOne(id);
		return ResponseUtil.wrapOrNotFound(result);
	}
	
	
	/**
	 * DELETE /posts/:id delete an "id" Post.
	 * 
	 * @param id id of the post
	 * @return ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id){
		
		LOG.debug("REST request to delete Post: { }", id);
		postService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
	
	
	

}
