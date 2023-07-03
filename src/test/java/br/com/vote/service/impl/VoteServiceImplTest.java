package br.com.vote.service.impl;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vote.documents.EnumVote;
import br.com.vote.exception.BussinessException;
import br.com.vote.service.CheckAssociateCanVoteService;
import br.com.vote.service.RegisterAssociatedVoteService;
import br.com.vote.service.RegisterVoteService;
import br.com.vote.service.ValidateAgendaOpenService;
import br.com.vote.service.ValidateExternalDocumentService;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {

	@InjectMocks
	private VoteServiceImpl provider;
	
	@Mock
	private RegisterVoteService registerVote;
	
	@Mock
	private RegisterAssociatedVoteService registerAssociatedVoteService;
	
	@Mock
	private ValidateAgendaOpenService validateAgendaOpen;
	
	@Mock
	private CheckAssociateCanVoteService checkAssociateCanVote;
	
	@Mock
	private ValidateExternalDocumentService validateExternalDocument;
	
	@Test
	public void shouldRegisterVote() {
		
		Mockito.when(validateExternalDocument.validate(Mockito.anyString())).thenReturn(true);
		provider.vote(UUID.randomUUID().toString(), UUID.randomUUID().toString(), EnumVote.YES.name());
		
		Mockito.inOrder(validateAgendaOpen,
				checkAssociateCanVote,
				validateExternalDocument,
				registerVote,
				registerAssociatedVoteService);
	}
	
	@Test(expected = BussinessException.class)
	public void shouldNotRegisterVote() {
		
		Mockito.when(validateExternalDocument.validate(Mockito.anyString())).thenReturn(false);
		provider.vote(UUID.randomUUID().toString(), UUID.randomUUID().toString(), EnumVote.YES.name());
		
		Mockito.inOrder(validateAgendaOpen,
				checkAssociateCanVote,
				validateExternalDocument);
	}
}
