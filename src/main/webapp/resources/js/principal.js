/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargaPublicaciones() {
    $('#contPrincipal').load('indexComunidad.xhtml div#contPrincipal');
}
function cargaRegistro() {
    $('#contPrincipal').load('userRegistro.xhtml');
}

function recoverPassword() {
    $('#contPrincipal').load('recoverPassword.xhtml');
}

function userModify() {
    $('#contPrincipal').load('userModify.xhtml');
}

function mapaPrueba() {
    $('#contPrincipal').load('pruebamapa.xhtml');
}

function calUsuario(){
    $('#contPrincipal').load('calificarUsr.xhtml')
}