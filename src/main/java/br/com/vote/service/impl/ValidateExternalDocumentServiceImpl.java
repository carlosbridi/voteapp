package br.com.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.vote.gateway.http.DocumentValidator;
import br.com.vote.gateway.http.response.CPFResponse;
import br.com.vote.service.ValidateExternalDocumentService;

@Component
public class ValidateExternalDocumentServiceImpl implements ValidateExternalDocumentService {
	
	@Value("${br.com.voteapp.externalvalidator}")
	public boolean validateExternalDocument;
	
	@Autowired
	private DocumentValidator cpfValidator;
	
	@Override
	public boolean validate(String cpf) {
		if (validateExternalDocument) {
			CPFResponse findCPFValid = cpfValidator.findCPFValid(cpf);
			return findCPFValid.getStatus().equalsIgnoreCase("ABLE_TO_VOTE");
		}
		return true;
	}
}
