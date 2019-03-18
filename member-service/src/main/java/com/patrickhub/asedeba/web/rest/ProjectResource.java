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

import com.patrickhub.asedeba.domain.Project;
import com.patrickhub.asedeba.service.ProjectService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;

/**
 * Controller for managing Rest API requests on project domain
 * @author PatrickHub
 *
 */

@RestController
@RequestMapping("/api")
public class ProjectResource {
	private final Logger LOG = LoggerFactory.getLogger(AdressResource.class);
	private ProjectService projectService;
	public final String ENTITY_NAME = "member-service.Project";
	
	public ProjectResource(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
	/**
	 * POST /projects : Create a new project
	 * 
	 * @param project the project to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new project, or with status 400 (Bad Request) if project has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/projects")
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) throws URISyntaxException{
		
		LOG.debug("REST request to save a Project: { }", project);
		if(project.getId() != null) {
			throw new BadRequestAlertException("A new project cannot already have an id", ENTITY_NAME, "idexists");
		}
		Project result = projectService.save(project);
		return ResponseEntity.created(new URI("/api/projects/" + result.getId()))
								.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
	}
	
	/**
	 * PUT /projects :  update an existing project
	 * @param project project to update
	 * @return ResponseEntity with status 200 and body the updated project
	 * or with status 400 (Bad Request) if the project is not valid
	 * or with status 500 (Internal Server Error) is the project could not be update
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/projects")
	public ResponseEntity<Project> updateProject(@Valid @RequestBody Project project) throws URISyntaxException{
		
		LOG.debug("REST request to update a Project : { }", project);
		
		if(project.getId() == null) {
			throw new BadRequestAlertException("invalid id", ENTITY_NAME, "idnull");
		}
		
		Project result = projectService.save(project);
		return ResponseEntity.ok()
								.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
								.body(result);
				
	}
	
	
	/**
	 * GET /projects : get all the projects .
	 * 
	 * @param pageable the pagination information
	 * @return ResponseEntity with status 200 (OK) and the list of projects in boby
	 */
	@GetMapping("/projects")
	public ResponseEntity<List<Project>> getAllProjects(Pageable pageable){
		
		LOG.debug("REST request to get a page of projects");
		Page<Project> result = projectService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "api/adress");
		return ResponseEntity.ok().headers(headers).body(result.getContent());
	}
	
	
	/**
	 * GET /project/:id : get an "id" project
	 * @param id id of the project to retrieve
	 * @return ResponseEntity with status 200 (OK) and body the project or with status 404 (Not Found) if the id is not valid
	 */
	@GetMapping("/projects/{id}")
	ResponseEntity<Project> getProject(@PathVariable Long id){
		
		LOG.debug("REST request to get a project : { }", id);
		Optional<Project> result = projectService.findOne(id);
		return ResponseUtil.wrapOrNotFound(result);
	}
	
	
	/**
	 * DELETE /projects/:id : delete an "id" project
	 * @param id id of the project to delete
	 * @return ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/projects/{id}")
	ResponseEntity<Void> deleteProject(@PathVariable Long id){
		
		LOG.debug("REST request to delete a project : { }", id);
		projectService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
