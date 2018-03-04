/**
 * Created by Дмитрий on 04.03.2018.
 */
function validInput() {

    var login = document.getElementById('login');
    var pas = document.getElementById('password');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (login.value.trim().length == 0 || login.value.trim().length > 40 ||
        pas.value.trim().length == 0 || pas.value.trim().length > 40){

        login.style.border = redBorder;
        pas.style.border = redBorder;

        document.getElementById('message').innerHTML = "заполните поля";
        document.getElementById('message').style.color = "red";

        return false;

    }else{

        doc.style.border = noBorder;
        document.getElementById('message').innerHTML = "";

    }

    return true;

}
