/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.controller.index;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
 * @author Memo
 */
@Controller("indexController")
@Scope("request")
public class IndexController {
     public String getCurrentUser() {
        UserDetails usr = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "DETALLES";
    }
    
}
