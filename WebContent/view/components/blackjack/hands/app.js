
//調査用
//document.addEventListener('click', function(event) {
//const clickedElement = event.target;
//console.log(clickedElement);
//});
//
//document.addEventListener('mouseover', function(event) {
//	const clickedElement = event.target;
//	console.log(clickedElement);
//});


const standBtn = document.querySelector(".form-button.stand");

const dealerHand = document.querySelectorAll(".inline-block img");
const dealerSum = document.querySelector(".dealer-sum h4")

const checkEndGame = document.querySelector(".flip");
const frontCard = document.querySelector(".front-card");
const backCard = document.querySelector(".back-card");

const inlineBlockImg = document.querySelectorAll(".inline-block > img");



let count = 0;

document.addEventListener("DOMContentLoaded", () => {

	//裏返し処理
	if(checkEndGame != null){
		setTimeout(()=> {
			frontCard.classList.add("flipped");
			backCard.classList.add("flipped");
	  	},100);
	}


  //カード配布処理
  dealerHand.forEach((img, index) => {
	  count++
    if (index > 1) {
      //１枚ずつ遅らせる
      setTimeout(() => {
        img.style.transform = "translate(0px,0px)";
      }, index * 200);
    }
  });

  //合計点表示処理
  //ゲームの途中でも呼び出されてエラーとなるので何とかする。
  	setTimeout(()=> {
  		dealerSum.style.opacity = 1;
  	},dealerHand.length * 300);


});

//////////////////////////

//    	dealerhand.forEach((img,index) => {
//    	    	  if(index > 1){
//    	        img.style.transform = "translate(0px,0px)";
//    	    	  }
//    	      });
//

//遷移で毎回呼び出される。
//setTimeout(()=>{
//	console.log("hello");
//},2000);

//nanikaはゲームが終わると存在するようになってendFlag != nullとなるもの
//const endFlag = document.querySelector("#nanika");
//
//if(endFlag != null){
//
//}

//standBtn.addEventListener("click", () => {
//	setTimeout(() => {
//	    dealerhand.forEach((img,index) => {
//	    	  if(index > 1){
//	        img.style.transform = "translate(-40px,+40px)";
//	        console.log("hello");
//	    	  }
//	      });
//	    }, 100);
//})

//console.log(dealerhand);

//window.addEventListener("load", () => {
//
//	console.log("loaded");
//
//    setTimeout(() => {
//      dealerhand.forEach((img,index) => {
//    	  if(index > 1){
//        img.style.transform = "translate(-40px,-40px)";
//        console.log("hello");
//    	  }
//      });
//    }, 1000);
//  });