package com.api.beelieve.entidades.cronograma;

import java.util.ArrayList;
import java.util.List;

import com.api.beelieve.entidades.cronograma.dto.DadosMes;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Mes {
	private String mes_cronograma;
	private Integer ordem_mes_cronograma;
	private List<Progresso> niveis;
	
	public Mes() {};
	
	public Mes(DadosMes mes) {
		this.mes_cronograma = mes.mes_cronograma();
		this.ordem_mes_cronograma = mes.ordem_mes_cronograma();
		List<Progresso> niveis = new ArrayList<Progresso>();
		mes.niveis().forEach((nivel)->{
			niveis.add(new Progresso(nivel));
		});
		this.niveis = niveis;
	}

	public Mes(String nome_mes, int mes, List<Progresso> niveis) {
		this.mes_cronograma = nome_mes;
		this.ordem_mes_cronograma = mes;
		this.niveis = niveis;
	}

	public Mes(Mes mes) {
		this.mes_cronograma = mes.mes_cronograma;
		List<Progresso> listaProgresso =new ArrayList<Progresso>();
		mes.niveis.forEach((nivel)->{
			listaProgresso.add(new Progresso(nivel));
		});
		this.niveis = listaProgresso;
	}
}
