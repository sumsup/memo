window.onload = () => {
    document.querySelector('#memo-ta').focus();

    const $memoTextarea = document.querySelector('#memo-ta');

    document.querySelector('#submit-memo').addEventListener('click', registerMemo);

    selectMemo();

    function outputMemo(memo, outType) {
        if (outType === 'register') {
            memo = convertEnterToDiv($memoTextarea.value);

        }
        document.querySelector('#memo-output').insertAdjacentHTML('afterbegin', '<div class="memo-item">'
            + memo + '</div>');

        $memoTextarea.value = '';
        $memoTextarea.focus();
    }

    function convertEnterToDiv(memo) {
        memo = '<div>' + memo.replaceAll('\n', '</div><div>&#8302;') + '</div>';
        return memo;
    }

    function registerMemo() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    console.log(xhr.responseText);
                    outputMemo(null, 'register');
                }
                else {
                    console.error(xhr.responseText);
                }
            }
        }
        xhr.open('POST', 'http://localhost:8080/memo/register');
        xhr.setRequestHeader('Content-Type', 'application/json');
        let memo = $memoTextarea.value;
        memo = convertEnterToDiv(memo);
        xhr.send(jsonParser(memo));
    }

    function jsonParser(memo) {
        let parsedJson = '{ "writerId" : 1,' +
            '"memo": "' + memo + '"}';
        return parsedJson;
    }

    function selectMemo() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    let allMemo = xhr.response;
                    showAllMemo(allMemo);
                }
                else {
                    console.error(xhr.responseText);
                }
            }
        }
        xhr.open('POST', 'http://localhost:8080/memo/search');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.responseType = 'json';
        let memo = $memoTextarea.value;
        xhr.send(jsonParser(memo));
    }

    function showAllMemo(memoList) {
        for(let i = 0; i < memoList.length; i++) {
            outputMemo(memoList[i]["memo"]);
        }

    }

}