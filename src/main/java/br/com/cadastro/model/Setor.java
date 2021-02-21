package br.com.cadastro.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

 
@Entity
@Getter @Setter
public class Setor extends EntityBase {
	
	//@NotBlank(message = "Este campo é obrigatório")
	private String name;
	private String descricao;
	
		
}
