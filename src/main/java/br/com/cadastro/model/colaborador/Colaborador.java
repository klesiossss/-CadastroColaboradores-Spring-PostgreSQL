package br.com.cadastro.model.colaborador;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.cadastro.model.EntityBase;
import br.com.cadastro.model.setor.Setor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Colaborador extends EntityBase {
	
	
	@NotBlank(message = "esse campo é mandatório ser preenchido")
	private String nome;
	
	private int idade;
	
	@NotNull(message = "O preenchimento deste campo é obrigatório")
	private LocalDateTime dataNascimento;
	
	@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@Embedded
	@Valid
	private Endereco endereco;
	
	
	@NotBlank(message = "Esse campo é mandatório ser preenchido!")
	@Email(message = "Informe um email válido!")
	private String email;
	
	@Valid
	@ManyToOne
	private Setor setor;


}
