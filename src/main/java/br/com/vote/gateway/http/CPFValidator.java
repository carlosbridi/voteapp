package br.com.vote.gateway.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.vote.config.FeignConfiguration;
import br.com.vote.gateway.http.response.CPFResponse;

@FeignClient(url = "${br.com.vote.integration.cpfurl}", name = "${br.com.vote.integration.cpfname}", configuration = FeignConfiguration.class)
public interface CPFValidator {

	@GetMapping(path = "/{cpf}")
	CPFResponse findCPFValid(@PathVariable String cpf);
	
}
