package br.com.vote.documents;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class AssociatedAgendaVote {

	@Id
	private String id;
	
	private String agendaId;
	private String associatedDocument;
	
	
	@CreatedDate
	private LocalDateTime votedAt;

	public AssociatedAgendaVote(String agendaId, String associatedDocument) {
		super();
		this.agendaId = agendaId;
		this.associatedDocument = associatedDocument;
		this.votedAt = LocalDateTime.now();
	}
	
	
}
