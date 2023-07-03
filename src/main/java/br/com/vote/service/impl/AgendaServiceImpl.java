package br.com.vote.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import br.com.vote.documents.Agenda;
import br.com.vote.documents.EnumStatusPauta;
import br.com.vote.documents.Score;
import br.com.vote.http.input.AgendaRequest;
import br.com.vote.repository.AgendaRepository;
import br.com.vote.repository.ScoreRepository;
import br.com.vote.service.AgendaService;

@Component
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public Agenda createAgenda(AgendaRequest agendaRequest) {
		final Agenda savedPauta = agendaRepository.save(agendaRequest.toDomain());
		scoreRepository.save(new Score(savedPauta.getId()));
		return savedPauta;
	}

	@Override
	public void endAgenda() {
		Query query = new Query(Criteria.where("endAt").lte(LocalDateTime.now().toString())
				.and("status").in(EnumStatusPauta.OPENED));
		
		Update update = new Update();
		update.set("status", EnumStatusPauta.FINISHED);		
		
		mongoOperations.updateMulti(query, update, Agenda.class);
	}

}
