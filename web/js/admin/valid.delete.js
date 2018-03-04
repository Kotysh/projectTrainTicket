/**
 * Created by Дмитрий on 02.03.2018.
 */
function validDelete(name) {

    var delName = document.getElementById(name);
    var mesDel = document.getElementById('mesDel');

    var redBorder = "2px solid red";
    var noBorder = "2px solid #cccccc";

    if (delName.value.trim().length == 0 || delName.value.trim().length > 40 || !(isInteger(delName.value.trim()))) {
        mesDel.innerHTML = "укажите id удаляемого объекта (целое число)";
        mesDel.style.color = "red";
        delName.style.border = redBorder;
        return false;
    } else {
        mesDel.innerHTML = "";
        delName.style.border = noBorder;
        return true;
    }


}
function isInteger(num) {
    return num%1 == 0 && num>0;
}