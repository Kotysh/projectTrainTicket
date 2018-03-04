/**
 * Created by Дмитрий on 07.02.2018.
 */
function validPlace() {

    var firstName = document.getElementById('firstName');
    var middleName = document.getElementById('middleName');
    var lastName = document.getElementById('lastName');

    var birthday = document.getElementById('birthday');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";
    var countNotValid = 0;
    var now = new Date();


    if (firstName.value.trim().length == 0 || firstName.value.trim().length > 40){
        firstName.style.border = redBorder;
        countNotValid++;
    }else{
        firstName.style.border = noBorder;
    }
    if (middleName.value.trim().length == 0 || middleName.value.trim().length > 40){
        middleName.style.border = redBorder;
        countNotValid++;
    }else{
        middleName.style.border = noBorder;
    }
    if(lastName.value.trim().length == 0 || lastName.value.trim().length > 40){
        lastName.style.border = redBorder;
        countNotValid++;
    }else{
        lastName.style.border = noBorder;
    }
    if (birthday.value.length == 0){
        birthday.style.border = redBorder;
        countNotValid++;
    }else{
        birthday.style.border = noBorder;
    }


    var email = document.getElementById('email');
    if (email.value.trim().length == 0){
        email.style.border = redBorder;
        countNotValid++;
    }else{
        email.style.border = noBorder;
    }

    var docNumber = document.getElementById('docNumber');
    if (docNumber.value.trim().length == 0 || docNumber.value.trim().length > 40){
        docNumber.style.border = redBorder;
        countNotValid++;
    }else{
        docNumber.style.border = noBorder;
    }


    var radioGender = document.getElementsByName('gender');
    var perGender = check(radioGender);

    var gender = document.getElementById('noGender');

    if (perGender == 0){
        gender.innerHTML = "Укажите пол!";
        gender.style.color = "red";
        countNotValid++;
    }else{
        gender.innerHTML = "";
    }

    var radioDocument = document.getElementsByName('document');
    var perDocument = check(radioDocument);

    var doc = document.getElementById('noDocument');

    if (perDocument == 0){
        doc.innerHTML = "Укажите тип документа!";
        doc.style.color = "red";
        countNotValid++;
    }else{
        doc.innerHTML = "";
    }

    var place = document.getElementsByName('place');
    var perPlace = check(place);

    var pl = document.getElementById('noPlace');

    if (perPlace == 0){
        pl.innerHTML = "Выберите место!";
        pl.style.color = "red";
        countNotValid++;
    }else{
        pl.innerHTML = "";
    }

    var telephone = document.getElementById('telephone');
    var erTelephone = document.getElementById('erTelephone');
    if ((telephone.value.trim() == 0) || (telephone.value.trim().length == 10 &&  isInteger(telephone.value.trim()))){
        telephone.style.border = noBorder;
        erTelephone.innerHTML = "";
    }else{
        telephone.style.border = redBorder;
        erTelephone.innerHTML = "номер записан не правильно";
        erTelephone.style.color = "red";
        countNotValid++;
    }


    if (countNotValid > 0){
        document.getElementById('message').innerHTML = "Поля отмеченные звездочкой (*) - обязательно должны быть заполнены!";
        var mes = document.getElementById('message');
        mes.style.color = "red";
        return false;
    }

    return true;


}
function isInteger(num) {
    return num%1 == 0 && num>0;
}
function check(inp)
{

    for (var i = 0; i < inp.length; i++) {
        console.log("все ок");
        if (inp[i].type == "radio" && inp[i].checked) {
            console.log(inp[i]);
            return 1;
        }
    }
    return 0;
}
