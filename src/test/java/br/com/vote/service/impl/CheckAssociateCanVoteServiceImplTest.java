package br.com.vote.service.impl;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vote.documents.AssociatedAgendaVote;
import br.com.vote.exception.BussinessException;
import br.com.vote.repository.AssociatedAgendaVoteRepository;

@RunWith(MockitoJUnitRunner.class)
public class CheckAssociateCanVoteServiceImplTest {

	@InjectMocks
	private CheckAssociateCanVoteServiceImpl provider;
	
	@Mock
	private AssociatedAgendaVoteRepository repository;
	
	@Test
	public void shouldAssociateCanVote() {
		Mockito.when(repository.findByAgendaIdAndAssociatedDocument(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
		
		provider.execute(Mockito.anyString(), Mockito.anyString());	
		
	}
	
	@Test(expected = BussinessException.class)
	public void shouldNotAssociateCanVote() {
		AssociatedAgendaVote associatedAgendaVote = new AssociatedAgendaVote("3289109321", "43521643521");
		Mockito.when(repository.findByAgendaIdAndAssociatedDocument(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(associatedAgendaVote));
		
		provider.execute(Mockito.anyString(), Mockito.anyString());
	}
	
}
