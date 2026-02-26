const func1= async () => {
    try{    // 1. 예외처리
        // 2. axios 앞에 await 키워드 이용한 동기화
        // 3. axios 결과를 변수/상수에 대입 받는다
        const data={
            studentName: "홍길동", date: "2026-02-26", status: "출석"
        };
        const response = await axios.post("/attendance",data);
        // 4. 결과확인
        console.log( response.data );
    }catch(e){console.log(e)};
}
const func2 = async () => {
    try {
        const response = await axios.get("/attendance");
        console.log(response.data);
    } catch(e) { console.log(e); }
}

const func3 = async () => {
    try {
        const response = await axios.get("/attendance/detail", { params: { ano: 1 } });
        console.log(response.data);
    } catch(e) { console.log(e); }
}

const func4 = async () => {
    try {
        const response = await axios.delete("/attendance", { params: { ano: 1 } });
        console.log(response.data);
    } catch(e) { console.log(e); }
}

const func5 = async () => {
    try {
        const data = { ano: 1, status: "지각" };
        const response = await axios.put("/attendance", data);
        console.log(response.data);
    } catch(e) { console.log(e); }
}