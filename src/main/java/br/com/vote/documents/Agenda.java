package br.com.vote.documents;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {

	@Id
	private String id;
	private String description;
	private String createAt;
	private String endAt;
	private long ttlInMinutes;
	private EnumStatusPauta status;
	
	public Agenda(final String description, final long ttl) {
		super();
		this.description = description;
		this.createAt = LocalDateTime.now().toString();
		this.ttlInMinutes = ttl == 0 ? 1L : ttl;
		this.endAt = LocalDateTime.now().plusMinutes(this.ttlInMinutes).toString();
		this.status = EnumStatusPauta.OPENED;
	}
}
