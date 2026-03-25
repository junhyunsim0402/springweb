-- ---------------- day11 todo sample insert ----------------
/*
insert into category (cname, create_date, update_date) values ('공부', now(), now());
insert into category (cname, create_date, update_date) values ('운동', now(), now());
insert into category (cname, create_date, update_date) values ('업무', now(), now());
insert into category (cname, create_date, update_date) values ('취미', now(), now());
insert into category (cname, create_date, update_date) values ('생활', now(), now());

insert into todo (title, content, done, cno, create_date, update_date) values('자바 공부', 'JPA 기본 개념 정리', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('Spring Boot 실습', 'REST API 만들기', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('React 공부', 'useState와 props 이해하기', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('데이터베이스 공부', 'JOIN과 INDEX 복습', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('알고리즘 문제풀이', '백준 문제 5개 풀기', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('헬스장 가기', '하체 운동', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('러닝', '5km 달리기', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('스트레칭', '아침 스트레칭 20분', true, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('수영', '자유형 연습', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('요가', '유연성 운동', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('프로젝트 기획', '서비스 기능 목록 정리', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('회의 준비', '자료 PPT 작성', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('코드 리뷰', '팀원 PR 검토', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('API 설계', 'ERD 및 엔드포인트 설계', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('배포 준비', 'Docker 이미지 빌드', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('기타 연습', '기타 코드 연습', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('독서', '기술 서적 30페이지 읽기', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('사진 촬영', '야경 촬영 연습', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('영화 감상', 'SF 영화 보기', true, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('드로잉', '인물 스케치', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('장보기', '마트에서 식료품 구매', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('청소', '집 청소하기', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('세탁', '세탁기 돌리기', true, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('요리', '저녁 식사 준비', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('공과금 납부', '전기세 납부', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('AI 공부', '머신러닝 개념 정리', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('딥러닝 실습', 'CNN 모델 구현', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('LLM 실습', 'OpenAI API 테스트', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('팀 프로젝트', '프론트엔드 UI 구현', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('Git 정리', 'Git Flow 복습', false, 3, now(), now());

-- ---------------- Member sample insert ----------------
INSERT INTO member (mid, pwd, nickname, create_date, update_date) VALUES ('user1', '1234', '유저1', now(), now());
INSERT INTO member (mid, pwd, nickname, create_date, update_date) VALUES ('user2', '1234', '유저2', now(), now());
INSERT INTO member (mid, pwd, nickname, create_date, update_date) VALUES ('user3', '1234', '유저3', now(), now());
INSERT INTO member (mid, pwd, nickname, create_date, update_date) VALUES ('user4', '1234', '유저4', now(), now());
INSERT INTO member (mid, pwd, nickname, create_date, update_date) VALUES ('user5', '1234', '유저5', now(), now());

-- ---------------- Board sample insert ----------------
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('첫 번째 게시글', '내용입니다1', null, 1, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('두 번째 게시글', '내용입니다2', 'https://placehold.co/100', 2, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('세 번째 게시글', '내용입니다3', null, 3, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('네 번째 게시글', '내용입니다4', 'https://placehold.co/100', 4, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('다섯 번째 게시글', '내용입니다5', null, 5, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('여섯 번째 게시글', '내용입니다6', 'https://placehold.co/100', 1, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('일곱 번째 게시글', '내용입니다7', null, 2, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('여덟 번째 게시글', '내용입니다8', 'https://placehold.co/100', 3, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('아홉 번째 게시글', '내용입니다9', null, 4, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열 번째 게시글', '내용입니다10', 'https://placehold.co/100', 5, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열한 번째 게시글', '내용입니다11', null, 1, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열두 번째 게시글', '내용입니다12', 'https://placehold.co/100', 2, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열세 번째 게시글', '내용입니다13', null, 3, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열네 번째 게시글', '내용입니다14', 'https://placehold.co/100', 4, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열다섯 번째 게시글', '내용입니다15', null, 5, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열여섯 번째 게시글', '내용입니다16', 'https://placehold.co/100', 1, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열일곱 번째 게시글', '내용입니다17', null, 2, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열여덟 번째 게시글', '내용입니다18', 'https://placehold.co/100', 3, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('열아홉 번째 게시글', '내용입니다19', null, 4, now(), now());
INSERT INTO board (btitle, bcontent, bfile, mno, create_date, update_date) VALUES ('스무 번째 게시글', '내용입니다20', 'https://placehold.co/100', 5, now(), now());
 */