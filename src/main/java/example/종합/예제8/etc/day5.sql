drop database if exists boardservice6;
create database boardservice6;
use boardservice6;
create table board(
	bno int auto_increment,
    constraint primary key(bno),
    bcontent longtext not null,
    bwriter varchar(30) not null,
    bdate datetime default now()
);
INSERT INTO board (bcontent, bwriter) VALUES 
('스프링 부트와 MySQL 연동 실습 중입니다.', '심준현'),
('오늘 정적 다이어그램 종류에 대해 공부했어요.', 'study_hard'),
('클래스 다이어그램과 객체 다이어그램의 차이점은?', 'it_quiz'),
('SQLD 자격증 시험이 얼마 안 남았네요.', 'sql_master'),
('프로토타입 패턴은 생성 패턴이라는 걸 잊지 마세요!', 'design_pattern'),
('Longtext 타입에는 아주 긴 문장도 들어갈 수 있습니다.', 'db_admin'),
('Git Commit 메시지 컨벤션을 정하는 것은 중요합니다.', 'git_user'),
('GitHub Desktop으로 간편하게 push 하기', 'dev_helper'),
('오늘 점심 메뉴는 돈까스였는데 아주 맛있었습니다.', 'foodie'),
('자바 스트림 API는 정말 편리한 것 같아요.', 'java_lover'),
('HTML/CSS로 게시판 레이아웃 잡는 법 공유합니다.', 'web_dev'),
('자바스크립트 비동기 처리가 아직도 어렵네요.', 'js_newbie'),
('프로젝트 기획서 작성 중인데 아이디어가 안 떠올라요.', 'planner'),
('데이터베이스 인덱스 설정 시 주의할 점', 'db_expert'),
('이것은 15번째 테스트 데이터입니다.', 'tester15'),
('반응형 웹 디자인을 위해 미디어 쿼리를 사용합시다.', 'frontend_dev'),
('MyBatis와 JPA 중 어떤 것을 더 선호하시나요?', 'backend_dev'),
('오너코드 프로젝트 대박 났으면 좋겠네요!', 'project_king'),
('영화관 좌석 예약 시스템 로직 구현 완료.', 'mini_project'),
('마지막 20번째 데이터 입력 테스트입니다.', 'final_check');

select * from board;