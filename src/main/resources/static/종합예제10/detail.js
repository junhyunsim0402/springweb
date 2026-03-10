console.log("detail.js");

// 1] 쿼리스트링이란? URL주소 뒤에 ? 로 매개변수 (값) 표현하는 경우
// 2] JS에서 쿼리스트링의 값 가져오는 방법
//      new URLSearchParams(location.search).get("변수명");
const bno=new URLSearchParams(location.search).get("bno");
// 3] 확인
console.log(bno);

// 개별조회 함수 정의
const 개별조회=async () => {
    // 1. 어디에
    const boardBox=document.querySelector("#boardBox");
    // 2. 무엇을, 쿼리스트링에 존재하는 (클릭된 게시물) 
    const response=await axios.get(`/board/detail?bno=${bno}`);
    const data=response.data;
    let html=`
        제목 : <div>${data.btitle}</div>
        작성자/작성일 : <div>${data.bwriter}/${data.create_date}</div>
        내용 : <div>${data.bcontent}</div>
        <button onclick="개별수정(${data.bno})">수정하기</button>
        <button onclick="개별삭제(${data.bno})">삭제하기</button>`;
    // 3. 출력
    boardBox.innerHTML=html;
}
// 개별조회 함수 호출
개별조회();

// 개별 삭제 함수 정의
const 개별삭제=async (bno) => {
    // 1. 현재 게시물 삭제하기 위해 현재 게시물 번호 확인 bno
    // 2. axios 이용하여 서버에게 게시물 삭제 요청 결과받기
    const response=await axios.delete(`/board?bno=${bno}`);
    const data=response.data;
    if(data==true){
        alert("삭제성공");
        location.href="/종합예제10/index.html";
    } else{ alert("삭제실패"); }
}

// 개별 수정 함수 정의
const 개별수정 = async (bno) => {
    // 1. 현재 게시물 수정하기 위해 현재 게시물 번호 확인 bno
    // 2. 수정할 내용 입력받기
    const btitle=prompt("수정할 제목을 입력하세요");
    const bcontent=prompt("수정할 내용을 입력하세요");
    const bwriter=prompt("수정할 작성자를 입력하세요");
    // 3. axios 이용한 서버에게 수정 요청 응답 받기
    const obj={bno,btitle,bcontent,bwriter};
    const response=await axios.put(`/board`,obj);
    const data=response.data;
    // 4. 결과
    if(data==true){
        alert("수정성공");
        location.reload();  // 현재페이지 새로고침
    } else{ alert("수정실패"); }
}

const 댓글등록 = async () => {
    const writer=document.querySelector("#rwriter").value;
    const content=document.querySelector("#rcontent").value;
    const obj = { bno, rwriter: writer, rcontent: content };
    const response=await axios.post(`/reply`,obj);
    const data=response.data;
    if(data==true){
        alert("등록성공");
        location.reload();
    }else{
        alert("등록실패");
    }
}

// 2. 댓글 전체조회 함수
const 댓글전체조회 = async () => {
    const replyListBox = document.querySelector("#replyListBox");
    const response = await axios.get(`/reply?bno=${bno}`);
    const data = response.data;

    let html = "";
    data.forEach( r => {
        html += `
            <div style="margin: 20px 0px;">
                <span>작성자: ${r.rwriter} </span> <span> 작성일: ${r.create_date}</span>
                <span>
                    <button onclick="댓글수정(${r.rno})">수정</button>
                    <button onclick="댓글삭제(${r.rno})">삭제</button>
                </span>
                <div>${r.rcontent}</div>
            </div>`;
    });
    replyListBox.innerHTML = html;
}
댓글전체조회();

const 댓글삭제 = async (rno) => {
    if(!confirm("삭제하시겠습니까?")) return;
    const response = await axios.delete(`/reply?rno=${rno}`);
    if(response.data == true) {
        alert("삭제 성공");
        댓글전체조회();
    }
}

// [5] 댓글 수정
const 댓글수정 = async (rno) => {
    const rwriter=prompt("수정할 작성자 입력");
    const rcontent = prompt("수정할 내용 입력");
    const obj = { rno, rwriter, rcontent,bno };
    const response = await axios.put("/reply", obj);
    if(response.data == true) {
        alert("수정 성공");
        location.reload();
    }else{
        alert("수정 실패");
    }
}