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
                getCommentList(cmtData.bno);
            }
        })
    }
})

async function spreadCommentFromServer(bno){
    try {
        const resp = await fetch("/comment/" + bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno){
    spreadCommentFromServer(bno).then(result=>{
        console.log(result);
        const ul = document.getElementById("cmtListArea");
        if(result.length>0){
            ul.innerHTML = "";
            for(let cvo of result){
                let li = `<li data-cno="${cvo.cno}"><div>${cvo.writer}`;
                li += `<div><input type="text" id="cmtTextMod" value="${cvo.content}">`;
                li += `<button type="button" class="modBtn">수정</button>`;
                li += `<button type="button" class="delBtn">삭제</button></div></div></li>`;
                li += `<span>${cvo.reg_date}</span></li>`;
                ul.innerHTML += li;
            }
        }else{
            let li = `<li>Comment가 없습니다.</li>`;
            ul.innerHTML = li;
        }
    })
}

async function removeCommentFromServer(cnoVal){
    try {
        const resp = await fetch("/comment/" +cnoVal);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function updateCommentFromServer(cnoVal, cmtTextMod, writer){
    try {
        const url = "/comment/modify";
        const config = {
            method: "post",
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify({cno:cnoVal, content:cmtTextMod, writer:writer})
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains("modBtn")){
        let cnoVal = e.target.dataset.cno;
        let ul = e.target.closest('ul');
        let cmtTextMod = ul.querySelector('#cmtTextMod').value;
        let writer = e.target.dataset.writer;

        updateCommentFromServer(cnoVal, cmtTextMod, writer).then(result=>{
            if(result>0){
                alert("댓글 수정 완료");
                getCommentList(cmtData.bno);
            }else{
                alert("댓글 수정 실패");
            }
        })
    }
    if(e.target.classList.contains("delBtn")){
        let cnoVal = e.target.dataset.cno;
        console.log(cnoVal);
        removeCommentFromServer(cnoVal).then(result=>{
            if(result>0){
                alert("댓글 삭제 완료");
                getCommentList(cmtData.bno);
            }else{
                alert("댓글 삭제 실패");
            }
        })
    }
})