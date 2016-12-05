/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.repository;

import com.tecnosoft.encuentrame.model.UsrCalificacion;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 *
 * @author DELLINDIGO
 */
public interface IUsrCalificacionRepository extends CrudRepository<UsrCalificacion, Integer>{
    @Override
    List<UsrCalificacion> findAll();
}
