package com.patrickhub.asedeba.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.patrickhub.asedeba.domain.Member;

/**
 * Service interface for managing members
 * @author PatrickHub
 *
 */
public interface MemberService {
	
	 /**
     * Save a member.
     *
     * @param member the entity to save
     * @return the persisted entity
     */
    Member save(Member member);

    /**
     * Get all the members.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Member> findAll(Pageable pageable);


    /**
     * Get the "id" member.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Member> findOne(Long id);

    /**
     * Delete the "id" member.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
