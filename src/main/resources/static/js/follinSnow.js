// マウスストーカー関連の要素（任意で変更してください）
const mouseStalker = "#stkr";           // マウスストーカーになる要素を指定
const mouseTarget = ".stkr-target";     // リンクなどアクションを付けたい要素を指定
const mouseStalkerArea = window;        // マウスストーカーが機能する要素を指定

// 処理で使う変数たち
let stkrSize, stkrPosX, stkrPosY, cssPosAjust, scale;

// ページ読み込み時に変数を初期化
$(document).ready(function() {
    stkrSize = parseInt($(mouseStalker).css("width").replace(/px/, ""));
    stkrPosX = parseInt($(mouseStalker).css("left").replace(/px/, ""));
    stkrPosY = parseInt($(mouseStalker).css("top").replace(/px/, ""));
    cssPosAjust = stkrPosX + (stkrSize / 2);
    scale = 1;
});

// 追従用の処理
$(mouseStalkerArea).hover(function () {
    $(mouseStalkerArea).mousemove(function (e) {
        let x = e.clientX - cssPosAjust;
        let y = e.clientY - cssPosAjust;
        $(mouseStalker).css({
            "transform": "translate(" + x + "px," + y + "px) scale(" + scale + ")",
        });
    });
});

// リンクホバーの処理
$(mouseTarget).hover(function (e) {
    scale = 2;
    let x = e.clientX - cssPosAjust;
    let y = e.clientY - cssPosAjust;
    $(mouseStalker).css({
        "transform": "translate(" + x + "px," + y + "px) scale(" + scale + ")",
    });
}, function () {
    scale = 1;
});
