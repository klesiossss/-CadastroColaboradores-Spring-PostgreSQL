package br.com.cadastro.model.setor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cadastro.model.EntityBase;
import br.com.cadastro.model.colaborador.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter
public class Setor extends EntityBase {
	
	
	@NotBlank(message = "Este campo é obrigatório")
	private String name;
	
	@NotBlank(message = "Fill in the description field")
	@Size(min = 50, message = "description should have at least 50 characters")
	private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "setor")
	private List<Colaborador> colaboradores;
		
}
