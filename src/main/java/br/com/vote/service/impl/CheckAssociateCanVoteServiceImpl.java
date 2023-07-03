package br.com.vote.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vote.documents.AssociatedAgendaVote;
import br.com.vote.exception.BussinessException;
import br.com.vote.repository.AssociatedAgendaVoteRepository;
import br.com.vote.service.CheckAssociateCanVoteService;

@Component
public class CheckAssociateCanVoteServiceImpl implements CheckAssociateCanVoteService {

	@Autowired
	private AssociatedAgendaVoteRepository repository;
	
	@Override
	public void execute(String agendaId, String associatedDocument) {
		final Optional<AssociatedAgendaVote> findByAssociateDocumentAndAgendaId = repository.findByAgendaIdAndAssociatedDocument(agendaId, associatedDocument);
		if (findByAssociateDocumentAndAgendaId.isPresent())
			throw new BussinessException("Associated already voted in this Agenda!");
	}

}
