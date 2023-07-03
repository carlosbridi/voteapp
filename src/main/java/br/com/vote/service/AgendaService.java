package br.com.vote.service;

import br.com.vote.documents.Agenda;
import br.com.vote.http.input.AgendaRequest;

public interface AgendaService {

	Agenda createAgenda(final AgendaRequest pautaRequest);
	
	void endAgenda();
}
