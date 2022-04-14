-- 회원 테이블 생성
create table t_member(
    id varchar2(10) primary key,
    pwd varchar2(10),
    name varchar2(50),
    email varchar2(50),
    joinDate date default sysdate   -- 명시적으로 추가하지 않으면 현재 시각을 입력
);

-- 회원 정보 추가
insert into t_member
values('hong','1212','홍길동','hong@gmail.com',sysdate);

insert into t_member
values('lee','1212','이순신','lee@test.com',sysdate);

insert into t_member
values('kim','1212','김유신','kim@jweb.com',sysdate);
commit;
-- SQL Developer에서 테이블에 회원 정보를 추가한 후 반드시
-- 커밋(commit)을 해줘야 영구적으로 반영이 됨

-- 테이블에서 회원정보를 조회
select * from t_member;



DROP TABLE t_Board CASCADE CONSTRAINTS;

--게시판 테이블을 생성합니다.
create table t_Board(
    articleNO number( 10 ) primary key,
    parentNO number(10) default 0,
    title varchar2( 500 ) not null,
    content varchar2( 4000 ),
    imageFileName varchar2(30),
    writedate date default sysdate not null ,
    id varchar2(10),
    
    -- ID 컬럼을 회원 테이블의 ID 컬럼에 대해 외래키로 지정
    CONSTRAINT FK_ID FOREIGN KEY(id)
    REFERENCES t_member(id)
    
);

-- 테이블에 테스트 글을 추가합니다.
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(1, 0, '테스트글입니다.', '테스트글입니다.', null, sysdate, 'hong' );
 
 insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
  values(2, 0, '안녕하세요.', '상품 후기입니다.', null,sysdate, 'hong' );
 
 insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
  values(3, 2, '답변입니다.', '상품 후기에 대한 답변입니다.', null,sysdate, 'hong' );
  
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(5, 3, '답변입니다.', '상품 좋습니다.', null,sysdate, 'lee' );
 
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(4, 0, '김유신입니다.', '김유신 테스트글입니다.', null, sysdate, 'kim' );
 
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(6, 2, '상품 후기입니다.', '이순신 상품 사용 후기를 올립니다.', null, sysdate, 'lee' );
 
 commit;    -- 추가 후 반드시 커밋을 해줌
 select * from t_board;
 
 SELECT LEVEL,  -- 오라클에서 제공하는 가상 컬럼으로 글의 깊이를 나타냄
                -- (부모 글은 1)
    articleNO,
    parentNO,
    LPAD(' ',4*(LEVEL-1)) || title title,
    content,
    writeDate,
    id
    FROM t_board
    START WITH parentNO=0
    CONNECT BY PRIOR articleNO=parentNO
    ORDER SIBLINGS BY articleNO DESC;
  
  
  
-- 예금자 계좌 정보 테이블 생성 및 추가
create table cust_account(
    accountNO varchar2(20) primary key,    -- 계좌번호
    custName varchar2(50),                 -- 예금자
    balance number(20, 4)                  -- 계좌 잔고
);

insert into cust_account(accountNo, custName, balance)
values('70-490-930', '홍길동', 10000000);

insert into cust_account(accountNo, custName, balance)
values('70-490-911', '김유신', 10000000);

commit; -- insert문 실행 후 반드시 커밋을 해야함

select * from cust_account;



-- 이미지 저장 테이블 생성 SQL문
create table t_imageFile(
    imageFileNO number(10) primary key,
    imageFileName varchar2(50),
    regDate date default sysdate,
    articleNO number(10),
    CONSTRAINT FK_ARTICLENO FOREIGN KEY(articleNO)
    REFERENCES t_board(articleNO) ON DELETE CASCADE
);
-- ON DELETE CASCADE : 게시판 글을 삭제할 경우 해당 글 번호를 참조하는
--                     이미지 정보도 자동으로 삭제됨


