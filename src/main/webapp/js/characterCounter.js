function textCounter(field, cntfieldId, maxlimit) {
    if (field.value.length > maxlimit)
        field.value = field.value.substring(0, maxlimit);
    else
        document.getElementById(cntfieldId).innerHTML = maxlimit - field.value.length;
}
