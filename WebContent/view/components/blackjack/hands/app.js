
//これをimportするjspにはこのボタンがない⇒play.jspにimportする
const standBtn = document.querySelector(".form-button.stand");

const dealerhand = document.querySelectorAll(".dealerhand img");

let count = 0;

document.addEventListener("DOMContentLoaded", () => {

    setTimeout(() => {

    	const setIntervalId = setInterval(() => {
    		  console.log("Hello!");
    		  count++;
    		  console.log(count > 5);

    		  if(count > 5){
    		    	clearInterval(setIntervalId);
    		    }
    	},1000)


//    	dealerhand.forEach((img,index) => {
//    	    	  if(index > 1){
//    	        img.style.transform = "translate(0px,0px)";
//    	    	  }
//    	      });
//

    }, 1000);
  });












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


