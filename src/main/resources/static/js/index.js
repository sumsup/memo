window.onload = function () {
    let memos;
    searchMemos();

    function searchMemos() {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    console.log(xhr.response);
                    memos = xhr.response;
                    outputMemos(memos);

                } else {
                    console.error("error stack trace : ", xhr.response);

                }
            }
        };

        xhr.responseType = 'json';
        xhr.open("POST", "http://localhost:8080/memo/search");
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({}));
    }

    function outputMemos(memos) {
        var elem = document.getElementById("output_memos");
        for (var i = 0; i < memos.length; i++) {
            var memo = '<div class="memo_div">' + memos[i].memo + '</div>';
            elem.insertAdjacentHTML('afterbegin', memo);
        }
    }

}