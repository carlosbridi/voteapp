package br.com.vote.service.impl;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vote.repository.AssociatedAgendaVoteRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegisterAssociatedVoteServiceImplTest {

	@InjectMocks
	private RegisterAssociatedVoteServiceImpl provider;
	
	@Mock
	private AssociatedAgendaVoteRepository repository;
	
	@Test
	public void shouldRegisterAssociatedVotedInAgenda() {
		provider.execute(UUID.randomUUID().toString(), UUID.randomUUID().toString());
		
		Mockito.verify(repository).save(Mockito.any());
	}
	
}
