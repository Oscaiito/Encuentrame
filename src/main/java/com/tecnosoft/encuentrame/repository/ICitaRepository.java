/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.repository;

import com.tecnosoft.encuentrame.model.Cita;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DELLINDIGO
 */
public interface ICitaRepository extends CrudRepository<Cita, Integer>{
    @Override
    List<Cita> findAll(); 
}
