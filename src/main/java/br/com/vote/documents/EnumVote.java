package br.com.vote.documents;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.vote.exception.BussinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumVote {

	YES,
	NO;
	
	@JsonCreator
    public static EnumVote valueOfOrInvalid(String name) {
        try {
            return EnumVote.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new BussinessException("Invalid value. Only YES or NO are allowed.");
        }
    }
	
	
}
