/**
 * Created by Дмитрий on 02.03.2018.
 */
function validAdmin() {

    var name = document.getElementById('name');
    var erName = document.getElementById('erName');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";
    var countNotValid = 0;

    if (name.value.trim().length == 0 || name.value.trim().length > 40) {
        erName.innerHTML = "укажите имя админисратора (максимум 40 символов)";
        erName.style.color = "red";
        name.style.border = redBorder;
        countNotValid++;
    }
    else {
        erName.innerHTML = "";
        name.style.border = noBorder;
    }

    var password = document.getElementById('password');
    var erPass = document.getElementById('erPass');

    if (password.value.trim().length == 0 || password.value.trim().length > 40 || password.value.trim().length < 6) {
        erPass.innerHTML = "укажите пароль админисратора (от 6 до 40 символов)";
        erPass.style.color = "red";
        password.style.border = redBorder;
        countNotValid++;
    }
    else {
        erPass.innerHTML = "";
        password.style.border = noBorder;
    }

    var boss = document.getElementById('boss');
    var erBoss = document.getElementById('erBoss');

    if (boss.value.trim().length == 0 || boss.value.trim().length > 40 || !(isInteger(boss.value.trim()))) {
        erBoss.innerHTML = "укажите id начальника(целое число)";
        erBoss.style.color = "red";
        boss.style.border = redBorder;
        countNotValid++;
    }
    else {
        erBoss.innerHTML = "";
        boss.style.border = noBorder;
    }

    return countNotValid == 0;

}
function isInteger(num) {
    return num%1 == 0 && num>0;
}