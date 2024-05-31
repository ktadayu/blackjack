
'use strict'

/*
 * bet画面
 */
let i=0;
let k=0;

let tip_1 = document.getElementById('tip-1');
tip_1.addEventListener('click', function (e) {
    // 1.IDによるノード要素の取得
    //let counter = document.getElementById('betting-counter');
    let form = document.getElementById('betting-form');
    // 2.テキストの置換
    i++;
//    let j = counter.textContent+i;
//    counter.textContent = Math.min(j,100)
    //counter.textContent= Math.min(i,10);
    form.value=Math.min(i,10);

}, false);


let tip_5 = document.getElementById('tip-5');
tip_5.addEventListener('click', () =>  {
    // 1.IDによるノード要素の取得
    //let counter = document.getElementById('betting-counter');
    let form = document.getElementById('betting-form');
    // 2.テキストの置換
    i = i +5;
    //counter.textContent= Math.min(i,10);
    form.value=Math.min(i,10);
}, false);


let tip_10 = document.getElementById('tip-10');
tip_10.addEventListener('click', function (e) {
    // 1.IDによるノード要素の取得
  //  let counter = document.getElementById('betting-counter');
    let form = document.getElementById('betting-form');
    // 2.テキストの置換
    i = i +10;
   // counter.textContent= Math.min(i,10);
    form.value=Math.min(i,10);
//    let j = counter.textContent + i;
//    counter.textContent = Math.min(j,100)
}, false);



let btn_2 = document.getElementById('reset-button');
btn_2.addEventListener('click', function (e) {
    i = 0;
   // let counter = document.getElementById('betting-counter');
    let form = document.getElementById('betting-form');
   // counter.textContent = i;
    form.value= i;
}, false);



/*
 *playing画面
 */
//function opt1() {
//    document.opt_form.value = 'hit'
//    document.opt_form.submit();
//}s
//function opt2() {
//	document.opt_form.value = 'stand'
//	document.opt_form.submit();
//}
//

