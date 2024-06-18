const bettingFormContainer = document.querySelector(".betting");
const transitionalAnimation = document.querySelector(".transitional-animation");

document.addEventListener("DOMContentLoaded", function () {
	transitionalAnimation.classList.add("transition-start");
	bettingFormContainer.style.opacity = 0;
//  setTimeout(() => {
//    globalSection.style.opacity = 1;
//  }, 2800);
	transitionalAnimation.addEventListener('animationend', () => {
	  bettingFormContainer.style.opacity = 1;
	})
});