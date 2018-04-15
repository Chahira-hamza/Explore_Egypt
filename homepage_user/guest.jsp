
<%@page import="newpackage.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="styleother.css">
<link href='https://fonts.googleapis.com/css?family=Bungee Inline' rel='stylesheet'>
</head>

<body>

<div class="slideshow-container">

<div class="mySlides fade">
  <div class="numbertext">1 / 5</div>
  <img src="chrome-extension://cepomndpolfdigigicogmfiaelajoopg/nl1.jpg"  height="460px" width="1024px">
  <div class="text">Egypt The Land of Wonders</div>
</div>

<div class="mySlides fade">
  <div class="numbertext">2 / 5</div>
  <img src="chrome-extension://cepomndpolfdigigicogmfiaelajoopg/MiddleEast-Egypt-Aswan-Abu-Simbel-Guest.jpg"  height="460px" width="1024px">
  <div class="text">Discover</div>
</div>


<div class="mySlides fade">
  <div class="numbertext">3 / 5</div>
  <img src="chrome-extension://cepomndpolfdigigicogmfiaelajoopg/EBF2BD6C-F561-7082-7EA0-E5F5A69FB5F3.jpg"  height="460px" width="1024px">
  <div class="text">Leap Through Time</div>
</div>


<div class="mySlides fade">
  <div class="numbertext">4 / 5</div>
  <img src="chrome-extension://cepomndpolfdigigicogmfiaelajoopg/a404732f54630cd36ab03e592c1520e7.jpg"  height="460px" width="1024px">
  <div class="text">Find Red Sea Gems</div>
</div>

<div class="mySlides fade">
  <div class="numbertext">5 / 5</div>
  <img src="chrome-extension://cepomndpolfdigigicogmfiaelajoopg/Nile-river.jpg" height="460px" width="1024px">
  <div class="text">Cruise The River Nile</div>
</div>

<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
<a class="next" onclick="plusSlides(1)">&#10095;</a>

</div>
<br>

<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
  <span class="dot" onclick="currentSlide(4)"></span> 
  <span class="dot" onclick="currentSlide(5)"></span> 
  
 
</div>


<script>
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1;}    
  if (n < 1) {slideIndex = slides.length;}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}
</script>

 <p align="center">
         
     <%
         User user = (User) session.getAttribute("user");
     %>

    Welcome <%=  user.getUsername() %> !
    
    
 </p> 
 
 
<div class = "navbar">
<ul>
<li><a href= "MuseumsPage.jsp">Museums</a></li>
<li><a href= "#">Maps</a></li>
<li><a href= "#">History</a></li>
<li><a href= "#">Era</a></li>
<li><a href="homepage.jsp">Logout</a></li>
</ul>
</div>



</body>
</html> 