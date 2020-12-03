$(document).ready(function (){
   let el = document.querySelector(".nav-ul");
   el.style.height = window.innerHeight - 70 + "px";
   $(window).resize(function (){
       $(".nav-ul").css("height",$(window).height()-70);
   });
})