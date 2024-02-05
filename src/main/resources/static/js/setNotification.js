// // DOMContentLoaded イベントを使ってスクリプトを実行
// document.addEventListener('DOMContentLoaded', function() {
//     // createNotification 関数を呼び出す前に通知用の要素を生成
//     var notificationContainer = document.createElement('div');
//     notificationContainer.id = 'notification';
//     notificationContainer.hidden = true; // 要素を最初から非表示にする
//     document.body.appendChild(notificationContainer);

//     // createNotification 関数を呼び出す
//     createNotification('');
// });

// function createNotification(message) {
//     var notificationElement = document.getElementById('notification');
//     if (notificationElement) {
//         notificationElement.textContent = message;
//     } else {
//         console.error("Element with id 'notification' not found.");
//     }
// }
// 新しい div 要素を作成
var notificationElement = document.createElement('div');

// id 属性を設定
notificationElement.id = 'notification';

// hidden 属性を設定
notificationElement.hidden = true;

// 生成した要素を body 内に追加
document.body.appendChild(notificationElement);
