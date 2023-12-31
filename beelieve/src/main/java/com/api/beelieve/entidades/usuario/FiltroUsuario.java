package com.api.beelieve.entidades.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


import com.api.beelieve.entidades.projeto.Projeto;
import com.api.beelieve.entidades.subprojeto.SubProjeto;
import com.fasterxml.jackson.core.StreamReadConstraints.Builder;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FiltroUsuario {
	private String nome;
	private String email;
	private String cargo;
	private String departamento;
	private String projeto;
	
	public FiltroUsuario(Map<String, String> parametros) {
		if(parametros.containsKey("nome")) {
			this.nome = parametros.get("nome");
		}
		if(parametros.containsKey("email")) {
			this.email = parametros.get("email");
		}
		if(parametros.containsKey("departamento")) {
			this.departamento = parametros.get("departamento");
		}
		if(parametros.containsKey("cargo")) {
			this.cargo = parametros.get("cargo").replace("_", " ");
		}
		if(parametros.containsKey("projeto")) {
			this.projeto = parametros.get("projeto");
		}
	}
	
	public Specification<Usuario> toSpec(){
		return (root, query, builder) -> {
			List<Predicate> listaPredicados = new ArrayList<>();
			
			Specification<Usuario> especificacaoGeral = criarSpecificationGeral();
			listaPredicados.add(especificacaoGeral.toPredicate(root, query, builder));
			
			if(StringUtils.hasText(projeto)) {
				Specification<Usuario> especificacaoLiderPacote = criarSpecificationLiderPacote();
				listaPredicados.add(especificacaoLiderPacote.toPredicate(root, query, builder));
				
				Specification<Usuario> especificacaoEngeinheiro = criarSpecificationEngeinheiro();
				listaPredicados.add(especificacaoEngeinheiro.toPredicate(root, query, builder));
				
				Specification<Usuario> especificacaoAnalista = criarSpecificationAnalista();
				listaPredicados.add(especificacaoAnalista.toPredicate(root, query, builder));
				
				//Specification<Usuario> especificacaoProjeto = especificacaoAnalista.or(especificacaoEngeinheiro).or(especificacaoLiderPacote);
				//listaPredicados.add(especificacaoProjeto.toPredicate(root, query, builder));
				
				
			}
			
			return builder.and(listaPredicados.toArray(new Predicate[0]));
		};
	}
	
	
	private Specification<Usuario> criarSpecificationLiderPacote() {
		return (root, query, builder) -> {
			
	        Join<Usuario, SubProjeto> joinSubProjetos = root.join("subProjetosAtrelados");
	        
	        Join<SubProjeto, Projeto> joinProjetoSub = joinSubProjetos.join("projeto");
	        

			Path<String> campoNomeLider = joinProjetoSub.get("nome_projeto");

			
			return builder.like(campoNomeLider, "%" + projeto + "%");
		};
	}
	
	private Specification<Usuario> criarSpecificationAnalista() {
		return (root, query, builder) -> {
			
			Join<Usuario, Projeto> joinAnalista = root.join("projetosAtribuidos",JoinType.INNER);
			
			
			Path<String> campoNomeAnalista = joinAnalista.get("nome_projeto");

			
			return builder.like(campoNomeAnalista, "%" + projeto + "%");
		};
	}
	
	private Specification<Usuario> criarSpecificationEngeinheiro() {
		return (root, query, builder) -> {
			
			Join<Usuario, Projeto> joinEng = root.join("projetosAtrelados");
			
			Path<String> campoNomeProjetoEngenhiro = joinEng.get("nome_projeto");

			
			return builder.like(campoNomeProjetoEngenhiro, "%" + projeto + "%");
			
		};
	}
	
	private Specification<Usuario> criarSpecificationGeral(){
		return (root, query, builder) -> {
			List<Predicate> listaPredicados = new ArrayList<Predicate>();
			
			if(StringUtils.hasText(nome)) {
				Path<String> campoNome = root.<String>get("nome");
				Predicate predicadoNome = builder.like(campoNome, "%" + nome + "%");
				listaPredicados.add(predicadoNome);
			}
			if(StringUtils.hasText(cargo)) {
				Path<String> campoCargo = root.<String>get("cargo");
				Predicate predicadoCargo = builder.equal(campoCargo, cargo);
				listaPredicados.add(predicadoCargo);
			}
			if(StringUtils.hasText(departamento)) {
				Path<String> campoDepartamento = root.<String>get("departamento");
				Predicate predicadoDepartamento = builder.like(campoDepartamento, "%" + departamento + "%");
				listaPredicados.add(predicadoDepartamento);
			}
			if(StringUtils.hasText(email)) {
				Path<String> campoEmail = root.<String>get("email");
				Predicate predicadoEmail = builder.like(campoEmail, "%" + email + "%");
				listaPredicados.add(predicadoEmail);
			}
			
			Path<String> campoIs_active = root.<String>get("is_active");
			Predicate predicadoAtivo = builder.equal(campoIs_active, true);
			listaPredicados.add(predicadoAtivo);
			
			return builder.and(listaPredicados.toArray(new Predicate[0]));
		};
			
	}
}
