const container = document.querySelector("#animation-container");
const globalSection = document.querySelector(".global-section");
const animateTitle = document.querySelector(".animate-title");

document.addEventListener("DOMContentLoaded", function () {
  animateTitle.classList.add("inview");
  globalSection.style.opacity = 0;
//  setTimeout(() => {
//    globalSection.style.opacity = 1;
//  }, 2800);
  animateTitle.addEventListener('animationend', () => {
	  globalSection.style.opacity = 1;
	})
});