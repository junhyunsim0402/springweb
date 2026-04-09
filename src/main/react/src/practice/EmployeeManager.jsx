import axios from "axios";
import React, { useEffect, useState } from "react";

export default function EmployeeManager() {
  const [memberList, setMemberList] = useState([]);
  const [nameInput, setNameInput] = useState("");
  const [positionInput, setPositionInput] = useState("");
  const [deptInput, setDeptInput] = useState("");
  const [fileInput, setFileInput] = useState(null);
  const [deptList, setDeptList] = useState([]);

  const [updateMember, setUpdateMember] = useState(null);

  useEffect(() => {
    onFindAll();
    onFindDept();
  }, []);

  // 1. 등록
  const memberAdd = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("mname", nameInput);
      formData.append("position", positionInput);
      formData.append("category", deptInput);
      if (fileInput) {
        formData.append("uploadFile", fileInput);  // getUploadFile() 필드명과 맞춰야 함
      }

      await axios.post("http://localhost:8080/member/add", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setNameInput("");
      setPositionInput("");
      setDeptInput("");
      setFileInput(null);
      onFindAll();
    } catch (e) {
      console.log("등록 실패", e);
    }
  };

  // 2. 전체 조회
  const onFindAll = async () => {
    try {
      const res = await axios.get("http://localhost:8080/member/select");
      setMemberList(res.data);
    } catch (e) {
      console.log("조회 실패", e);
    }
  };

  // 3. 멤버 수정
  const update = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("mno", updateMember.mno);
      formData.append("mname", updateMember.mname);
      formData.append("position", updateMember.position);
      formData.append("category", updateMember.dept);
      if (updateMember.uploadFile) formData.append("uploadFile", updateMember.uploadFile);

      await axios.put("http://localhost:8080/member/update", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setUpdateMember(null);
      onFindAll();
    } catch (e) {
      console.log("수정 실패", e);
    }
  };

  // 4. 멤버 삭제
  const onDelete = async (mno) => {
    try {
      await axios.delete(`http://localhost:8080/member/delete?mno=${mno}`);
      onFindAll();
    } catch (e) {
      console.log("삭제 실패", e);
    }
  }

  const onFindDept = async () => {
    try {
      const res = await axios.get("http://localhost:8080/category/select");
      console.log(res.data);  // 여기 뭐 찍히는지 확인
      setDeptList(res.data);
    } catch (e) {
      console.log("부서 조회 실패", e);
    }
  };

  return (
    <div className="main">
      {/* 사원 등록 */}
      <form className="form-box" onSubmit={memberAdd}>
        <h3>사원 등록</h3>

        <div className="form-row">
          <input type="text" placeholder="이름" value={nameInput} onChange={(e) => setNameInput(e.target.value)} />
          <input type="text" placeholder="직급" value={positionInput} onChange={(e) => setPositionInput(e.target.value)} />
        </div>

        <div className="form-row">
          <select
            value={deptInput}
            onChange={(e) => setDeptInput(e.target.value)}
            onClick={onFindDept}
          >
            <option value="">부서를 선택하세요</option>
            {deptList.map((dept, index) => (
              <option key={index} value={dept.category}>{dept.category}</option>
            ))}
          </select>
          <input
            type="file"
            onChange={(e) => setFileInput(e.target.files[0])}
          />
        </div>

        <div className="form-action">
          <button type="submit" className="primary">등록</button>
        </div>
      </form>

      {/* 사원 목록 */}
      <div className="table-box">
        <h3>사원 전체 목록</h3>

        <table>
          <thead>
            <tr>
              <th>사진</th>
              <th>이름</th>
              <th>부서</th>
              <th>직급</th>
              <th>관리</th>
            </tr>
          </thead>
          <tbody>
            {memberList.map((member) => (
              <tr key={member.mno}>
                <td>
                  <img
                    className="img-box"
                    src={`http://localhost:8080/uploads/${member.mfile}`}
                    alt=""
                  />
                </td>
                <td>{member.mname}</td>
                <td>{member.category}</td>
                <td>{member.position}</td>
                <td>
                  <span className="edit" onClick={() => setUpdateMember({ ...member })}>수정</span>
                  <span className="delete" onClick={() => onDelete(member.mno)}>삭제</span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* 수정 모달 */}
      {updateMember && (
        <div className="modal-overlay">
          <form className="modal-box" onSubmit={update}>
            <h3>사원 수정</h3>

            <div className="form-row">
              <input
                type="text"
                placeholder="이름"
                value={updateMember.mname}
                onChange={(e) => setUpdateMember({ ...updateMember, mname: e.target.value })}
              />
              <input
                type="text"
                placeholder="직급"
                value={updateMember.position}
                onChange={(e) => setUpdateMember({ ...updateMember, position: e.target.value })}
              />
            </div>

            <div className="form-row">
              <select value={updateMember.category} onChange={(e) => setUpdateMember({ ...updateMember, category: e.target.value })}>
                <option value="">부서를 선택하세요</option>
                {deptList.map((dept, index) => (
                  <option key={index} value={dept.category}>{dept.category}</option>
                ))}
              </select>

              <input
                type="file"
                onChange={(e) => setUpdateMember({ ...updateMember, uploadFile: e.target.files[0] })}
              />
            </div>

            <div className="form-action">
              <button type="button" onClick={() => setUpdateMember(null)}>취소</button>
              <button type="submit" className="primary">저장</button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
}