console.log("index.js");

const 전체조회=async () => {
    // 1. 어디에
    const tbody=document.querySelector("#boardTable");
    // 2. 무엇을
    let html=``;
    const response=await axios.get("/board");
    const data=response.data;
    for( let index = 0 ; index < data.length ; index++ ){
        const board = data[index];
        html += `<tr>
        <td> ${ board.bno } </td>
        <td> <a href="/종합예제10/detail.html?bno=${board.bno}">${ board.btitle }</a> </td>
        <td> ${ board.bwriter } </td>
        <td> ${ board.create_date } </td>
        </tr>`
}
    // 3. 출력
    tbody.innerHTML=html;
}
전체조회();