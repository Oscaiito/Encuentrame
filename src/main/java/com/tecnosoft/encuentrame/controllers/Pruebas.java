/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author DELLINDIGO
 */
public class Pruebas {
 private UsrComunidad usr;
 @Autowired
 private IUsrComunidadRepository usrRepository;
  public void guardar(){
      usrRepository.save(usr);
      usrRepository.delete(usr);
      usrRepository.findAll();
  }
}
