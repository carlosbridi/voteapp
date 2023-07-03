package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vote.exception.BussinessException;
import br.com.vote.service.CheckAssociateCanVoteService;
import br.com.vote.service.RegisterAssociatedVoteService;
import br.com.vote.service.RegisterVoteService;
import br.com.vote.service.ValidateAgendaOpenService;
import br.com.vote.service.ValidateExternalDocumentService;
import br.com.vote.service.VoteService;

@Component
public class VoteServiceImpl implements VoteService {

	@Autowired 
	private RegisterVoteService registerVote;
	
	@Autowired
	private RegisterAssociatedVoteService registerAssociatedVoteService;
	
	@Autowired
	private ValidateAgendaOpenService validateAgendaOpen;
	
	@Autowired
	private CheckAssociateCanVoteService checkAssociateCanVote;
	
	@Autowired
	private ValidateExternalDocumentService validateExternalDocument;
	
	@Override
	public void vote(String agendaId, String associatedDocument, String vote) {
		validateAgendaOpen.execute(agendaId);
		checkAssociateCanVote.execute(agendaId, associatedDocument);
		
		if (validateExternalDocument.validate(vote)) {
			registerVote.execute(agendaId, vote);
			registerAssociatedVoteService.execute(agendaId, associatedDocument);
		} else {
			throw new BussinessException("This Document is cannot be able to vote");
		}
	}	
}
