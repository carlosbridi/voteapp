package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.vote.service.AgendaService;

@Component
public class AgendaJobFinish {

	@Autowired
	private AgendaService agendaService;
	
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		agendaService.endAgenda();
	}
}
