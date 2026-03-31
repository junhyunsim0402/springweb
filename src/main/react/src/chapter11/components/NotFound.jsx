import { Link } from "react-router-dom";

export default function NotFound(props){
    return (<div>
        <h2>Not Found</h2>
        <p>
            페이지를 찾을수 없습니다.<br/>
            <Link to="/">홈으로</Link>
        </p>
    </div>
    );
}