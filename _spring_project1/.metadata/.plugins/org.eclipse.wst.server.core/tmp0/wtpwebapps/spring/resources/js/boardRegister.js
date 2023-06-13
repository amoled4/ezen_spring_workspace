//  trigger를 클릭하면 input file이 클릭되게끔 
document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
})

// 정규표현식을 사용하여 생성자 함수를 만들기
// 실행파일 막기, 이미지 파일인지 아닌지 체크 file_type
// fileUpload 시 형식 제한 함수
const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");    // 실행파일 막기
const regExpImg = new regExp("\.(jpg|jpeg|png|gif|bmp)$");  // 이미지 파일
const maxSize = 1024*1024*20;  // 20MB보다 크면 들어가지 않음

function fileSizeValidation(fileName, fileSize){  // 파일이름과 사이즈를 확인하고 부합하는지 확인
    if(regExp.test(fileName)){  // boolean으로 리턴 -> true면 실행 파일
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

// image file에 따라서 체크