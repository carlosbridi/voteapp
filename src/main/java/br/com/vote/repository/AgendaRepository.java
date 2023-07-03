package br.com.vote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vote.documents.Agenda;

public interface AgendaRepository  extends MongoRepository<Agenda, String> {

	public List<Agenda> findByStatus(String status);
}
