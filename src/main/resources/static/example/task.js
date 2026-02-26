console.log("task.js");
// [1] 타입 : JS는 동적 타입이므로 
    // 1. 기본자료 리터럴
console.log(3);console.log(true);console.log(3.14);
    // 2 배열
console.log([3,true,3.41,"안녕"]);
    // 3 객체
console.log({"name":"유재석","age":40});
    // 4 함수
function func1(){}

// [2] 다양한 함수 형식
// function func2(){}  // 선언적 함수
// function func5=(){}       // 익명함수
// () => {}            // 화살표(람다표현식) 함수, 리엑트 표현
// [3] 화살표 함수는 익명 이므로 변수에 저장한다
const func3 = () => {} 

/* 
1] AXIOS 
    1. 정의 : 대표적으로 비동기/동기 통신 하는 함수
        AXIOS(REACT) VS JS(FETCH) VS JQUERY(AJAX) 등
    2. 목적 : JS환경에서 서버와의 통신
    3. 설치 : 
        1. HTML 에 CDN 코드 추가
        2.  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    4. 사용법
*/

// [1] axios.HTTP메소드명("통신할 주소")
axios.get("http://localhost:8080/day3/task");

// [2] 프론트 서버와 백엔드 서버와 같다면 도메인 생략
// axios.HTTP메소드명("통신할주소").then( ( response ) => { response.data } )
axios
.delete("/day3/task?name=유재석")
.then(( response ) => { console.log( response.data ); } );

// [3] axios.HTTP메소드명
axios.post("/day3/task?age=25")
.then(( response ) => { console.log( response.data ); } )
.catch(( error ) => { console.log( error ); } );

// [4] axios.HTTP메소드명("통신할주소",body)
const obj={"name":"유재석","age":40};   // 객체생성
axios.put("/day3/task",obj)
.then(( r ) => { console.log( r.data ); } )
.catch(( e ) => { console.log( e ); } );

// * 비동기통신 이란? 요청이 여러개 했을때 먼저 처리된 응답부터 실행
//      즉] 먼저 처리된 로직부터 실행된다. 순서보장 X
// * 동기통신 이란? 요청이 여러개 했을 때 먼저 요청한 로직이 응답 하는 순서대로 실행
//      즉] 먼저 요청한 로직부터 실행된다. 순서보장 O

// [5] async 동기화
const func5= async () => {
    try{    // 1. 예외처리
        // 2. axios 앞에 await 키워드 이용한 동기화
        // 3. axios 결과를 변수/상수에 대입 받는다
        const response = await axios.get("/day3/task/axios?name=강호동");
        // 4. 결과확인
        console.log( response.data );
    }catch(e){console.log(e)};
}
func5();