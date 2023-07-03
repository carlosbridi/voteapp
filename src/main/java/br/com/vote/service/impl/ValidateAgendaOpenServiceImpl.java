package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vote.documents.Agenda;
import br.com.vote.documents.EnumStatusPauta;
import br.com.vote.exception.BussinessException;
import br.com.vote.exception.NotFoundException;
import br.com.vote.repository.AgendaRepository;
import br.com.vote.service.ValidateAgendaOpen;

@Component
public class ValidateAgendaOpenServiceImpl implements ValidateAgendaOpen {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Override
	public void execute(String agendaId) {
		final Agenda agenda = agendaRepository
				.findById(agendaId)
				.orElseThrow(() -> new NotFoundException("Agenda not found."));
		
		if (EnumStatusPauta.FINISHED.equals(agenda.getStatus())){
			throw new BussinessException("Agenda already Finished.");
		}
		
		
	}

}
