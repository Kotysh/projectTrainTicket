/**
 * Created by Дмитрий on 27.02.2018.
 */
function validCustomer() {

    var firstName = document.getElementById('firstName');
    var erFirstName = document.getElementById('erFirstName');
    var middleName = document.getElementById('middleName');
    var erMiddleName = document.getElementById('erMiddleName');
    var lastName = document.getElementById('lastName');
    var erLastName = document.getElementById('erLastName');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";
    var countNotValid = 0;

    if (firstName.value.trim().length == 0 || firstName.value.trim().length > 40){
        erFirstName.innerHTML = " имя должно содержать от 0 до 40 символов";
        firstName.style.border = redBorder;
        erFirstName.style.color = "red";
        countNotValid++;
    }
    else{
        firstName.style.border = noBorder
    }
    console.log("Проверяем второе поле");
    if (middleName.value.trim().length == 0 || middleName.value.trim().length > 40){
        erMiddleName.innerHTML = " отчество должно содержать от 0 до 40 символов";
        middleName.style.border = redBorder;
        erMiddleName.style.color = "red";
        countNotValid++;
    }else{
        middleName.style.border = noBorder;
    }
    if(lastName.value.trim().length == 0 || lastName.value.trim().length > 40){
        erLastName.innerHTML = " фамилия должна содержать от 0 до 40 символов";
        lastName.style.border = redBorder;
        erLastName.style.color = "red";
        countNotValid++;
    }else{
        lastName.style.border = noBorder;
    }

    var year = document.getElementById('year');
    var month = document.getElementById('month');
    var day = document.getElementById('day');
    var noDate = document.getElementById('noDate');

    if (year.value.trim().length == 0 ||
        year.value.trim().length > 4 ||
        !(isInteger(year.value.trim())) ||
        month.value.trim().length == 0 ||
        month.value.trim() < 1 ||
        month.value.trim() > 12 ||
        !(isInteger(month.value.trim())) ||
        day.value.trim().length == 0 ||
        day.value.trim() < 1 ||
        day.value.trim() > 31 ||
        !(isInteger(day.value.trim())) ||
        validYear(parseInt(year.value.trim()), parseInt(month.value.trim()), parseInt(day.value.trim())))
    {
        year.style.border = redBorder;
        month.style.border = redBorder;
        day.style.border = redBorder;
        noDate.innerHTML = " неверный формат даты";
        noDate.style.color = "red";
    }else{
        year.style.border = noBorder;
        month.style.border = noBorder;
        day.style.border = noBorder;
        noDate.innerHTML = "";
    }

    var radioGender = document.getElementsByName('gender');
    var perGender = check(radioGender);
    var gender = document.getElementById('noGender');

    if (perGender == 0){
        gender.innerHTML = "укажите пол";
        gender.style.color = "red";
        countNotValid++;
    }else{
        gender.innerHTML = "";
    }

    var radioDocument = document.getElementsByName('document');
    var perDocument = check(radioDocument);

    var doc = document.getElementById('noDocument');

    if (perDocument == 0){
        doc.innerHTML = "укажите тип документа";
        doc.style.color = "red";
        countNotValid++;
    }else{
        doc.innerHTML = "";
    }

    var docNumber = document.getElementById('documentNumber');
    if (docNumber.value.trim().length == 0 || docNumber.value.trim().length > 40){
        docNumber.style.border = redBorder;
        countNotValid++;
    }else{
        docNumber.style.border = noBorder;
    }

    var email = document.getElementById('email');
    if (email.value.trim().length == 0 || email.value.trim().length > 64){
        email.style.border = redBorder;
        countNotValid++;
    }else{
        email.style.border = noBorder;
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
        document.getElementById('message').innerHTML = "заполните поля выделенные красным";
        var mes = document.getElementById('message');
        mes.style.color = "red";
    }


    return countNotValid == 0;
}

function check(inp)
{

    for (var i = 0; i < inp.length; i++) {
        if (inp[i].type == "radio" && inp[i].checked) {
            console.log(inp[i]);
            return 1;
        }
    }
    return 0;
}
function isInteger(num) {
    return num%1 == 0 && num>0;
}
function validYear(year, month, day) {

    var fevbool = year%4;
    var result = 0;
    switch (month){
        case 1:
        case 3:
        case 4:
        case 7:
        case 8:
        case 10:
        case 12:
        {
            result = day-31;
            break;
        }
        case 4:
        case 5:
        case 6:
        case 7:
        {
            result = day-30;
            break;
        }
        case 2:
        {
            if (fevbool == 0) result = day-29;
            else if (fevbool > 0) result = day-28;
            break;
        }
    }
    return result>0;

}
