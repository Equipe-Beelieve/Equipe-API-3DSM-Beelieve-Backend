package com.api.beelieve.entidades.usuario.dto;

public record DadosListagemUsuario(
		Long id_usuario,
		String nome,
		String matricula,
		String cpf,
		String email,
		String senha,
		String cargo,
		String departamento,
		String telefone,
		Boolean is_active
		) {
}