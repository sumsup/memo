window.onload = () => {

    // const HOST_ADDRESS = "http://localhost:8080";
    const HOST_ADDRESS = "http://220.76.63.77:8080";
    const $memoTextarea = document.querySelector('#memo-ta');

    memoStarter(); // 진입점.

    function memoStarter() {
        document.querySelector('#memo-ta').focus();
        eventListeners();
        selectMemo();
    }

    function eventListeners() {
        document.querySelector('#submit-memo').addEventListener('click', registerMemo);
        // 이벤트 위임. 클래스에 이벤트를 바인딩 하려면, 상위요소에 리스너를 걸고. event 객체에서 눌려진 요소를 찾아서 작업.
        document.querySelector('#memo-output').addEventListener('click', deleteMemo);
    }

    function outputMemo(memo, outType, memoId) {
        if (outType === 'register') {
            memo = convertEnterToDiv($memoTextarea.value);
        }
        document.querySelector('#memo-output').insertAdjacentHTML('afterbegin', '<div class="memo-item"><div '
        + 'class="memo-contents">' + memo + '</div><div class="memo-del-div"><button id="memo-num-' 
        + memoId + '" class="memo-del-btn">삭제</button></div></div>');

        $memoTextarea.value = '';
        $memoTextarea.focus();
    }

    function convertEnterToDiv(memo) {
        memo = '<div>' + memo.replaceAll('\n', '</div><div>&#8302;') + '</div>';
        return memo;
    }

    function registerMemo() {

        // 전달할 파라미터에 map으로 변수를 담아야 함.
        // 이렇게 파라미터로 전달하는 형식 말고. 빌더 패턴으로 원하는 값들 설정하도록 구현해 보자.
        // commonXHR('POST', '/memo/register', outputMemo, );

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    const registeredMemoId = xhr.responseText;
                    outputMemo(null, 'register', registeredMemoId);
                }
                else {
                    console.error(xhr.responseText);
                }
            }
        }
        xhr.open('POST', HOST_ADDRESS + '/memo/register');
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
                    console.error(xhr.response);
                }
            }
        }
        xhr.open('POST', HOST_ADDRESS + '/memo/search');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.responseType = 'json';
        let memo = $memoTextarea.value;
        xhr.send(jsonParser(memo));
    }

    function showAllMemo(memoList) {
        for(let i = 0; i < memoList.length; i++) {
            outputMemo(memoList[i]["memo"], null, memoList[i]["id"]);
        }
    }

    function deleteMemo() {
        if (event.target.getAttribute('class') !== 'memo-del-btn') {
            return;
        }
        const memoId = event.target.getAttribute('id').replace('memo-num-','');

        commonXHR('DELETE', '/memo/delete/', deleteMemoFromDOM, memoId);
    }

    function deleteMemoFromDOM(memoId) {
        // DOM에서 memo id에 해당하는 요소를 기준으로 첫번째 .memo-item 요소를 가져온다. 삭제한다.
        var $memoItemElem = document.querySelector('#memo-num-' + memoId).closest('.memo-item').remove();
    }

    function commonXHR(method, api, callbackFunction, param) {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    callbackFunction(param);
                }
                else {
                    console.error(xhr.response);
                }
            }
        }
        xhr.open(method, HOST_ADDRESS + api + param);
        xhr.send();
    }

    // var commonXHR = function(method) {
    //     let method;
    //     let api;
    //   };

}
