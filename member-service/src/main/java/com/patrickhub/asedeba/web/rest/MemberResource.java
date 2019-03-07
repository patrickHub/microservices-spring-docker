package com.patrickhub.asedeba.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patrickhub.asedeba.domain.Member;
import com.patrickhub.asedeba.service.MemberService;
import com.patrickhub.asedeba.web.error.BadRequestAlertException;
import com.patrickhub.asedeba.web.util.HeaderUtil;
import com.patrickhub.asedeba.web.util.PaginationUtil;
import com.patrickhub.asedeba.web.util.ResponseUtil;


/**
 * Controller for managing Rest API requests on member domain
 * @author PatrickHub
 *
 */
@RestController
@RequestMapping("/api")
public class MemberResource {
	private final Logger LOG = LoggerFactory.getLogger(MemberResource.class);
	
	
	private MemberService memberService;
	
	private static final String ENTITY_NAME = "member-service.Member";

	
	public MemberResource(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	/**
     * POST  /members : Create a new member.
     *
     * @param member the member to create
     * @return the ResponseEntity with status 201 (Created) and with body the new member, or with status 400 (Bad Request) if the member has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) throws URISyntaxException {
        LOG.debug("REST request to save Member : {}", member);
        if (member.getId() != null) {
            throw new BadRequestAlertException("A new member cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Member result = memberService.save(member);
        return ResponseEntity.created(new URI("/api/members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /members : Updates an existing member.
     *
     * @param member the member to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated member,
     * or with status 400 (Bad Request) if the member is not valid,
     * or with status 500 (Internal Server Error) if the member couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/members")
    public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member) throws URISyntaxException {
        LOG.debug("REST request to update Member : {}", member);
        if (member.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Member result = memberService.save(member);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, member.getId().toString()))
            .body(result);
    }

    /**
     * GET  /members : get all the members.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of members in body
     */
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers(Pageable pageable) {
        LOG.debug("REST request to get a page of Members");
        Page<Member> page = memberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/members");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /members/:id : get the "id" member.
     *
     * @param id the id of the members to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the member, or with status 404 (Not Found)
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        LOG.debug("REST request to get Member : {}", id);
        Optional<Member> member = memberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(member);
    }

    /**
     * DELETE  /members/:id : delete the "id" member.
     *
     * @param id the id of the member to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        LOG.debug("REST request to delete Member : {}", id);
        try {
        	memberService.delete(id);
        	return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        }catch(IllegalArgumentException e) {
        	return ResponseEntity.notFound().headers(HeaderUtil.deleteFailureAlert(ENTITY_NAME, id.toString(), "Identifier " + id.toString() + "don't exist! ")).build();
        }
        
    }
    

	
}
