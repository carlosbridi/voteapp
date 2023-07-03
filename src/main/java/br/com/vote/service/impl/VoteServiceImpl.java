package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vote.service.CheckAssociateCanVoteService;
import br.com.vote.service.RegisterAssociatedVoteService;
import br.com.vote.service.RegisterVote;
import br.com.vote.service.ValidateAgendaOpen;
import br.com.vote.service.VoteService;

@Component
public class VoteServiceImpl implements VoteService {

	@Autowired 
	private RegisterVote registerVote;
	
	@Autowired
	private RegisterAssociatedVoteService registerAssociatedVoteService;
	
	@Autowired
	private ValidateAgendaOpen validateAgendaOpen;
	
	@Autowired
	private CheckAssociateCanVoteService checkAssociateCanVote;
	
	@Override
	public void vote(String agendaId, String associatedDocument, String vote) {
		validateAgendaOpen.execute(agendaId);
		checkAssociateCanVote.execute(agendaId, associatedDocument);
		//Validate external CPF
		registerVote.execute(agendaId, vote);
		registerAssociatedVoteService.execute(agendaId, associatedDocument);
	}	
}
