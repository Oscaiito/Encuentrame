/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.repository;

import com.tecnosoft.encuentrame.model.UsrComunidad;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DELLINDIGO
 */
public interface IUsrComunidadRepository extends CrudRepository<UsrComunidad, String>{
    @Override
    List<UsrComunidad> findAll();
    
    @Query("SELECT u FROM UsrComunidad u WHERE u.usuario =?")
    public UsrComunidad findByUsuario(String usuario);
}
