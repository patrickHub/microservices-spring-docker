package com.patrickhub.asedeba.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.Adress;
import com.patrickhub.asedeba.repository.AdressRepository;
import com.patrickhub.asedeba.service.AdressService;


/**
 * Service class for managing adresses
 * @author PatrickHub
 *
 */

@Service
@Transactional
public class AdressServiceImpl implements AdressService {
	
	private final Logger LOG = LoggerFactory.getLogger(AdressServiceImpl.class);
	private AdressRepository adressRepository;
	

	public AdressServiceImpl(AdressRepository adressRepository) {
		super();
		this.adressRepository = adressRepository;
	}

	@Override
	public Adress save(Adress adress) {
		
		LOG.debug("Request to save an Adress: { }", adress);
		return adressRepository.save(adress);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Adress> findAll(Pageable pageable) {
		
		LOG.debug("Request to get all Adresses");
		return adressRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Adress> findOne(Long id) {

		LOG.debug("Request to get Adress: { }", id);
		return adressRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		
		LOG.debug("Request to delete Adress: { }", id);
		adressRepository.deleteById(id);
	}

}
