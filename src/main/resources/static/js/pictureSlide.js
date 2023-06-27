var sign = 2;
function showPic(index) {
    var focusImg = document.getElementById("focusImage");
    var imgSrc = "../picture/pic";
    imgSrc =imgSrc+ index + ".jpg";
	focusImg.src = imgSrc;
    var lis = document.getElementsByClassName("focus")[0].getElementsByTagName("li");    
    for (var i = 0; i < lis.length; i++) {
        lis[i].className = "";
    }
    lis[index - 1].className = "cur";
}
function setCurrentPic() {
    showPic(sign);
    sign++;
    if (sign == 6) {
        sign = 1;
    }
}
window.onload = function () {
    showPic(1);
}
window.setInterval("setCurrentPic()",2000);
