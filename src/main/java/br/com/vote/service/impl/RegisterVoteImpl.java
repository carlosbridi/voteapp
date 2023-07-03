package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import br.com.vote.documents.EnumVote;
import br.com.vote.documents.Score;
import br.com.vote.service.RegisterVoteService;

@Component
public class RegisterVoteImpl implements RegisterVoteService {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void execute(String agendaId, String vote) {
		EnumVote valueOfOrInvalid = EnumVote.valueOfOrInvalid(vote.toString());
		Query q = new Query(Criteria.where("agendaId").is(agendaId));
		Update up = new Update().inc(valueOfOrInvalid.name().toLowerCase(), 1);
		mongoOperations.updateFirst(q, up, Score.class);
	}

}
