package br.com.vote.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import br.com.vote.documents.Agenda;
import br.com.vote.documents.EnumStatusPauta;
import br.com.vote.http.input.AgendaRequest;
import br.com.vote.repository.AgendaRepository;
import br.com.vote.repository.ScoreRepository;

@RunWith(MockitoJUnitRunner.class)
public class AgendaServiceImplTest {

	
	@InjectMocks
	private AgendaServiceImpl agendaServiceImpl;
	
	@Mock
	private AgendaRepository agendaRepository;
	
	@Mock 
	private ScoreRepository scoreRepository;
	
	@Mock
	private MongoOperations mongoOperations;
	
	@Test
	public void shouldCreateAgendaTest_WithTTlZero() {
		AgendaRequest agendaRequest = new AgendaRequest("Sample", 0);
		Agenda agendaDomain = agendaRequest.toDomain();
		Mockito.when(agendaRepository.save(Mockito.any())).thenReturn(agendaDomain);
		
		Agenda createAgenda = agendaServiceImpl.createAgenda(agendaRequest);
		assertEquals(createAgenda.getDescription(), agendaRequest.getDescription());

		//comparing default 1min when ttl given is zero
		assertEquals(1, createAgenda.getTtlInMinutes());
		assertEquals(EnumStatusPauta.OPENED, createAgenda.getStatus());
	}
	
	@Test
	public void shouldCreateAgendaTest_WithTtlIs10() {
		AgendaRequest agendaRequest = new AgendaRequest("Sample", 10);
		Agenda agendaDomain = agendaRequest.toDomain();
		Mockito.when(agendaRepository.save(Mockito.any())).thenReturn(agendaDomain);
		
		Agenda createAgenda = agendaServiceImpl.createAgenda(agendaRequest);
		assertEquals(createAgenda.getDescription(), agendaRequest.getDescription());

		//comparing default 1min when ttl given is zero
		assertEquals(10, createAgenda.getTtlInMinutes());
		assertEquals(EnumStatusPauta.OPENED, createAgenda.getStatus());
	}
	
}
