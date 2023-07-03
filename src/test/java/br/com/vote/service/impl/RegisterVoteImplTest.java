package br.com.vote.service.impl;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import br.com.vote.documents.EnumVote;
import br.com.vote.documents.Score;
import br.com.vote.exception.BussinessException;

@RunWith(MockitoJUnitRunner.class)
public class RegisterVoteImplTest {

	@InjectMocks
	private RegisterVoteImpl provider;
	
	@Mock
	private MongoOperations mongoOperations;
	
	@Test
	public void shouldRegisterVoteWhenYes() {
		
		provider.execute(UUID.randomUUID().toString(), EnumVote.YES.name());
		
		Mockito.verify(mongoOperations).updateFirst(Mockito.any(), Mockito.any(), Mockito.eq(Score.class));	
	}
	
	@Test
	public void shouldRegisterVoteWhenNo() {
		
		provider.execute(UUID.randomUUID().toString(), EnumVote.NO.name());
		
		Mockito.verify(mongoOperations).updateFirst(Mockito.any(), Mockito.any(), Mockito.eq(Score.class));	
	}
	
	@Test(expected = BussinessException.class)
	public void shouldNotRegisterVoteWhenOptionInvalid() {	
		provider.execute(UUID.randomUUID().toString(), "MAYBE");
		
		Mockito.verify(mongoOperations).updateFirst(Mockito.any(), Mockito.any(), Mockito.eq(Score.class));	
	}
	
	
}
