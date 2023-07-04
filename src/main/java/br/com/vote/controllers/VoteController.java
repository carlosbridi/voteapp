package br.com.vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.http.input.VoteRequest;
import br.com.vote.service.VoteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController("/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Vote registered with success"),
		@ApiResponse(responseCode = "404", description = "Agenda not found"),
		@ApiResponse(responseCode = "502", description = "Error with request"),
		
	})
	@PutMapping(value = "/v1/{agendaId}/vote/{associatedDocument}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity registerVote(@PathVariable String agendaId, @PathVariable String associatedDocument, @RequestBody VoteRequest voteRequest) {
		voteService.vote(agendaId, associatedDocument, voteRequest.getVote().toString());
		return ResponseEntity.ok(null);
	}
	
}
