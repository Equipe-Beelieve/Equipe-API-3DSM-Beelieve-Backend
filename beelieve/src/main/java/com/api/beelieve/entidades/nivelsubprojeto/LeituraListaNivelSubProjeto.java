package com.api.beelieve.entidades.nivelsubprojeto;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.beelieve.entidades.subprojeto.SubProjeto;
import com.api.beelieve.repositorio.NivelSubProjetoRepositorio;

@Service
public class LeituraListaNivelSubProjeto {
	
	@Autowired
	private NivelSubProjetoRepositorio repositorio_nivel;
	
	@Autowired
	private AtualizarNivelSubProjeto atualizaNivelSubProjeto;
	
	@Autowired
	private SalvarListaNivelSubProjeto salvarListaNivelSubProjeto;
	
	public void atualizarLista(List<DadosNivelSubProjetoAtualizacao> listaDadosNivelSubProjeto, SubProjeto subProjeto) {
		List<NivelSubProjeto> listaNivelSubProjetoAtual = repositorio_nivel.findBySubProjeto(subProjeto);
		
		//Atualizando elementos que existem no banco
		Iterator<DadosNivelSubProjetoAtualizacao> iteratorDadosNivel = listaDadosNivelSubProjeto.iterator();
		while(iteratorDadosNivel.hasNext()) {
			DadosNivelSubProjetoAtualizacao dadosNivel = iteratorDadosNivel.next();
			Iterator<NivelSubProjeto> iteratorNivelSubProjeto = listaNivelSubProjetoAtual.iterator();
			while(iteratorNivelSubProjeto.hasNext()) {
				NivelSubProjeto nivelSubProj = iteratorNivelSubProjeto.next();
				if(dadosNivel.id_nivel_sub_projeto() == nivelSubProj.getNivel_sub_projeto_id()) {
					atualizaNivelSubProjeto.atualizar(nivelSubProj, dadosNivel);
					iteratorDadosNivel.remove();
					iteratorNivelSubProjeto.remove();
				}
			}
		}
		//Criando elementos não encontrados no banco mas que estão no JSON
		if(!listaDadosNivelSubProjeto.isEmpty()) {
			salvarListaNivelSubProjeto.salvar(listaDadosNivelSubProjeto, subProjeto);
		};
		//Deletando elementos que não estão no JSON
		if(!listaNivelSubProjetoAtual.isEmpty()) {
			listaNivelSubProjetoAtual.forEach((nivelSub)->{
				repositorio_nivel.delete(nivelSub);
			});
		}
	}
	
	
}
