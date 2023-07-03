package br.com.vote.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vote.documents.AssociatedAgendaVote;

public interface AssociatedAgendaVoteRepository extends MongoRepository<AssociatedAgendaVote, Long> {

	@Cacheable(value = "vote:findByAssociateAgendaVote",
			key = "'agendaId:' + #agendaId + 'associatedDocument:' + #associatedDocument",
			unless = "#result == null or #result.isEmpty()")
	public Optional<AssociatedAgendaVote> findByAgendaIdAndAssociatedDocument(String agendaId, String associatedDocument);
	
}
