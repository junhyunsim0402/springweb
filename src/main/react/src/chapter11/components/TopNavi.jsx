import { Link, NavLink } from "react-router-dom";

export default function TopNavi(porps){ // 상단메뉴/바 = 헤더 컴포넌트
    // <a> : a마크업은 클릭하면 브라우저 전체 새로고침( 깜빡임 ) <a href="URL">
    // <Link> : 클리하 새로고침 없이 URL 변경( 깜빡임 없음 )    <Link to="URL">

    return (
    <nav>
        <a href="/">Home </a>
        <NavLink to="/intro">인트로 </NavLink>
        <NavLink to="/intro/router">Router관련Hook </NavLink>
        <Link to="/xyz">잘못된 URL</Link>
    </nav>
    )
}