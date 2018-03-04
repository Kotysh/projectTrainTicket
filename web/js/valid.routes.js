/**
 * Created by Дмитрий on 07.02.2018.
 */

function validRoute () {

    var firstField = document.getElementById('stationOne').value.trim();
    var secondField = document.getElementById('stationTwo').value.trim();
    var dateField = document.getElementById('date').value.trim();
    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";
    var bold = "bold";
    var countNotValid = 0;

    if (firstField.length == 0 || firstField.length > 256){
        document.getElementById('stationOne').style.border = redBorder;
        countNotValid++;
    }else{
        document.getElementById('stationOne').style.border = noBorder;
    }
    if(secondField.length == 0 || secondField.length > 256){
        document.getElementById('stationTwo').style.border = redBorder;
        countNotValid++;
    }else{
        document.getElementById('stationTwo').style.border = noBorder;
    }
    if(dateField.length == 0){
        document.getElementById('date').style.border = redBorder;
        countNotValid++;
    }else{
        document.getElementById('date').style.border = noBorder;
    }

    if (countNotValid > 0){
        document.getElementById('message').innerHTML = "Заполните форму";
        var mes = document.getElementById('message');
        if (firstField.length > 256 || secondField.length > 256){
            document.getElementById('mess').innerHTML = "Слишком длинное имя станции(максимум 256 символов)";
            document.getElementById('mess').style.color = "#f03600";
            document.getElementById('mess').style.fontWeight = bold;
        }
        mes.style.color = "#f03600";
        mes.style.fontWeight = bold;
        return false;
    }


    document.getElementById('message').innerHTML = "&nbsp;";
    return true;

}
