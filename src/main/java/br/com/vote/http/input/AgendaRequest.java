package br.com.vote.http.input;

import br.com.vote.documents.Agenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {

	private String description;
	private long ttlInMinutes = 1;

	public Agenda toDomain() {
		return new Agenda(description, ttlInMinutes);	
	}
	
}
