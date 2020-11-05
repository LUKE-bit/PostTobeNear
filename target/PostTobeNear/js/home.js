let userPage = document.getElementsByClassName("userPage")[0];
let slideBar = document.getElementsByClassName("slideBar")[0];
let close = document.getElementsByClassName("close")[0];

userPage.addEventListener("click", () => {
  slideBar.classList.add("showSlide")
 slideBar.classList.remove("hiddenSlide") 
})

close.addEventListener("click", () => {
  slideBar.classList.add("hiddenSlide")
  slideBar.classList.remove("showSlide")

})