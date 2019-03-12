package com.patrickhub.asedeba.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.Partner;
import com.patrickhub.asedeba.repository.PartnerRepository;
import com.patrickhub.asedeba.service.PartnerService;



/**
 * service class for managing partners
 * @author PatrickHub
 *
 */
@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {
	
	private final Logger LOG = LoggerFactory.getLogger(PartnerServiceImpl.class);
	private PartnerRepository partnerRepository;
	

	
	public PartnerServiceImpl(PartnerRepository partnerRepository) {
		super();
		this.partnerRepository = partnerRepository;
	}

	@Override
	public Partner save(Partner partner) {

		LOG.debug("Request to save a Partner: { }", partner);
		return partnerRepository.save(partner);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Partner> findAll(Pageable pageable) {
		
		LOG.debug("Request to get all Partners" );
		return partnerRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Partner> findOne(Long id) {
		
		LOG.debug("Request to get Partner: { }", id);
		return partnerRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		
		LOG.debug("Request to delete Partner: { }", id);
		partnerRepository.deleteById(id);

	}

}
