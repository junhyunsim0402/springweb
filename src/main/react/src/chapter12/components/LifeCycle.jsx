import { useEffect, useState } from "react"

function MoveBox( props ){
    // props란? 상위컴포넌트에서 해당 컴포넌트 호출시 전달해주는 속성명과속성값 들(여러개)을 객체로 받는 매개변수
    // props -> { initPosition : 50 } vs 구조분해 -> { initPosition }

    // useState란? 컴포넌트내 데이터(값) 상태 관리하는 함수 , 왜? 재렌더링(함수재호출) 하기 위해서
        // const [ 상태명 , set상태명 ] = useState( 초기값 ); 
    const [ position , setPosition ] = useState( props.initPosition );

    const [ leftCount , setLeftCount ] = useState( 1 ); // 상태(변수) 선언
    // setLeftCount( 3 ); // 수정 , 현재 함수( MoveBox ) 재 실행이 된다. -> return 다시 반환된다 -> html 다시 그리기
    // vs
    // let rightCount = 2; // 변수 선언 
    // rightCount = 3; // 수정 ,현재 함수( MoveBox ) 재 실행이 안된다.

    // [0] CSS 문법을 객체지향으로 표현 가능 , 변수대입은 `${}` 백틱 사용하여 대입 가능
    const boxStyle = {
        backgroundColor : 'red',
        position : 'relative', 
        left : `${ position }px`, // CSS 요소의 JS변수 대입 즉] CSS를 변수화/객체화
        width : '100px', height : '100px' , margin : '10px' 
    }

    // [1] 
    const moveLeft = ( ) => { 
        // 함수선언부 , function 함수명(){}  vs  function(){}  vs  ()=>{}   
        setPosition( () => position - 20 ); // 50 -20 -> 30
        setLeftCount( () => leftCount +1 );
    }
    // [2]
    const moveRight = ( ) => {
        setPosition ( () => position +20 );
    }

    // [3] 
    useEffect( function(){ 
        console.log( "useEffect 실행 : 마운트 " ); // 최초 렌더링시 실행 // moveLeft 함수 실행
        return( )=>{
            console.log( "useEffect 실행 : 언마운트"); // 재(다시) 랜더링 하면 기존 렌더링된 컴포넌트(화면/함수는) 지운다.
        }
    // } , [ leftCount ] ) // 의존성배열이란? state(상태)변수 배열내 대입하여 해당하는 상태가 변경되면 useEffect 실행
    //  }  ) // 179 (1) 생략
    // } , [ ] ) // 179 (2) 빈배열 
    } , [ leftCount ] ) // 179 (3) 특정한 state변수 대입

    // [4]
    console.log( "return 실행 : 렌더링 ")
    // 해당 컴포넌트 실행하고 , 좌측이동 버튼 3번 클릭 했을때 return 총 몇번? 4
    // 해당 컴포넌트 실행하고 , 우측이동 버튼 3번 클릭 했을때 return 총 몇번? 4
    // 해당 컴포넌트 실행하고 , 좌측이동 버튼 3번 클릭 했을때 마운트 총 몇번? 4
    // 해당 컴포넌트 실행하고 , 우측이동 버튼 3번 클릭 했을때 마운트 총 몇번? 1

    return (<>
        <div>
            <h4> 함수형 컴포넌트의 생명주기 </h4>
            <div style={ boxStyle }> { leftCount } </div>
            <input  type="button" value="좌측이동" onClick={ moveLeft }/>
            <input  type="button" value="우측이동" onClick={ moveRight }/>
        </div>
    </>)

} // f end 

export default function LifeCycle( props ){

    // html에 없는 마크업들은 모두 컴포넌트 취급한다. 즉] 컴포넌트란? 나만의 마크업 만들기

    return (<>
        <h2> chapter12 </h2>
        <MoveBox initPosition={ 50 }></MoveBox>
    </>)
}