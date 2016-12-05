/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.controller.index;

import com.tecnosoft.encuentrame.controllers.TempUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roy
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService,Serializable {


    @Autowired
    private IUsrComunidadRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        System.out.println("Estoy aqui");
        UsrComunidad user;        
        List<GrantedAuthority> autoridades = new ArrayList<>();

        user = usuarioRepository.findByUsuario(username);
        if (user != null) {
            TempUser.setCurrentUsr(user);
            autoridades.add(new SimpleGrantedAuthority("admin"));

            return new User(user.getUsuario(), user.getPassword(), true, true, true, true, autoridades);
        } else {
            throw new UsernameNotFoundException("User " + username + " no se encontro");
        }
    }
    
 }