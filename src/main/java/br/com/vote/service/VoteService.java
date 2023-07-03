package br.com.vote.service;

public interface VoteService {
	
	void vote(String agendaId, String associatedId, String vote);

}
