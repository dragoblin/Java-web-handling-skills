-- ȸ�� ���̺� ����
create table t_member(
    id varchar2(10) primary key,
    pwd varchar2(10),
    name varchar2(50),
    email varchar2(50),
    joinDate date default sysdate   -- ��������� �߰����� ������ ���� �ð��� �Է�
);

-- ȸ�� ���� �߰�
insert into t_member
values('hong','1212','ȫ�浿','hong@gmail.com',sysdate);

insert into t_member
values('lee','1212','�̼���','lee@test.com',sysdate);

insert into t_member
values('kim','1212','������','kim@jweb.com',sysdate);
commit;
-- SQL Developer���� ���̺� ȸ�� ������ �߰��� �� �ݵ��
-- Ŀ��(commit)�� ����� ���������� �ݿ��� ��

-- ���̺��� ȸ�������� ��ȸ
select * from t_member;



DROP TABLE t_Board CASCADE CONSTRAINTS;

--�Խ��� ���̺��� �����մϴ�.
create table t_Board(
    articleNO number( 10 ) primary key,
    parentNO number(10) default 0,
    title varchar2( 500 ) not null,
    content varchar2( 4000 ),
    imageFileName varchar2(30),
    writedate date default sysdate not null ,
    id varchar2(10),
    
    -- ID �÷��� ȸ�� ���̺��� ID �÷��� ���� �ܷ�Ű�� ����
    CONSTRAINT FK_ID FOREIGN KEY(id)
    REFERENCES t_member(id)
    
);

-- ���̺� �׽�Ʈ ���� �߰��մϴ�.
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(1, 0, '�׽�Ʈ���Դϴ�.', '�׽�Ʈ���Դϴ�.', null, sysdate, 'hong' );
 
 insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
  values(2, 0, '�ȳ��ϼ���.', '��ǰ �ı��Դϴ�.', null,sysdate, 'hong' );
 
 insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
  values(3, 2, '�亯�Դϴ�.', '��ǰ �ı⿡ ���� �亯�Դϴ�.', null,sysdate, 'hong' );
  
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(5, 3, '�亯�Դϴ�.', '��ǰ �����ϴ�.', null,sysdate, 'lee' );
 
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(4, 0, '�������Դϴ�.', '������ �׽�Ʈ���Դϴ�.', null, sysdate, 'kim' );
 
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
 values(6, 2, '��ǰ �ı��Դϴ�.', '�̼��� ��ǰ ��� �ı⸦ �ø��ϴ�.', null, sysdate, 'lee' );
 
 commit;    -- �߰� �� �ݵ�� Ŀ���� ����
 select * from t_board;
 
 SELECT LEVEL,  -- ����Ŭ���� �����ϴ� ���� �÷����� ���� ���̸� ��Ÿ��
                -- (�θ� ���� 1)
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
  
  
  
-- ������ ���� ���� ���̺� ���� �� �߰�
create table cust_account(
    accountNO varchar2(20) primary key,    -- ���¹�ȣ
    custName varchar2(50),                 -- ������
    balance number(20, 4)                  -- ���� �ܰ�
);

insert into cust_account(accountNo, custName, balance)
values('70-490-930', 'ȫ�浿', 10000000);

insert into cust_account(accountNo, custName, balance)
values('70-490-911', '������', 10000000);

commit; -- insert�� ���� �� �ݵ�� Ŀ���� �ؾ���

select * from cust_account;



-- �̹��� ���� ���̺� ���� SQL��
create table t_imageFile(
    imageFileNO number(10) primary key,
    imageFileName varchar2(50),
    regDate date default sysdate,
    articleNO number(10),
    CONSTRAINT FK_ARTICLENO FOREIGN KEY(articleNO)
    REFERENCES t_board(articleNO) ON DELETE CASCADE
);
-- ON DELETE CASCADE : �Խ��� ���� ������ ��� �ش� �� ��ȣ�� �����ϴ�
--                     �̹��� ������ �ڵ����� ������


