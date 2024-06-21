
//これをimportするjspにはこのボタンがない⇒play.jspにimportする
const standBtn = document.querySelector(".form-button.stand");

const dealerHand = document.querySelectorAll(".dealerhand img");
const dealerSum = document.querySelector(".dealer-sum h4")

let count = 0;

document.addEventListener("DOMContentLoaded", () => {

  //１枚１秒で出す
  dealerHand.forEach((img, index) => {
	  count++
    if (index > 1) {
      //1秒遅らせる
      setTimeout(() => {
    	  console.log(index);
        img.style.transform = "translate(0px,0px)";
      }, index * 200);
    }
  });
  //ゲームの途中でも呼び出されてエラーとなるので何とかする。
  	setTimeout(()=> {
  		dealerSum.style.opacity = 1;
  	},dealerHand.length * 300);
});

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


