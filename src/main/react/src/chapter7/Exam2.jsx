import { useState } from "react";
import FrontComp from "./FrontComp";
import BackComp from "./BackComp";

export default function Exam2(props){
    // 1) useState 선언 : const [변수명,set 변수명 ] = useState(초기값);
    const [mode,setMode] = useState('both');
    //                  VS 차이점 : 상태변수는 setXXX()이용하여 값 수정 시 함수/컴포넌트 재실행
    const mode2='both'; // 일반변수 값 변경 이후에 아무런 변화가 없다
    // 2) 상태(값) 변경하는 함수 : set변수명( 새로운 값 );
    const handleSetMode = (mode) => {setMode(mode);} 
    // 3) 컴포넌트 저장용 변수)
    let contents='';
    // 4) 상태에 따라 컴포넌트 그리기, 분기(if), setMode가 실행되서 상태가 변경되면 화면을 다시 그리므로 ( 리렌더링/함수 )호출
    if(mode=='front'){
        contents=<>
            <FrontComp onSetMode={(mode)=> {setMode(mode)}} />
        </>
    }else if(mode=='back'){
        contents=<>
            <BackComp setMode={setMode}/>
        </>
    }else{
        contents=<>
            <FrontComp onSetMode={(mode)=> {handleSetMode(mode)}}></FrontComp>
            <BackComp setMode={handleSetMode}/>
        </>
    }
    // 5. 분기 (if)에 따른 결과묵 return 에서 출력)
    return(<>
    <h3> chapter7 p.118 </h3>
    <h2><a href="/" onClick={(event)=>{
        event.preventDefault();
        setMode('both');
    }}>React - State</a></h2>
    <ol>
        {contents}
    </ol>
    </>)
}