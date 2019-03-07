package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.Member;

/**
 * Member Repository to manage member entity
 * @author PatrickHub
 *
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

}
