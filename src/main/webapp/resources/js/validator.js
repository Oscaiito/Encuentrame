/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function logOut() {
   $('#contPrincipal').load('indexComunidad.xhtml');
}

function creaObjeto(){
    $('#contPrincipal').load('CreaObjeto.xhtml');
}

function cargaUserPublicaciones() {
    $('#contPrincipal').load('indexUsuario.xhtml div#buscadorPublic');
}

function cargaMisPublicaciones(){
    $('#contPrincipal').load('views.xhtml div#misP');
}

function killUser(){
    $('#contPrincipal').load('views.xhtml div#userSearch');
}

function agregarLugar(){
    $('#contPrincipal').load('views.xhtml div#addPlace');
}

function validaRespuestas(){
    $('#contPrincipal').load('views.xhtml div#misEncuestas');    
}

function loadCitas(){
    $('#contPrincipal').load('views.xhtml div#viewDate');
}

function crearCitas(){
    $('#contPrincipal').load('views.xhtml div#mkDate');
}

function generaRespuestas(){
    $('#contPrincipal').load('views.xhtml div#mkAns');
}

function loadPic() {
   $('#contPrincipal').load('views.xhtml div#changePicture');
}

function validateReg(){
    validateField('usrReg:nameRegLabel', 'usrReg:nameReg', 'Se requiere un nombre');
}

function validateField(labelId, inputId, msj){
    var res = document.getElementById(inputId).value;    
    if(res == null || res == ""){
        document.getElementById(labelId).innerHTML = msj;
        return false;
    }    
    document.getElementById(labelId).innerHTML = "";    
    return true;
    
}

function search(field1, field2, panel){
    if(!field1)
        return;
    if(!field2)
        return;
    document.getElementById(panel).style.display = '';    
    
}

function unHide(label1,val,label2){
    var str = "";
    str = str.concat(label1,val, label2);
    var seccion = document.getElementById(str);
    if(seccion.style.display === ''){
        seccion.style.display = 'none';
    }else{
        seccion.style.display = '';    
    }
}
/**/


function validateQstn(labelId, inputId){
    var qst = document.getElementById(inputId).value;    
    if(qst == null || qst == ""){
        document.getElementById(labelId).innerHTML = "Es necesario escribir la pregunta";
        return false;
    } 
    if(qst.length < 5){
        document.getElementById(labelId).innerHTML = "Su pregunta es muy corta, vuelva a formular";
        return false;
        
    }
    if(qst.length > 50){
        document.getElementById(labelId).innerHTML = "Su pregunta es demasiado larga, vuelva a formular";
        return false;        
    }    
    document.getElementById(labelId).innerHTML = "";
    
    return true;
}

function validaRes(inputId, labelId){
    var res = document.getElementById(inputId).value.trim();    
    if(res == null || res == ""){
        document.getElementById(labelId).innerHTML = "Es necesario escribir la respuesta";
        return false;
    }    
    document.getElementById(labelId).innerHTML = "";    
    return true;
}

function answReady(){
    var isready = true;
    isready &= validaRes('mkaRespuestas:mkaR1','mkaRespuestas:labelR1');    
    isready &= validaRes('mkaRespuestas:mkaR2','mkaRespuestas:labelR2');
    isready &= validaRes('mkaRespuestas:mkaR3','mkaRespuestas:labelR3');
    isready &= validaRes('mkaRespuestas:mkaR4','mkaRespuestas:labelR4');
    isready &= validaRes('mkaRespuestas:mkaR5','mkaRespuestas:labelR5');    
    if(isready){        
        $('#contPrincipal').load('indexUsuario.xhtml div#buscadorPublic');
        return true;
    }
    return false;
}


function logIn(opc){
    if(!validateField('logForm:usrLabel', 'logForm:firstname', 'Ingrese usuario')){
        return;
    }
    if(!validateField('logForm:passLabel', 'logForm:surname', 'Ingrese contraseña')){
        return;
    }  
    console.log(opc);
    
    switch(opc){
        case 0:
            cargaMenu();
            console.log('correcto');
            break;
        case 1:
            document.getElementById('logForm:usrLabel').innerHTML = "Usuario no registrado";
            console.log('usuario no encontrado');
            break;
        case 2:
            document.getElementById('logForm:passLabel').innerHTML = "Contraseña incorrecta";
            console.log('contraseña incorrecta');
        default:
            console.log('something wrong');
    }    
}



function getName(user, name){
    var str = "Usuario: ";
    str = str.concat(user, " - " , name);
    return str;
}

function println(val){
    console.log(val);
}

function validDate(val, id, label){
    var str = "mkdForm:mkdTable:";
    var today = new Date();
    today.setDate(today.getDate() + 1);
    if(!validateField(str.concat(val, label), str.concat(val, id), 'Debe seleccionar una fecha y hora')){
        return false;
    }
    var fecha = new Date(document.getElementById(str.concat(val, id)).value);
    if(fecha.getTime() < today.getTime()){
        document.getElementById(str.concat(val, label)).innerHTML = 'La fecha deber ser al menos un dia apartir de hoy';
        return false;
    }
    var h = fecha.getUTCHours();    
    if(h < 7 || h > 21){
        document.getElementById(str.concat(val, label)).innerHTML = 'El horario de entrega es de 7:00am - 8:00pm';
        return false;
    }
    if(h === 20){
        if(fecha.getUTCMinutes() > 0){
            document.getElementById(str.concat(val, label)).innerHTML = 'El horario de entrega es de 7:00am - 8:00pm';
            return false;
        }
    }
    document.getElementById(str.concat(val, label)).innerHTML = "";
    return true;
}

function dateCreated(val){
    var str = "mkdForm:mkdTable:";
    var msj = 'Debe seleccionar un lugar de la lista';
    var isOk1 = validateField(str.concat(val, ':date1Label'), str.concat(val, ':place1'), msj); 
    if(isOk1){
        isOk1 &= validDate(val, ':date1', ':date1Label');
    }
    var isOk2 = validateField(str.concat(val, ':date2Label'), str.concat(val, ':place2'), msj);
    if(isOk2){
        isOk2 &= validDate(val, ':date2', ':date2Label');
    }
    var isOk3 = validateField(str.concat(val, ':date3Label'), str.concat(val, ':place3'), msj);
    if(isOk3){
        isOk3 &= validDate(val, ':date3', ':date3Label');
    }
    if(isOk1 && isOk2 && isOk3){
        cargaPublicaciones();
        return true;
    }
    return false;
}