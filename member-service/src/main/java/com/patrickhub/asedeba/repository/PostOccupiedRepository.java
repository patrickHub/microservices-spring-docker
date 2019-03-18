package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.PostOccupied;



/**
 * post occupied repository for managing post occupied entity
 * @author PatrickHub
 *
 */
@Repository
public interface PostOccupiedRepository extends JpaRepository<PostOccupied, Long> {

}
