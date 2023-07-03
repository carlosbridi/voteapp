package br.com.vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.documents.Agenda;
import br.com.vote.http.input.AgendaRequest;
import br.com.vote.service.AgendaService;

@RestController("/v1/pauta")
public class AgendaController {

	@Autowired
	private AgendaService pautaService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Agenda> createPauta(@RequestBody AgendaRequest agendaRequest){
		return ResponseEntity.ok(pautaService.createAgenda(agendaRequest));
	}
	
}
