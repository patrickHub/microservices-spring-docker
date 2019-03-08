package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.Donor;


/**
 * A repository for managing SQL request on Donor entity
 * @author PatrickHub
 *
 */

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
	
}
