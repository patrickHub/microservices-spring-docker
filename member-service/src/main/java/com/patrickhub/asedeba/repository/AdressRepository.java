package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.Adress;


/**
 * Adress Repository to manage adress entity
 * @author PatrickHub
 *
 */
@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {

}
