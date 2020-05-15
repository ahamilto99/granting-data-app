
INSERT INTO ROLE VALUES(seq_role.nextval, 'Program Lead', 'Responsable du Programme');
INSERT INTO ROLE VALUES(seq_role.nextval, 'Program Officer', 'Agent de Programme');

INSERT INTO MEMBER_ROLE (ID, USER_LOGIN, ROLE_ID, BUSINESS_UNIT_ID) VALUES (seq_member_role.nextval, 'aha', 1, 1);
INSERT INTO MEMBER_ROLE (ID, USER_LOGIN, ROLE_ID, BUSINESS_UNIT_ID) VALUES (seq_member_role.nextval, 'jfs', 2, 1);
INSERT INTO MEMBER_ROLE (ID, USER_LOGIN, ROLE_ID, BUSINESS_UNIT_ID) VALUES (seq_member_role.nextval, 'rwi', 2, 1);

