window.onload = function () {
    var memos;
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
                    console.error(xhr.responseText);

                }
            }
        };
        xhr.responseType = 'json';
        xhr.open("GET", "http://localhost:8080/memo/search");
        xhr.send();
    }

    function outputMemos(memos) {
        var elem = document.getElementById("output_memos");
        for (var i = 0; i < memos.length; i++) {
            var memo = '<div class="memo_div">' + memos[i].memo + '</div>';
            elem.insertAdjacentHTML('afterbegin', memo);
        }
    }

}