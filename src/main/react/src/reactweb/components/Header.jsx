import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header( props ){

    // [2] 로그인 상태 저장하는 상태변수, 
    const [ login , setLogin ] = useState( false ); // 초기값은 false 로그인 안했다는 뜻 
    // [3] 로그인중인 회원 정보 담는 상태변수
    const [ user , setUser ] = useState( null ); // 초기값은 비로그인 정보

    // [1] 로그인 상태에 따라 상단메뉴 분기 위한 함수 
    const getMyInfo = async () => { 
        // 3) 헤더에 표시할 로그인된 유저 아이디 가져오기
        const response = await axios.get( 
            'http://localhost:8080/api/member3/my/info', // 통신할 (스프링 컨트롤러 매핑) 주소
            { withCredentials : true } // * 쿠키(+토큰포함) 전송으로 변경 *
        );
        // 4) 통신 결과 분기
        const data = response.data;
        if( data || data != false ){ // 응답 자료가 존재하면 
            setLogin( true ); // 로그인상태 변경 
            setUser( data ); // 응답 받은 자료(회원정보) 를 저장  
        }else{
            setLogin( false ); // 비로그인상태 변경 
        }
    }

    // [4] 헤더가 열리면 최초 1번 실행 , 로그인상태( 백엔드 검증해야한다. ) 
    useEffect( ()=>{ getMyInfo(); } , [] )

    // [5] 로그아웃 
    const logout = async( ) => {
        // 1) axios
        const response = await axios.get(
            "http://localhost:8080/api/member3/logout", // 통신할 서버의 경로( controller 매핑 주소 )
            { withCredentials : true } // 쿠키 전송
        );
        // 2) 로그인 상태 변경 , 안내후 페이지 변경 
        setLogin( false );
        alert('로그아웃');  
        location.href="/"; 
    }

    // JS 삼항연산자 , 조건 ? 참실행문 : 거짓실행문 , 조건이 참이면 참실행문 거짓이면 거짓실행문  
    // JS 단축평가 , 조건 && 실행문 , 조건이 참이면 실행문 거짓이면 생략
    return (
        <div>
            { /* 로그인 상태에 따른 메뉴 분기 */}
            <Link to="/"> 홈 </Link> |
            <Link to="/board"> 게시물 </Link> |

            { /* 비로그인 메뉴  */}
            { login == false && (<>
                <Link to="/member/login"> 로그인 </Link> |
                <Link to="/member/sigunup"> 회원가입 </Link> |
            </> )}  

            { /* 로그인 메뉴  */}
            { login == true && ( <> 
                <span> { user.mid } 님 </span> |
                <Link to="/member/page"> 내정보 </Link> |
                <Link to="/board/write"> 글쓰기 </Link> |
                <Link to="/chat"> 채팅방 </Link> |
                <button onClick={ logout }> 로그아웃 </button>
            </> )}  
            <hr/>
        </div>
    )
}