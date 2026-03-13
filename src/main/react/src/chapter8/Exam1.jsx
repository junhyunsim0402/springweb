// 1. src/assets/ 폴더에 있는 이미지를 불러올 때 (상위 폴더로 이동 후 접근)
import reactLogo from "../assets/images.png" 
import "./index.css"    // 현재 컴포넌트 

export default function Exam1(props){
    const myStyle = {
        color: "white",
        backgroundColor: "DodgerBlue",
        padding: "10px",
        fontFamily: "Verdana"
    }
    const iwidth = {maxWidth: '300px'}

    // 정적 파일 : public 이하 경로만 지정한다
    // 즉 [ /public/img/이미지 ] -> /img/이미지
    return(<>   
        <h3>스타일과 이미지 127p</h3>
        <ol>
            <li style={{color: "red"}}>프론트앤드</li>
            <ul>
                {/* 2. public 폴더 안에 img 폴더가 있을 경우의 경로입니다. */}
                {/* 현재 트리에선 src/img 폴더이므로, 제대로 나오려면 img폴더를 public으로 옮기세요! */}
                <li><img src="/img/images.png" style={iwidth} alt="Public 경로" /></li>
                
                {/* 3. 위에서 import한 이미지를 사용할 경우 */}
                <li><img src={reactLogo} style={iwidth} alt="Import 경로" /></li>
                
                {/* 4. 외부 URL 이미지 */}
                <li><img src="https://placehold.co/600x400" style={iwidth} alt="외부 경로" /></li>
            </ul>
            
            <li className="backEnd">백엔드</li>
            <ul>
                <li id="backEndSub">java</li>
                <li className="warnings">oracle</li>
                <li style={myStyle}>JSP</li>
                <li>Spring Boot</li>
            </ul>
        </ol>
    </>)
}