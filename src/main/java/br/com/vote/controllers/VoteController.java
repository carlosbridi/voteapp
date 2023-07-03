package br.com.vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.http.input.VoteRequest;
import br.com.vote.service.VoteService;
import jakarta.validation.Valid;

@RestController("/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PutMapping(value = "v1/{agendaId}/vote/{associatedDocument}")
	public ResponseEntity registerVote(@PathVariable String agendaId, @PathVariable String associatedDocument, @RequestBody VoteRequest voteRequest) {
		voteService.vote(agendaId, associatedDocument, voteRequest.getVote().toString());
		return ResponseEntity.ok(null);
	}
	
}
