$(document).ready(() => {
    console.log("Ready!!");

    // 1. Permissionの確認
    if (!Push.Permission.has()) {
        // // 2. Permissionのリクエスト
        // Push.Permission.request(() => {
        //     console.log("onGranted!!");
        //     const status = Push.Permission.get(); // Status
        //     $("#my_status").text(status);

        //     // subscribe 関数を呼び出す
        //     subscribe();
        // }, () => {
        //     console.log("onDenied!!");
        //     const status = Push.Permission.get(); // Status
        //     $("#my_status").text(status);

        //     // 通知権限がない場合は subscribe 関数を呼び出さない
        // });
    } else {
        // 通知権限がすでにある場合は subscribe 関数を呼び出す
        subscribe();
    }

    function subscribe() {
        const notificationDiv = document.getElementById('notification');

        function poll() {
            $.ajax({
                url: '/subscribe',
                method: 'GET',
                success: function (data) {
                    notificationDiv.innerText = data;
                    console.log(data + "データです");

                    // データがあれば通知を表示
                    if (data != "") {
                        console.log("通知成功");
                        showNotification(data);
                    }

                    poll();
                }
                // error: function () {
                //     poll();
                // }
            });
        }

        // subscribe 関数内で最初に一度 poll を呼び出す
        poll();
    }

    function showNotification(data) {
        // 3. Notificationの実行
        Push.create("⚠賞味・消費期限通知⚠", {
            body: data + "が賞味・消費期限に近づいています！",
            // icon: "./images/reimu.png",
            tag: "myTag",
            timeout: 24000,
            vibrate: [100, 100, 100],
            onClick: function () {
                // 通知がクリックされた場合の設定
                window.open('https://localhost:8443/purchaseH', '_blank');
            }
        });
    }
});
