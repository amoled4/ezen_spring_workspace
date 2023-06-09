async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method: 'post',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtPostBtn').addEventListener('click', ()=>{
    const cmtText = document.getElementById('cmtText').value;
    console.log(cmtText);
    if(cmtText == null || cmtText == ""){
        alert("댓글을 입력해 주세요.");
        document.getElementById('cmtText').focus();
        return false;
    } else{
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtText
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result =>{
            if(result>0){
                alert("댓글 등록 성공");
                document.getElementById('cmtText').value = "";
            }
            // printCommentList(cmtData.bno);
        })
    }
})