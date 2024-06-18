const container = document.querySelector(".animation-container");
const globalSection = document.querySelector(".global-section");
const animationCahracters = document.querySelector(".animation-characters");

document.addEventListener("DOMContentLoaded", function () {
	animationCahracters.classList.add("viewing");
	globalSection.style.opacity = 0;
//  setTimeout(() => {
//    globalSection.style.opacity = 1;
//  }, 2800);
	animationCahracters.addEventListener('animationend', () => {
	  globalSection.style.opacity = 1;
	})
});