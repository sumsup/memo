window.onload = () => {
    generateEventListener();

    function registerMemo(memo) {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    console.log(xhr.responseText);
                }
            }
            else {
                console.error(xhr.responseText);
            }
        }
        xhr.open('POST', 'http://localhost:8080/memo/register');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(jsonParser(memo));
    }

    function submitMemo() {
        let memoContents = document.getElementById('memo').value;

        registerMemo(memoContents);
    }

    function generateEventListener() {
        document.querySelector('#memo-submit').addEventListener('click', submitMemo);
    }

    function jsonParser(memo) {
        let parsedJson = '{ "writerId" : 1,' +
            '"memo": "' + memo + '" }';

        return parsedJson;
    }

}