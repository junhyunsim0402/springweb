console.log("write.js");
const 등록= async () => {
    // 1. 입력받은 DOM가져오기
    const writerInput=document.querySelector(".writerInput");
    const contentInput=document.querySelector(".contentInput");
    const titleInput=document.querySelector(".titleInput");
    // 2. DOM에서 입력받은 값 가져오기
    const bwriter=writerInput.value;
    const bcontent=contentInput.value;
    const btitle=titleInput.value;
    // 3. 객체 구성, 속성명과 대입변수명이 같을 경우 생략 가능
    const obj={bwriter,bcontent,btitle};
    // 4. AXIOS 이용하여 서버에서 저장 요청 / 응답 받기
    const response=await axios.post("/board",obj);
    const data=response.data;
    if(data==true){
        alert("등록성공");
        location.href="/종합예제10/index.html";
    }else{
        alert("등록실패");
    }
}