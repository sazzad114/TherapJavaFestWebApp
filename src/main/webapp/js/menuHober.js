/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 8/9/12
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
function changeMenuBackground() {

    var path = window.location.pathname;

    var menu = document.getElementById("menu");
    var a_elements = menu.getElementsByTagName("a");

    //alert("entered here");
    var alertString = path+" ";

    for (var i = 0, len = a_elements.length; len > i; i++) {

        alertString += a_elements[i].pathname +" ";
        if (path.search(a_elements[i].pathname) >= 0 || (path.search("greetings") >=0 && a_elements[i].pathname.search("greetings") >= 0)) {

            a_elements[i].style.backgroundColor = '#f2622d';
        }
        else {

            a_elements[i].style.backgroundColor = '#0086df';
        }
    }
   // alert(alertString);
}