package com.patrickhub.asedeba.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patrickhub.asedeba.domain.Member;
import com.patrickhub.asedeba.repository.MemberRepository;
import com.patrickhub.asedeba.service.MemberService;


/**
 * Service class for managing members
 * @author PatrickHub
 *
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	private final Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	private MemberRepository memberRepository;
	

	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}
	
	@Override
	public Member save(Member member) {
		
		LOG.debug("Request to save a Member: { }", member);
		return memberRepository.save(member);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Member> findAll(Pageable pageable) {
		
		LOG.debug("Request to get all Members");
		return memberRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Member> findOne(Long id) {
		
		LOG.debug("Request to find Member: { }", id);
		return memberRepository.findById(id);
	}

	@Override
	public void delete(Long id) throws IllegalArgumentException {
		
		LOG.debug("Request to delete Member: { }", id);
		try {
			memberRepository.deleteById(id);
		}catch (IllegalArgumentException e) {
			throw e;
		}
	}

}
