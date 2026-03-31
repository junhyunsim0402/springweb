// 컴포넌트 생성 : export default function 컴포넌트명(props){return JSX문법}
// 컴포넌트2
const topComp=({myData})=>{
    // const 상수, let 상수
    // () => {} 화살표함수  VS function(){}익명함수 VS function 함수명(매개변수){} 선언적함수
    // {myData}, 구조분해 : 상위컴포넌트로부터 props객체를 구조화 해서 변수로 받기(props객체내 myData속성값 변수로 받기)
    // return : 함수의 반환값
    // 리엑트JSX return : JSX 반환
    // JSX이란? HTML + JS 조합된 문법
    // JSX 주석? { /* 주석 */ }
    // JAVA MAP, 배열/리스트.stream.map((반복변수)->{})
    // JS MAP, 배열/리스트.map
        // FOREACH 반복문(return없음) VS MAP(return있음)
        // JSX 문법에서 반복된 자료를 HTML로 구성하여 반환하는 구조 다수, MAP활용 된다
        // 예] myData.front=['a','b','c']
        // < li key = { i } > { a } </li>) }
        // < li key = { i } > { b } </li>) }
        // < li key = { i } > { c } </li>) }
    return (<>
        {/* 주석처리 */}
        <ol>
            <li>프론트엔드</li>
            <ul>
                {myData.front.map((item,i) => < li key = { i } > { item } </li>) }
            </ul>
            <li>백엔드</li>
            <ul>
                {myData.front.map( (item,index) => < li key = { index } > { item } </li> ) }
            </ul>
        </ol>
    </>);
}
// 컴포넌트1
export default function Exam1(props){
    const [myData,setMyData] = useState({
        front : ['HTML','CSS','JS','jQuery'],
        back : ['Java','Oracle','JSP','SpringBoot']
    } )
    // 예] const [a,b] = {a:1, b:2}
    // a=1, b=2
    // useState 훅(hook : 갈고리-연결 상태/값/데이터 <-- 갈고리 --> 컴포넌트 )
        // 즉] 상태(state)가 ( *주소/참조값* )변경되면 컴포넌트 렌더링(새로고침) 된다
        // const [상태명,set상태명] = useState(초기값);
    // 리터럴 : 키보드로부터 입력받은 상수 자료, 주소/참조 값 : 자료가 위치한 메모리 주소값
        // let a = 3 ;  // 주소값 몇개> 2개, 3(101번지), a(201번지)
        // let b = 3 ;  // 주소값 몇개? 3개, 3(101번지), a(201번지), b(202번지)

        // MemberDto dto1=new MemberDto();     // 주소값 몇개? 2개, new MemberDto(301번지), dto1(401번지)
        // MemberDto dto2=new MemberDto();     // 주소값 몇개? 4개, new MemberDto(301번지), dto1(401번지), new MemberDto(302번지), dto2(402번지)
        // MemberDto dto3=dto2;                // 주소값 몇개? 5개, new MemberDto(301번지), dto1(401번지), new MemberDto(302번지), dto2(402번지), dto3(403번지)

    // 1] 렌더링 안되는 코드
    const addFront= ( ) => {
        myData.front.push( 'React');    // MyData(101동 203호), push() : 배열내 값 추가 함수, 주소 변경 없이 주소내 값 추가
        setMyData(myData);              // set상태명(새로운 주소값); // 상태는 값변경 감지하지않고 상태의 주소값 변경 감지한다.
    }
    // 2] 렌더링 되는 코드, {...기존객체},[...기존배열], {... 기존객체, 속성명:값}, [...기존배열, 값] 
    const addBack = ( ) => {
        const newBack= [...myData.back,'Node.js'];
        const newMydata= {...myData, back:newMydata};   // newMyData = {...MyData(101동 203호)}
        setMyData(newMydata);                               // newMyData = 105동 702호
    }
    return  <>
    <h2>chapter10</h2>
    <TopComp myData={myData} />
    <button type="button" onClick={addFront}>프론트추가</button>
    <button type="button" onClick={addBack}>백추가</button>
    </>
}
// onclick : HTML코드, onnClick : JSX코드(가상/가짜DOM)
// 리액트가 가상/가짜 DOM 사용하는 이유 : HTML이 객체지향이 아니라서 HTML를 객체지향으로 만든 구조 JSX
// DOM : document object model : HTML마크업들을 객체지향