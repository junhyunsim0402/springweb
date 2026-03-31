import { Outlet } from "react-router-dom";

export default function CommonLayout(props){
    return (<>
        <div>
            <div style={{background:'lightgray',padding:'10px'}}>
                Outlet 컴포넌트 알아보기
            </div>
            <div>
                <Outlet/>
            </div>
            <div style={{background:'lightgray',padding:'10px'}}>
                공통레이아웃
            </div>
        </div>
    </>)
}