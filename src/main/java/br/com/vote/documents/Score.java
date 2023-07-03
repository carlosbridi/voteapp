package br.com.vote.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Score {
	
	@Id
	private String id;
	private String agendaId;
	private long yes = 0;
	private long no = 0;
	
	public Score(String agendaId) {
		super();
		this.agendaId = agendaId;
	}	
	
}
