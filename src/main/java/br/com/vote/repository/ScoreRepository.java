package br.com.vote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vote.documents.Score;

public interface ScoreRepository extends MongoRepository<Score, String> {

	Score findByAgendaId(String agendaId);
	
}
