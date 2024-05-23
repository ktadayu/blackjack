/**
 *
 */

'use strict'

let i=0;

let tip_1 = document.getElementById('tip-1');
tip_1.addEventListener('click', function (e) {
    // 1.IDによるノード要素の取得
    let counter = document.getElementById('betting-counter');
    // 2.テキストの置換
    i++;
//    let j = counter.textContent+i;
//    counter.textContent = Math.min(j,100)
    counter.textContent =+i

}, false);


let tip_5 = document.getElementById('tip-5');
tip_5.addEventListener('click', function (e) {
    // 1.IDによるノード要素の取得
    let counter = document.getElementById('betting-counter');
    // 2.テキストの置換
    i = i +5;
   counter.textContent=+i

}, false);


let tip_10 = document.getElementById('tip-10');
tip_10.addEventListener('click', function (e) {
    // 1.IDによるノード要素の取得
    let counter = document.getElementById('betting-counter');
    // 2.テキストの置換
    i = i +10;
    counter.textContent=+i
//    let j = counter.textContent + i;
//    counter.textContent = Math.min(j,100)
}, false);



let btn_2 = document.getElementById('reset-button');
btn_2.addEventListener('click', function (e) {
    i = 0;
    let counter = document.getElementById('betting-counter');
    counter.textContent = i;
}, false);
