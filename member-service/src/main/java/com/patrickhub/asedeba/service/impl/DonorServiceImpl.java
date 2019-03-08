package com.patrickhub.asedeba.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.Donor;
import com.patrickhub.asedeba.repository.DonorRepository;
import com.patrickhub.asedeba.service.DonorService;


/**
 * Service class for managing donors
 * @author PatrickHub
 *
 */

@Service
@Transactional
public class DonorServiceImpl implements DonorService {
	
	private final Logger LOG = LoggerFactory.getLogger(DonorServiceImpl.class);
	private DonorRepository donorRepository;
	
	
	public DonorServiceImpl(DonorRepository donorRepository) {
		super();
		this.donorRepository = donorRepository;
	}

	@Override
	public Donor save(Donor donor) {
		
		LOG.debug("Request to save a Donor: { }", donor);
		return donorRepository.save(donor);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Donor> findAll(Pageable pageable) {
		
		LOG.debug("Request to get all Donors");
		return donorRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Donor> findOne(Long id) {
		
		LOG.debug("Request to get Donor: { }", id);
		return donorRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		
		LOG.debug("Request to delete Donor: { }", id);
		donorRepository.deleteById(id);

	}

}
