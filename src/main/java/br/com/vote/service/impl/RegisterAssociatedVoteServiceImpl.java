package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vote.documents.AssociatedAgendaVote;
import br.com.vote.repository.AssociatedAgendaVoteRepository;
import br.com.vote.service.RegisterAssociatedVoteService;

@Component
public class RegisterAssociatedVoteServiceImpl implements RegisterAssociatedVoteService {

	@Autowired
	private AssociatedAgendaVoteRepository repository;
	
	@Override
	public void execute(String agendaId, String associatedDocument) {
		repository.save(new AssociatedAgendaVote(agendaId, associatedDocument));
	}

}
