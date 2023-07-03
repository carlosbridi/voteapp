package br.com.vote.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Associated {

	@Id
	private String id;
	
	private String name;
	
	private String document;
	
}
