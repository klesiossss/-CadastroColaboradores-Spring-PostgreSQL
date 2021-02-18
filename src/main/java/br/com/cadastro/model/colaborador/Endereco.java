package br.com.cadastro.model.colaborador;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Endereco implements Serializable {
	private static final long serialVersionUID = 2442805561440142624L;
	
	@NotBlank(message = "O preenchimento deste campo é obrigatório")
	@Size(min = 8, max = 8, message = "Informe um CEP válido")
	private String cep;
	
	private String pais;
	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String referencia;

}
