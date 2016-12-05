/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.repository;

import com.tecnosoft.encuentrame.model.Respuestas;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DELLINDIGO
 */
public interface IRespuestasRepository extends CrudRepository<Respuestas, Integer>{
    @Override
    List<Respuestas> findAll();
    @Query("SELECT r FROM Respuestas r WHERE r.idRespuestas = ?")
    public Respuestas findById(int id);
}
