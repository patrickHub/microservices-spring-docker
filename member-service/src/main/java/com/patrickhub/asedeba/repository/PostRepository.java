package com.patrickhub.asedeba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrickhub.asedeba.domain.Post;



/**
 * Post repository to manage Post entity
 * @author PatrickHub
 *
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
