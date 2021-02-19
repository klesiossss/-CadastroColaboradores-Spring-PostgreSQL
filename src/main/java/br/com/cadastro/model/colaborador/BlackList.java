package br.com.cadastro.model.colaborador;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BlackList {
private List<Colaborador> blacklist;
}
