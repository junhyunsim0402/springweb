function FrontComp(){// 생성방법 1: function(){}
  return (<>
  <li>프론트엔드</li>
        <ul>
          <li>HTML5</li>
          <li>CSS3</li>
          <li>JavaScript</li>
          <li>jQuery</li>
        </ul>
        </>)  // 생성방법 2 : return ( <> HTML코드 <> )
  }
// 백엔드 관련 컴포넌트
const BackComp = () => {
  return (
    <>
      <li>백엔드</li>
      <ul>
        <li>Java</li>
        <li>Oracle</li>
        <li>JavaScript</li>
        <li>jQuery</li>
      </ul>
    </>
  )
}
// 폼 관련 컴포넌트
let formComp = function(){
  return (<>
  <form action="">
        <select name="" id="">
          <option value="front">프론트엔드</option>
          <option value="back">백엔드</option>
        </select>
        <input type="text" name="title" />       {/* ✅ */}
        <input type="submit" value="추가" />     {/* ✅ */}
      </form>
  </>)
}

// 여러 컴포넌트 호출/참조 하는 컴포넌트
function App() {
  return (
    <>
      <form>
        <select name="gubun">
          <option value="front">프론트앤드</option>
          <option value="back">백엔드</option>
        </select>
        {/* 태그 끝에 /를 붙여서 닫아줘야 합니다 */}
        <input type="text" name="title" /> 
        <input type="submit" value="추가" />
      </form>
    </>
  );
}

function App() {
  return (
    <>
      <h2>React 기본형</h2>
      <ol>
        <li>프론트엔드</li>
        <ul>
          <li>HTML5</li>
          <li>CSS3</li>
          <li>JavaScript</li>
          <li>jQuery</li>
        </ul>
        <li>백엔드</li>
        <ul>
          <li>Java</li>
          <li>Spring</li>
          <li>Spring Boot</li>
          <li>MyBatis</li>
        </ul>
      </ol>
      <form action="">
        <select name="" id="">
          <option value="front">프론트엔드</option>
          <option value="back">백엔드</option>
        </select>
        <input type="text" name="title" />       {/* ✅ */}
        <input type="submit" value="추가" />     {/* ✅ */}
      </form>
    </>
  )
}

export default App