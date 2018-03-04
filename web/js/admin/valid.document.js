/**
 * Created by Дмитрий on 28.02.2018.
 */
function validDocument() {

    var doc = document.getElementById('document');
    var countNotValid = 0;

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (doc.value.trim().length == 0 || doc.value.trim().length > 256){

        doc.style.border = redBorder;
        document.getElementById('message').innerHTML = "заполните поле (максимум 256 символов)";
        document.getElementById('message').style.color = "red";
        countNotValid++;

    }else{

        doc.style.border = noBorder;
        document.getElementById('message').innerHTML = "";

    }

    return countNotValid == 0;

}
