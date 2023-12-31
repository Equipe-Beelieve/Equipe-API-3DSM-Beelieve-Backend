package com.api.beelieve.entidades.projeto.dto;

import java.math.BigDecimal;
import java.util.List;

import com.api.beelieve.entidades.subprojeto.dto.DadosSubProjetoCadastro;


public record DadosProjetoCadastro(
		String ordem_projeto,
		String nome_projeto,
		Long chefe_projeto,
		String descricao_projeto,
		Integer prazo_meses,
		BigDecimal valor_hora_projeto,
		Double progresso_projeto,
		List<DadosSubProjetoCadastro> sub_projetos

		) {

}
