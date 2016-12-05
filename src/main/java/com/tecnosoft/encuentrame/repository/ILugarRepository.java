/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.repository;


import com.tecnosoft.encuentrame.model.Lugar;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DELLINDIGO
 */
public interface ILugarRepository extends CrudRepository<Lugar, String>{
    @Override
    List<Lugar> findAll();
}
