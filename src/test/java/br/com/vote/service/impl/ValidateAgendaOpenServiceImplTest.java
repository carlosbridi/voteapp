package br.com.vote.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vote.documents.Agenda;
import br.com.vote.documents.EnumStatusPauta;
import br.com.vote.exception.BussinessException;
import br.com.vote.exception.NotFoundException;
import br.com.vote.repository.AgendaRepository;

@RunWith(MockitoJUnitRunner.class)
public class ValidateAgendaOpenServiceImplTest {

	@InjectMocks
	private ValidateAgendaOpenServiceImpl provider;
	
	@Mock
	private AgendaRepository agendaRepository;
	
	@Test
	public void shouldReturnAbleAgenda() {
		
		Agenda agenda = new Agenda();
		agenda.setStatus(EnumStatusPauta.OPENED);
		
		Mockito.when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(agenda));
		provider.execute(UUID.randomUUID().toString());
		
	}
	
	@Test(expected = BussinessException.class)
	public void shouldExceptionOnAgendaFinished() {
		Agenda agenda = new Agenda();
		agenda.setStatus(EnumStatusPauta.FINISHED);
		
		Mockito.when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.of(agenda));
		provider.execute(UUID.randomUUID().toString());
	}
	
	@Test(expected = NotFoundException.class)
	public void shouldExceptionOnAgendaNotFoundException() {
		Mockito.when(agendaRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		provider.execute(UUID.randomUUID().toString());
	}
	
}
