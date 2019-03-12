package com.patrickhub.asedeba.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.Post;
import com.patrickhub.asedeba.repository.PostRepository;
import com.patrickhub.asedeba.service.PostService;

/**
 * service class for managing posts
 * @author PatrickHub
 *
 */

@Service
@Transactional 
public class PostServiceImpl implements PostService {
	
	private final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);
	private PostRepository postRepository;
	
	

	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@Override
	public Post save(Post post) {

		LOG.debug("Request to save a Post: { }", post);
		return postRepository.save(post);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Post> findAll(Pageable pegeable) {

		LOG.debug("Request to get all Posts");
		return postRepository.findAll(pegeable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Post> findOne(Long id) {
		
		LOG.debug("Request to get Post: { }", id);
		return postRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		
		LOG.debug("Request to delete a Post: { }", id);
		postRepository.deleteById(id);

	}

}
