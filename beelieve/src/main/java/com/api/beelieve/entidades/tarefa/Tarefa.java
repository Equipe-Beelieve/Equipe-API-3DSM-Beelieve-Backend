package com.api.beelieve.entidades.tarefa;

import java.util.Date;

import com.api.beelieve.entidades.nivelsubprojeto.NivelSubProjeto;
import com.api.beelieve.entidades.subprojeto.SubProjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Table(name = "tarefa")
@NoArgsConstructor
public class Tarefa {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tarefa;
	
	@Column
	private String descricao_atividade_tarefa;
	
	@Column
	private String resultado_esperado_tarefa;
	
	@Column
	private Integer peso_tarefa;
	
	@Column
	private Integer status_tarefa;
	
	@Column
	private Date prazo_tarefa;
	
	@ManyToOne
	@JoinColumn(name = "sub_projeto_id")
	private SubProjeto subProjeto;
	
	@ManyToOne
	@JoinColumn(name = "nivel_sub_projeto_id")
	private NivelSubProjeto nivelSubProjeto;

	public Tarefa() {
		
	}
	
	public Tarefa(DadosTarefaAtualizacao dadosTarefa, SubProjeto subProjeto) {
		
		this.descricao_atividade_tarefa = dadosTarefa.descricao_atividade_tarefa();
		this.subProjeto = subProjeto;
		this.resultado_esperado_tarefa = dadosTarefa.resultado_esperado_tarefa();
		this.status_tarefa = dadosTarefa.status_tarefa();
		
	}

	
	
	
	public Tarefa(DadosTarefaAtualizacao dadosTarefa, NivelSubProjeto nivelSubProjeto) {
		this.descricao_atividade_tarefa = dadosTarefa.descricao_atividade_tarefa();
		this.nivelSubProjeto = nivelSubProjeto;
		this.resultado_esperado_tarefa = dadosTarefa.resultado_esperado_tarefa();
		this.status_tarefa = dadosTarefa.status_tarefa();
	}




	public Tarefa(DadosTarefaCadastro tarefa, NivelSubProjeto nivelSubProjeto) {
		this.descricao_atividade_tarefa = tarefa.descricao_atividade_tarefa();
		this.resultado_esperado_tarefa = tarefa.resultado_esperado_tarefa();
		this.status_tarefa = 0;
		this.nivelSubProjeto = nivelSubProjeto;
	}




	public Tarefa(DadosTarefaCadastro tarefa, SubProjeto subProjeto) {
		this.descricao_atividade_tarefa = tarefa.descricao_atividade_tarefa();
		this.resultado_esperado_tarefa = tarefa.resultado_esperado_tarefa();
		this.status_tarefa = 0;
		this.subProjeto = subProjeto;
	}




	public Long getTarefa_id() {
		return id_tarefa;
	}

	public void setTarefa_id(Long tarefa_id) {
		this.id_tarefa = tarefa_id;
	}

	public SubProjeto getSubProjeto() {
		return subProjeto;
	}

	public void setSubProjeto(SubProjeto subProjeto) {
		this.subProjeto = subProjeto;
	}

	public NivelSubProjeto getModuloSubProjeto() {
		return nivelSubProjeto;
	}

	public void setNivelSubProjeto(NivelSubProjeto nivelSubProjeto) {
		this.nivelSubProjeto = nivelSubProjeto;
	}

	public String getDescricaoAtividade() {
		return descricao_atividade_tarefa;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricao_atividade_tarefa = descricaoAtividade;
	}

	public String getResultadoEsperado() {
		return resultado_esperado_tarefa;
	}

	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultado_esperado_tarefa = resultadoEsperado;
	}

	public Integer getStatus() {
		return status_tarefa;
	}

	public void setStatus(Integer status) {
		this.status_tarefa = status;
	}
	




}
