package com.patrickhub.asedeba.service.impl;

import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.PostOccupied;
import com.patrickhub.asedeba.repository.PostOccupiedRepository;
import com.patrickhub.asedeba.service.PostOccupiedService;

/**
 * service class for managing post occupied
 * @author PatrickHub
 *
 */
@Service
@Transactional
public class PostOccupiedServiceImpl implements PostOccupiedService {

	private final Logger LOG = LoggerFactory.getLogger(PostOccupiedService.class);
	private PostOccupiedRepository postOccupiedRepository;
	
	
	public PostOccupiedServiceImpl(PostOccupiedRepository postOccupiedRepository) {
		super();
		this.postOccupiedRepository = postOccupiedRepository;
	}

	@Override
	public PostOccupied save(PostOccupied postOccupied) {
		LOG.debug("Request to save new PostOccupied: { }", postOccupied);
		return postOccupiedRepository.save(postOccupied);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<PostOccupied> findAll(Pageable pegeable) {
		LOG.debug("Request get all PostOccupied");
		return postOccupiedRepository.findAll(pegeable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<PostOccupied> findOne(Long id) {
		LOG.debug("Request to get a PostOccupied: { }", id);
		return postOccupiedRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		LOG.debug("Request to delete a PostOccupied: { }", id);
		postOccupiedRepository.deleteById(id);

	}

}
