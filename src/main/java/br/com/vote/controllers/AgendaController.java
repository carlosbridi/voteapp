package br.com.vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.documents.Agenda;
import br.com.vote.http.input.AgendaRequest;
import br.com.vote.service.AgendaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController("/pauta")
public class AgendaController {

	@Autowired
	private AgendaService pautaService;
	
	@PostMapping(value = "/v1/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Agenda created")
	})
	public ResponseEntity<Agenda> createPauta(@RequestBody AgendaRequest agendaRequest){
		return ResponseEntity.ok(pautaService.createAgenda(agendaRequest));
	}
	
}
