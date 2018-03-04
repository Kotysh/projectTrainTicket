/**
 * Created by Дмитрий on 02.03.2018.
 */

function validWagon() {

    var trainId = document.getElementById('trainId');
    var erTrain = document.getElementById('erTrain');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";
    var countNotValid = 0;

    if (trainId.value.trim().length == 0 || !(isInteger(trainId.value.trim())) || trainId.value.trim().length > 40) {
        erTrain.innerHTML = "укажите целое число";
        erTrain.style.color = "red";
        trainId.style.border = redBorder;
        countNotValid++;
    }
    else {
        erTrain.innerHTML = "";
        trainId.style.border = noBorder;
    }


    var orderWagon = document.getElementById('orderWagon');
    var erOrder = document.getElementById('erOrder');

    if (orderWagon.value.trim().length == 0 || !(isInteger(orderWagon.value.trim())) || orderWagon.value.trim()>100) {
        erOrder.innerHTML = "укажите целое число (максимум 100)";
        erOrder.style.color = "red";
        orderWagon.style.border = redBorder;
        countNotValid++;
    }
    else {
        erOrder.innerHTML = "";
        orderWagon.style.border = noBorder;
    }

    return countNotValid == 0;

}
function isInteger(num) {
    return num%1 == 0 && num>0;
}