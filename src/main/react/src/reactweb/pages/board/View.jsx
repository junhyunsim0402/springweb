import axios from "axios";
import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom"

export default function View( props ){

    // [1] 현재 URL 상의 쿼리스트링 값 가져오기 , 조회할 게시물번호 가져오기
    const [ params ] = useSearchParams() // 예] http://localhost:5173/board/view?bno=17
    const bno = params.get( "bno" ); // URL 상의 bno 값 가져오기 , 17

    const [ board , setBoard ] = useState( null );  // [3] axios 결과 담는 상태변수 

    const findById = async () =>{ // [2] axios
        try{
            const response = await axios.get( `http://localhost:8080/api/board/view?bno=${ bno }`);
            const data = response.data;
            setBoard( data );
        }catch(e){ console.log(e); }
    }

    useEffect( ()=>{ findById() } , [] ); // [4] 실행시점

    // [5] 만약에 아직 axios의 결과가 없으면 
    if( !board ) return <div> 불러오는중 </div>

    return(<div>
        <h3> 게시물 상세 </h3> 
        <div> 작성자 : { board.mname } | 작성일 : { board.createDate } </div>
        <div> 제목 : { board.btitle } </div>

        { /* 만약에 웹에디터 사용할 경우 에는 HTML 형식으로 저장되므로 HTML 형식으로 출력해야한다. */}
        { /* 리액트는 가상DOM 이라서 직접적인 HTML 대입 비권장 한다. 직접 HTML 대입하는 방법 */}
    
        <div dangerouslySetInnerHTML={ { __html : board.bcontent } } /> 

        <div> 
            {   /* 만약에 첨부파일 존재하면 , 업로드 경로에서 파일명의 다운로드 링크 추가 */
                board.bfile && (
                    <>
                        <a href={ `http://localhost:8080/upload/${ board.bfile }` } download >
                            { board.bfile.split("_")[1] } 다운로드
                        </a>
                    </>
                )
            }
         </div>
    </div>)
}