package com.patrickhub.asedeba.service.impl;

import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.Project;
import com.patrickhub.asedeba.repository.ProjectRepository;
import com.patrickhub.asedeba.service.PostOccupiedService;
import com.patrickhub.asedeba.service.ProjectService;


/**
 * service class for managing Project
 * @author PatrickHub
 *
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private final Logger LOG = LoggerFactory.getLogger(PostOccupiedService.class);
	private ProjectRepository projectRepository;
	
	
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Project save(Project project) {
		LOG.debug("Request to save new project: { }", project);
		return projectRepository.save(project);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Project> findAll(Pageable pageable) {
		LOG.debug("Request to get all projects");
		return projectRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Project> findOne(Long id) {
		LOG.debug("Request to get a project: { }", id);
		return projectRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		LOG.debug("Request to delete a project: { }", id);
		projectRepository.deleteById(id);;
	}

}
