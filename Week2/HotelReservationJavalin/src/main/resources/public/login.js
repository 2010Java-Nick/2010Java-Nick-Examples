window.onload = function() {
   let path =  window.location.search;
   console.log(path);
   let error = document.getElementById("error");
   error.innerHTML = path;
}