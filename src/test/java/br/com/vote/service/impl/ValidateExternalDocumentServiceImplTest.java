package br.com.vote.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.vote.gateway.http.DocumentValidator;
import br.com.vote.gateway.http.response.CPFResponse;
import br.com.vote.service.ValidateExternalDocumentService;

@RunWith(MockitoJUnitRunner.class)
public class ValidateExternalDocumentServiceImplTest {
	
	@InjectMocks
	private ValidateExternalDocumentServiceImpl provider;
	
	@Mock
	private DocumentValidator documentValidator;
	
	@Test
	public void shouldAbleToVoteWhenExternalValidationOff() {
		assertTrue(provider.validate(UUID.randomUUID().toString()));
	}
	
	@Test
	public void shouldBeAbleToVote() {
		ReflectionTestUtils.setField(provider, "validateExternalDocument", true);
		String document = UUID.randomUUID().toString();
		when(documentValidator.findCPFValid(document)).thenReturn(new CPFResponse("ABLE_TO_VOTE"));
		assertTrue(provider.validate(document));		
	}
	
	@Test
	public void shouldBeUnableToVote() {
		ReflectionTestUtils.setField(provider, "validateExternalDocument", true);
		String document = UUID.randomUUID().toString();
		when(documentValidator.findCPFValid(document)).thenReturn(new CPFResponse("UNABLE_TO_VOTE"));
		assertFalse(provider.validate(document));		
		
	}
	
}
