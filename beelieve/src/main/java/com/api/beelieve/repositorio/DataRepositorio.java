package com.api.beelieve.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.beelieve.entidades.data.DataCustomizada;

public interface DataRepositorio extends JpaRepository<DataCustomizada, Long>{

}