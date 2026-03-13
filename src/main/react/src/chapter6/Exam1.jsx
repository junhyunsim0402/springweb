// 컴포넌트 만드는 방법
// 1. 파일 만들기
// 2. 첫글자는 대문자로 시작하고 .jsx파일 생성
// 3. export default function 컴포넌트명(props)
// 4. 컴포넌트 내 return( <> JSX문법 </> )

// o.111 컴포넌트 가져오기? 다른 jsx에서 컴포넌트 가져오기
import FrontComp from './FrontComp'
import BackComp from './BackComp'

export default function Exam1(props){
    return (<>
    <h3>Chapter 6 p.100</h3>
    <ol>
        <FrontComp></FrontComp>
        <BackComp onMyEvent2={ (msg)=>{
            alert(msg);
        }}></BackComp>
    </ol>
    </>)
}