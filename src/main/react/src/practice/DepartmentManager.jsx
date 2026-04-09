import React, { useEffect, useState } from "react";
import axios from "axios";

export default function DepartmentManager() {
  const [categoryList, setCategoryList] = useState([]); // 맴버리스트
  const [input, setInput] = useState("");

  useEffect(() => { onFindAll(); }, []);

  // 1. 등록
  const categoryAdd = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/category/add", { category: input });
      setInput("");
      onFindAll();
    } catch (e) {
      console.log("등록 실패", e);
    }
  };

  // 2. 전체 조회
  const onFindAll = async () => {
    try {
      const res = await axios.get("http://localhost:8080/category/select");
      setCategoryList(res.data);
    } catch (e) {
      console.log("조회 실패", e);
    }
  };

  // 3. 맴버 수정
  const updateCategory = async (categoryId) => {
    const newName = prompt("새 부서명 입력")
    if (!newName) return
    try {
      await axios.put(`http://localhost:8080/category/update/${categoryId}`, { category: newName });
      onFindAll();
    } catch (e) {
      console.log("수정 실패", e);
    }
  }

  // 4. 멤버 삭제
  const deleteCategory = async (categoryId) => {
    try {
      const res = await axios.delete(`http://localhost:8080/category/delete/${categoryId}`);
      setCategoryList(res.data);
    } catch (e) {
      console.log("삭제 실패", e);
    }
  }

  return (
    <div className="sidebar">
      <h3>부서 관리</h3>

      <form className="dept-input">
        <input type="text" placeholder="신규 부서명 입력" value={input} onChange={e => setInput(e.target.value)} />
        <button onClick={categoryAdd}>추가</button>
      </form>

      <table className="dept-table">
        <thead>
          <tr>
            <th>부서명</th>
            <th>관리</th>
          </tr>
        </thead>
        <tbody>
          {categoryList.map(category => (
            <tr key={category.categoryId}>
              <td>{category.category}</td>
              <td>
                <span className="edit" onClick={() => updateCategory(category.categoryId)}>수정</span>
                <span className="delete" onClick={() => deleteCategory(category.categoryId)}>삭제</span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}