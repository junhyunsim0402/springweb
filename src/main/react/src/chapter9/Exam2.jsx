import { useState } from "react";

function WriteForm(props){
    // event / e 객체란? 해당 이벤트(onClick, onSubmit)가 발생했을떄 그 이벤트 정보를 담고 있는 객체
    return (<>
        <form onSubmit={(event)=>{
            event.preventDefault();// 기존 전송방식 제거
            console.log("이벤트객체:",event);
            // let gubun = document.querySelector(); 리액트는 쿼리셀릭터 방식 하지않음
            console.log("이벤트 ㅂㄹ생한 주체:",event.target);  // <form>
            console.log(event.target.value);            // <form> -> 하위요소, .target.class 명
            let gubun=event.target.gubun;   // <form> -> <select>
            let title=event.target.title;   // <form> -> <input>
            // vs let title=document.querySelector('.title').value;
            props.writeAction(gubun,title); // 부모 컴포넌트로 부터 받은 함수에 입력받은 구분과 타이틀 대입하여 함수 실행

        }}>
            <select name="gubun">
                <option value="front">프론트엔드</option>
                <option value="back">백엔드</option>
            </select>
            <input type="text" name="title" />
            <input type="submit" value="추가" />
        </form>
    </>)
}
export default function Exam2(props){
    // 상태변수란? 하나의 값 저장하고 변경하면 해당 컴포넌트 재호출
    const [message,setMessage]=useState("폼 값 전송 진행 중");

    // 자식에게 전달할 함수
    const writeAction=(gubun,title)=>{
        if(gubun!='' && title!=''){
            let formValue=`검증 완료 폼값 : ${gubun}, ${title}`;
            setMessage(formValue);
        }else{
            alert("빈 칸 있음");
        }
    }
    return (<>
        <h2> 135p </h2>
        <WriteForm writeAction={writeAction}/>
        <div>{message}</div>
    </>)
}