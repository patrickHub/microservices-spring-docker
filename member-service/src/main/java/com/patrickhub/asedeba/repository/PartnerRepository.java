package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.Partner;


@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
