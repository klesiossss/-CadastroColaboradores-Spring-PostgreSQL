package br.com.cadastro.model;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Colaborador extends EntityBase {
	
	
	//@NotBlank(message = "esse campo é mandatório ser preenchido")
	private String name;
	
	private int idade;
	
	//@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDateTime dataNascimento;
	
	//@NotNull
	//@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@Embedded
	private Endereco endereco;
	
	
	//@NotBlank(message = "Esse campo é mandatório ser preenchido!")
	//@Email(message = "Informe um email válido!")
	//private String email;
	
	//@Valid
	@NotNull
	@ManyToOne
	private Setor setor;


}
