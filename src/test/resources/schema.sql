SET DB_CLOSE_DELAY -1;
CREATE USER IF NOT EXISTS "SA" SALT '3b549bd8a5abdfc1' HASH 'a898790ffa93221fc239e14aa60cbdc090c90db51f70a2b62f3b2e52f6f3057f' ADMIN;
CREATE SEQUENCE "PUBLIC"."SEQ_FUNDING_OPPORTUNITY" START WITH 142;
CREATE SEQUENCE "PUBLIC"."SEQ_AGENCY" START WITH 4;
CREATE SEQUENCE "PUBLIC"."SEQ_APPLICATION_PARTICIPATION" START WITH 1;
CREATE SEQUENCE "PUBLIC"."SEQ_SYSTEM_FUNDING_CYCLE" START WITH 1;
CREATE SEQUENCE "PUBLIC"."SEQ_FUNDING_CYCLE" START WITH 1;
CREATE SEQUENCE "PUBLIC"."SEQ_GRANTING_SYSTEM" START WITH 12;
CREATE SEQUENCE "PUBLIC"."SEQ_BUSINESS_UNIT" START WITH 31;
CREATE SEQUENCE "PUBLIC"."SEQ_FISCAL_YEAR" START WITH 6;
CREATE SEQUENCE "PUBLIC"."SEQ_GRANTING_STAGE" START WITH 6;
CREATE SEQUENCE "PUBLIC"."SEQ_GRANTING_CAPABILITY" START WITH 285;
CREATE SEQUENCE "PUBLIC"."SEQ_MEMBER_ROLE" START WITH 4;
CREATE SEQUENCE "PUBLIC"."SEQ_SYSTEM_FUNDING_OPPORTUNITY" START WITH 3;
CREATE SEQUENCE "PUBLIC"."SEQ_ROLE" START WITH 3;
CREATE MEMORY TABLE "PUBLIC"."DATABASECHANGELOGLOCK"(
    "ID" INT NOT NULL,
    "LOCKED" BOOLEAN NOT NULL,
    "LOCKGRANTED" TIMESTAMP,
    "LOCKEDBY" VARCHAR(255)
);
ALTER TABLE "PUBLIC"."DATABASECHANGELOGLOCK" ADD CONSTRAINT "PUBLIC"."PK_DATABASECHANGELOGLOCK" PRIMARY KEY("ID");
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.DATABASECHANGELOGLOCK;
INSERT INTO "PUBLIC"."DATABASECHANGELOGLOCK" VALUES
(1, FALSE, NULL, NULL);
CREATE MEMORY TABLE "PUBLIC"."DATABASECHANGELOG"(
    "ID" VARCHAR(255) NOT NULL,
    "AUTHOR" VARCHAR(255) NOT NULL,
    "FILENAME" VARCHAR(255) NOT NULL,
    "DATEEXECUTED" TIMESTAMP NOT NULL,
    "ORDEREXECUTED" INT NOT NULL,
    "EXECTYPE" VARCHAR(10) NOT NULL,
    "MD5SUM" VARCHAR(35),
    "DESCRIPTION" VARCHAR(255),
    "COMMENTS" VARCHAR(255),
    "TAG" VARCHAR(255),
    "LIQUIBASE" VARCHAR(20),
    "CONTEXTS" VARCHAR(255),
    "LABELS" VARCHAR(255),
    "DEPLOYMENT_ID" VARCHAR(10)
);
-- 34 +/- SELECT COUNT(*) FROM PUBLIC.DATABASECHANGELOG;
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES
('00000000000002', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.102', 1, 'EXECUTED', '8:70b10269a8fa8c113de67b28f8307629', 'createSequence sequenceName=seq_agency', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000003', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.103', 2, 'EXECUTED', '8:5c4dc06cd2ec60736fbc4bab78c629a9', 'createTable tableName=agency', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000004', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.104', 3, 'EXECUTED', '8:cf9f3c1d6e36d322121f9c0060d69bd6', 'createSequence sequenceName=seq_fiscal_year', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000005', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.105', 4, 'EXECUTED', '8:e7502be12f15e498f2273af35d995c32', 'createTable tableName=fiscal_year', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000006', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.106', 5, 'EXECUTED', '8:119481f9e657b8437d8a0eee61d69fb6', 'createSequence sequenceName=seq_funding_opportunity', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000007', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.108', 6, 'EXECUTED', '8:a51c959956c8dd9795f6d3f099e1f32c', 'createTable tableName=funding_opportunity', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000008', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.109', 7, 'EXECUTED', '8:9dd1ec4efa55f81cd257fb9c5e682837', 'createSequence sequenceName=seq_funding_cycle', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000009', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.111', 8, 'EXECUTED', '8:61bbe753c75364a22980ea44cfc2dcb0', 'createTable tableName=funding_cycle', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000010', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.113', 9, 'EXECUTED', '8:f2e1a7c2e9a78ad77d12ded9bd64779b', 'createTable tableName=funding_opportunity_participating_agencies', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000011', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.114', 10, 'EXECUTED', '8:74b2e01e89cda9958f42388ff7ee5650', 'createSequence sequenceName=seq_granting_capability', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000012', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.115', 11, 'EXECUTED', '8:20d35617686cb3167a51865e5e2be2b2', 'createTable tableName=granting_capability', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000013', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.116', 12, 'EXECUTED', '8:592740663948998e505c1be4973d1246', 'createSequence sequenceName=seq_granting_stage', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000014', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.116', 13, 'EXECUTED', '8:bb8c4f30bdaa6899f856b4b26a3949d9', 'createTable tableName=granting_stage', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000015', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.117', 14, 'EXECUTED', '8:17bface9d2ae442f257889a753f645dc', 'createSequence sequenceName=seq_granting_system', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000016', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.118', 15, 'EXECUTED', '8:f6908f6b01ea2017107ff32c88400d83', 'createTable tableName=granting_system', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000017', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.119', 16, 'EXECUTED', '8:8de2c491481e48f5547eb0321a57af2d', 'createSequence sequenceName=seq_system_funding_opportunity', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000018', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.125', 17, 'EXECUTED', '8:edbbf71dbc2597478c3153f5ef5cde49', 'createTable tableName=system_funding_opportunity', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000019', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.127', 18, 'EXECUTED', '8:fb688e0f61d07dfb637d5ce8e75bf56d', 'createSequence sequenceName=seq_system_funding_cycle', '', NULL, '3.8.2', NULL, NULL, '8015879049');
INSERT INTO "PUBLIC"."DATABASECHANGELOG" VALUES
('00000000000020', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.128', 19, 'EXECUTED', '8:f1507a959d2b1b8697c9e0258482fc32', 'createTable tableName=system_funding_cycle', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000021', 'jfs', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.129', 20, 'EXECUTED', '8:05559de6b68811c33eeb162c1335f61b', 'addPrimaryKey constraintName=PRIMARY5, tableName=funding_opportunity_participating_agencies', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000022', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.129', 21, 'EXECUTED', '8:361ed0b1751b17a8e77c7acd86bf093a', 'createSequence sequenceName=seq_application_participation', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('000000000023', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.131', 22, 'EXECUTED', '8:bb7d3e0542db4173ecc04c7c0c2eabee', 'createTable tableName=application_participation', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000023', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.132', 23, 'EXECUTED', '8:f530bf36267308548b42a25596b6c70f', 'createSequence sequenceName=seq_business_unit', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000024', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.134', 24, 'EXECUTED', '8:53057a63d8dae3002c3e4848f721f02d', 'createTable tableName=business_unit', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000025', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.142', 25, 'EXECUTED', '8:5673bbe8cf63892de07f13562becacd6', 'addColumn tableName=funding_opportunity', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000026', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.146', 26, 'EXECUTED', '8:4366f73249fbbcbc2d33054d9d281c01', 'createSequence sequenceName=seq_role', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000027', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.149', 27, 'EXECUTED', '8:e6b4981d2af7564a8d5aa84dbdd4ba02', 'createTable tableName=role', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000028', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.15', 28, 'EXECUTED', '8:1335de18e4b3aba0c5241af1cc5a416a', 'createSequence sequenceName=seq_member_role', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('00000000000029', 'dev_aha', 'db/version-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.152', 29, 'EXECUTED', '8:0a16aa27e5e044550679532ec2627743', 'createTable tableName=member_role', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('populate_granting_systems', 'dev_dyd', 'db/populate_granting_systems-0.0.1.xml', TIMESTAMP '2020-04-27 15:31:19.162', 30, 'EXECUTED', '8:99a085a0b3b612424c591d186dd5c20a', 'insert tableName=granting_system; insert tableName=granting_system; insert tableName=granting_system; insert tableName=granting_system; insert tableName=granting_system; insert tableName=granting_system; insert tableName=granting_system; insert ta...', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('populate_granting_systems', 'jfs', 'db/PopulateGoldenList.xml', TIMESTAMP '2020-04-27 15:31:19.218', 31, 'EXECUTED', '8:7763f9aed91f096109465c006cef9071', 'sqlFile; sqlFile', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('fixSequences', 'jfs', 'db/PopulateGoldenList.xml', TIMESTAMP '2020-04-27 15:31:19.219', 32, 'EXECUTED', '8:934169245012a00cf85755fad8c4c7d4', 'sql; sql', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('sample SFO data', 'dev_aha', 'db/h2-only-data.xml', TIMESTAMP '2020-04-27 15:31:19.223', 33, 'EXECUTED', '8:00f77159a756f9e647a820569af91710', 'insert tableName=system_funding_opportunity; insert tableName=system_funding_opportunity', '', NULL, '3.8.2', NULL, NULL, '8015879049'),
('populateTables', 'dev_aha', 'db/h2-only-data.xml', TIMESTAMP '2020-04-27 15:31:19.267', 34, 'EXECUTED', '8:c9fc7fc1063cd5199153f4966430be26', 'sqlFile', '', NULL, '3.8.2', NULL, NULL, '8015879049');
CREATE MEMORY TABLE "PUBLIC"."AGENCY"(
    "ID" BIGINT NOT NULL,
    "ACRONYM_EN" VARCHAR(255),
    "ACRONYM_FR" VARCHAR(255),
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255)
);
ALTER TABLE "PUBLIC"."AGENCY" ADD CONSTRAINT "PUBLIC"."PK_AGENCY" PRIMARY KEY("ID");
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.AGENCY;
INSERT INTO "PUBLIC"."AGENCY" VALUES
(1, 'CRSN', NULL, 'Social Sciences and Humanities Research Council', 'Conseil de recherches en sciences humaines'),
(2, 'CRSNG', NULL, 'Natural Sciences and Engineering Research Council', STRINGDECODE('Conseil de recherches en sciences naturelles et en g\u00e9nie')),
(3, 'IRSC', NULL, 'Canadian Institutes of Health Research', STRINGDECODE('Instituts de recherche en sant\u00e9'));
CREATE MEMORY TABLE "PUBLIC"."FISCAL_YEAR"(
    "ID" BIGINT NOT NULL,
    "YEAR" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."FISCAL_YEAR" ADD CONSTRAINT "PUBLIC"."PK_FISCAL_YEAR" PRIMARY KEY("ID");
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.FISCAL_YEAR;
INSERT INTO "PUBLIC"."FISCAL_YEAR" VALUES
(1, 2017),
(2, 2018),
(3, 2019),
(4, 2020),
(5, 2041);
CREATE MEMORY TABLE "PUBLIC"."ROLE"(
    "ID" BIGINT NOT NULL,
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255)
);
ALTER TABLE "PUBLIC"."ROLE" ADD CONSTRAINT "PUBLIC"."PK_ROLE" PRIMARY KEY("ID");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.ROLE;
INSERT INTO "PUBLIC"."ROLE" VALUES
(1, 'Program Lead', 'Responsable du Programme'),
(2, 'Program Officer', 'Agent de Programme');
CREATE MEMORY TABLE "PUBLIC"."FUNDING_CYCLE"(
    "ID" BIGINT NOT NULL,
    "END_DATE" DATE,
    "EXPECTED_APPLICATIONS" BIGINT NOT NULL,
    "IS_OPEN" BOOLEAN NOT NULL,
    "START_DATE" DATE,
    "FISCAL_YEAR_ID" BIGINT NOT NULL,
    "FUNDING_OPPORTUNITY_ID" BIGINT NOT NULL,
    "END_DATELOI" DATE,
    "END_DATENOI" DATE,
    "START_DATELOI" DATE,
    "START_DATENOI" DATE
);
ALTER TABLE "PUBLIC"."FUNDING_CYCLE" ADD CONSTRAINT "PUBLIC"."PK_FUNDING_CYCLE" PRIMARY KEY("ID");
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.FUNDING_CYCLE;
CREATE MEMORY TABLE "PUBLIC"."FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES"(
    "FUNDING_OPPORTUNITY_ID" BIGINT NOT NULL,
    "PARTICIPATING_AGENCIES_ID" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES" ADD CONSTRAINT "PUBLIC"."PRIMARY5" PRIMARY KEY("FUNDING_OPPORTUNITY_ID", "PARTICIPATING_AGENCIES_ID");
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES;
CREATE MEMORY TABLE "PUBLIC"."GRANTING_CAPABILITY"(
    "ID" BIGINT NOT NULL,
    "DESCRIPTION" VARCHAR(255),
    "URL" VARCHAR(255),
    "FUNDING_OPPORTUNITY_ID" BIGINT NOT NULL,
    "GRANTING_STAGE_ID" BIGINT,
    "GRANTING_SYSTEM_ID" BIGINT
);
ALTER TABLE "PUBLIC"."GRANTING_CAPABILITY" ADD CONSTRAINT "PUBLIC"."PK_GRANTING_CAPABILITY" PRIMARY KEY("ID");
-- 284 +/- SELECT COUNT(*) FROM PUBLIC.GRANTING_CAPABILITY;
INSERT INTO "PUBLIC"."GRANTING_CAPABILITY" VALUES
(1, NULL, NULL, 1, 2, 8),
(2, NULL, NULL, 2, 2, 8),
(3, NULL, NULL, 3, 2, 6),
(4, NULL, NULL, 4, 2, 6),
(5, NULL, NULL, 5, 2, 10),
(6, NULL, NULL, 6, 2, 10),
(7, NULL, NULL, 7, 2, 10),
(8, NULL, NULL, 8, 2, 10),
(9, NULL, NULL, 9, 2, 10),
(10, NULL, NULL, 10, 2, 10),
(11, NULL, NULL, 11, 2, 10),
(12, NULL, NULL, 12, 2, 10),
(13, NULL, NULL, 13, 2, 10),
(14, NULL, NULL, 14, 2, 10),
(15, NULL, NULL, 15, 2, 10),
(16, NULL, NULL, 16, 2, 10),
(17, NULL, NULL, 17, 2, 10),
(18, NULL, NULL, 18, 2, 10),
(19, NULL, NULL, 19, 2, 10),
(20, NULL, NULL, 20, 2, 10),
(21, NULL, NULL, 21, 2, 10),
(22, NULL, NULL, 22, 2, 10),
(23, NULL, NULL, 23, 2, 10),
(24, NULL, NULL, 24, 2, 5),
(25, NULL, NULL, 25, 2, 5),
(26, NULL, NULL, 26, 2, 5),
(27, NULL, NULL, 27, 2, 5),
(28, NULL, NULL, 28, 2, 5),
(29, NULL, NULL, 29, 2, 5),
(30, NULL, NULL, 30, 2, 5),
(31, NULL, NULL, 31, 2, 5),
(32, NULL, NULL, 32, 2, 5),
(33, NULL, NULL, 33, 2, 5),
(34, NULL, NULL, 34, 2, 5),
(35, NULL, NULL, 35, 2, 5),
(36, NULL, NULL, 36, 2, 5),
(37, NULL, NULL, 37, 2, 5),
(38, NULL, NULL, 38, 2, 5),
(39, NULL, NULL, 39, 2, 5),
(40, NULL, NULL, 40, 2, 5),
(41, NULL, NULL, 41, 2, 5),
(42, NULL, NULL, 42, 2, 5),
(43, NULL, NULL, 43, 2, 5),
(44, NULL, NULL, 44, 2, 5),
(45, NULL, NULL, 45, 2, 5),
(46, NULL, NULL, 46, 2, 5),
(47, NULL, NULL, 47, 2, 5),
(48, NULL, NULL, 48, 2, 5),
(49, NULL, NULL, 49, 2, 5),
(50, NULL, NULL, 50, 2, 1),
(51, NULL, NULL, 51, 2, 6),
(52, NULL, NULL, 52, 2, 10),
(53, NULL, NULL, 53, 2, 1),
(54, NULL, NULL, 54, 2, 1),
(55, NULL, NULL, 55, 2, 1),
(56, NULL, NULL, 56, 2, 1),
(57, NULL, NULL, 57, 2, 1),
(58, NULL, NULL, 58, 2, 1),
(59, NULL, NULL, 59, 2, 1),
(60, NULL, NULL, 60, 2, 1),
(61, NULL, NULL, 61, 2, 1),
(62, NULL, NULL, 62, 2, 10),
(63, NULL, NULL, 63, 2, 10),
(64, NULL, NULL, 64, 2, 10),
(65, NULL, NULL, 65, 2, 10),
(66, NULL, NULL, 66, 2, 10),
(67, NULL, NULL, 67, 2, 10),
(68, NULL, NULL, 68, 2, 10),
(69, NULL, NULL, 69, 2, 10),
(70, NULL, NULL, 70, 2, 10),
(71, NULL, NULL, 71, 2, 10),
(72, NULL, NULL, 72, 2, 10),
(73, NULL, NULL, 73, 2, 10),
(74, NULL, NULL, 74, 2, 10),
(75, NULL, NULL, 75, 2, 6),
(76, NULL, NULL, 76, 2, 6),
(77, NULL, NULL, 77, 2, 5),
(78, NULL, NULL, 78, 2, 5),
(79, NULL, NULL, 81, 2, 6),
(80, NULL, NULL, 82, 2, 7),
(81, NULL, NULL, 83, 2, 4),
(82, NULL, NULL, 84, 2, 4),
(83, NULL, NULL, 85, 2, 10),
(84, NULL, NULL, 86, 2, 10),
(85, NULL, NULL, 87, 2, 10),
(86, NULL, NULL, 88, 2, 4),
(87, NULL, NULL, 89, 2, 4),
(88, NULL, NULL, 90, 2, 4),
(89, NULL, NULL, 91, 2, 4),
(90, NULL, NULL, 92, 2, 4),
(91, NULL, NULL, 93, 2, 4),
(92, NULL, NULL, 94, 2, 1),
(93, NULL, NULL, 95, 2, 1),
(94, NULL, NULL, 96, 2, 10),
(95, NULL, NULL, 97, 2, 10),
(96, NULL, NULL, 98, 2, 3),
(97, NULL, NULL, 99, 2, 3),
(98, NULL, NULL, 100, 2, 3),
(99, NULL, NULL, 101, 2, 3),
(100, NULL, NULL, 102, 2, 3),
(101, NULL, NULL, 103, 2, 3),
(102, NULL, NULL, 104, 2, 3),
(103, NULL, NULL, 105, 2, 3),
(104, NULL, NULL, 106, 2, 3),
(105, NULL, NULL, 107, 2, 3),
(106, NULL, NULL, 108, 2, 3),
(107, NULL, NULL, 109, 2, 3),
(108, NULL, NULL, 110, 2, 3),
(109, NULL, NULL, 111, 2, 3),
(110, NULL, NULL, 112, 2, 3),
(111, NULL, NULL, 113, 2, 3),
(112, NULL, NULL, 114, 2, 3),
(113, NULL, NULL, 115, 2, 3),
(114, NULL, NULL, 116, 2, 4),
(115, NULL, NULL, 118, 2, 4),
(116, NULL, NULL, 119, 2, 4),
(117, NULL, NULL, 120, 2, 10),
(118, NULL, NULL, 121, 2, 7),
(119, NULL, NULL, 122, 2, 10),
(120, NULL, NULL, 123, 2, 10),
(121, NULL, NULL, 124, 2, 5),
(122, NULL, NULL, 125, 2, 5),
(123, NULL, NULL, 126, 2, 5),
(124, NULL, NULL, 127, 2, 4),
(125, NULL, NULL, 128, 2, 8),
(126, NULL, NULL, 129, 2, 8),
(127, NULL, NULL, 130, 2, 1),
(128, NULL, NULL, 131, 2, 10),
(129, NULL, NULL, 132, 2, 10),
(130, NULL, NULL, 133, 2, 10),
(131, NULL, NULL, 134, 2, 10),
(132, NULL, NULL, 135, 2, 10),
(133, NULL, NULL, 136, 2, 5),
(134, NULL, NULL, 137, 2, 10),
(135, NULL, NULL, 139, 2, 9),
(136, NULL, NULL, 140, 2, 9),
(137, NULL, NULL, 141, 2, 9),
(138, NULL, NULL, 1, 4, 6),
(139, NULL, NULL, 2, 4, 8),
(140, NULL, NULL, 3, 4, 6),
(141, NULL, NULL, 4, 4, 6);
INSERT INTO "PUBLIC"."GRANTING_CAPABILITY" VALUES
(142, NULL, NULL, 5, 4, 6),
(143, NULL, NULL, 6, 4, 6),
(144, NULL, NULL, 7, 4, 6),
(145, NULL, NULL, 8, 4, 6),
(146, NULL, NULL, 9, 4, 6),
(147, NULL, NULL, 10, 4, 6),
(148, NULL, NULL, 11, 4, 10),
(149, NULL, NULL, 12, 4, 6),
(150, NULL, NULL, 13, 4, 6),
(151, NULL, NULL, 14, 4, 6),
(152, NULL, NULL, 15, 4, 6),
(153, NULL, NULL, 16, 4, 6),
(154, NULL, NULL, 17, 4, 6),
(155, NULL, NULL, 18, 4, 6),
(156, NULL, NULL, 19, 4, 6),
(157, NULL, NULL, 20, 4, 10),
(158, NULL, NULL, 21, 4, 10),
(159, NULL, NULL, 22, 4, 6),
(160, NULL, NULL, 23, 4, 10),
(161, NULL, NULL, 24, 4, 6),
(162, NULL, NULL, 25, 4, 6),
(163, NULL, NULL, 26, 4, 6),
(164, NULL, NULL, 27, 4, 6),
(165, NULL, NULL, 28, 4, 6),
(166, NULL, NULL, 29, 4, 6),
(167, NULL, NULL, 30, 4, 6),
(168, NULL, NULL, 31, 4, 6),
(169, NULL, NULL, 32, 4, 6),
(170, NULL, NULL, 33, 4, 6),
(171, NULL, NULL, 34, 4, 6),
(172, NULL, NULL, 35, 4, 6),
(173, NULL, NULL, 36, 4, 6),
(174, NULL, NULL, 37, 4, 6),
(175, NULL, NULL, 38, 4, 6),
(176, NULL, NULL, 39, 4, 6),
(177, NULL, NULL, 40, 4, 5),
(178, NULL, NULL, 41, 4, 5),
(179, NULL, NULL, 42, 4, 6),
(180, NULL, NULL, 43, 4, 5),
(181, NULL, NULL, 44, 4, 5),
(182, NULL, NULL, 45, 4, 6),
(183, NULL, NULL, 46, 4, 6),
(184, NULL, NULL, 47, 4, 6),
(185, NULL, NULL, 48, 4, 6),
(186, NULL, NULL, 49, 4, 5),
(187, NULL, NULL, 50, 4, 1),
(188, NULL, NULL, 51, 4, 6),
(189, NULL, NULL, 52, 4, 6),
(190, NULL, NULL, 53, 4, 1),
(191, NULL, NULL, 54, 4, 6),
(192, NULL, NULL, 55, 4, 1),
(193, NULL, NULL, 56, 4, 1),
(194, NULL, NULL, 57, 4, 1),
(195, NULL, NULL, 58, 4, 1),
(196, NULL, NULL, 59, 4, 1),
(197, NULL, NULL, 60, 4, 1),
(198, NULL, NULL, 61, 4, 1),
(199, NULL, NULL, 62, 4, 6),
(200, NULL, NULL, 63, 4, 6),
(201, NULL, NULL, 64, 4, 6),
(202, NULL, NULL, 65, 4, 6),
(203, NULL, NULL, 66, 4, 6),
(204, NULL, NULL, 67, 4, 6),
(205, NULL, NULL, 68, 4, 10),
(206, NULL, NULL, 69, 4, 10),
(207, NULL, NULL, 70, 4, 10),
(208, NULL, NULL, 71, 4, 10),
(209, NULL, NULL, 72, 4, 10),
(210, NULL, NULL, 73, 4, 10),
(211, NULL, NULL, 74, 4, 10),
(212, NULL, NULL, 75, 4, 6),
(213, NULL, NULL, 76, 4, 6),
(214, NULL, NULL, 77, 4, 6),
(215, NULL, NULL, 78, 4, 6),
(216, NULL, NULL, 81, 4, 6),
(217, NULL, NULL, 82, 4, 7),
(218, NULL, NULL, 83, 4, 4),
(219, NULL, NULL, 84, 4, 4),
(220, NULL, NULL, 85, 4, 10),
(221, NULL, NULL, 86, 4, 10),
(222, NULL, NULL, 87, 4, 10),
(223, NULL, NULL, 88, 4, 4),
(224, NULL, NULL, 89, 4, 4),
(225, NULL, NULL, 90, 4, 4),
(226, NULL, NULL, 91, 4, 4),
(227, NULL, NULL, 92, 4, 4),
(228, NULL, NULL, 93, 4, 4),
(229, NULL, NULL, 94, 4, 1),
(230, NULL, NULL, 95, 4, 1),
(231, NULL, NULL, 96, 4, 4),
(232, NULL, NULL, 97, 4, 10),
(233, NULL, NULL, 98, 4, 4),
(234, NULL, NULL, 99, 4, 4),
(235, NULL, NULL, 100, 4, 4),
(236, NULL, NULL, 101, 4, 4),
(237, NULL, NULL, 102, 4, 3),
(238, NULL, NULL, 103, 4, 4),
(239, NULL, NULL, 104, 4, 3),
(240, NULL, NULL, 105, 4, 4),
(241, NULL, NULL, 106, 4, 4),
(242, NULL, NULL, 107, 4, 4),
(243, NULL, NULL, 108, 4, 4),
(244, NULL, NULL, 109, 4, 4),
(245, NULL, NULL, 110, 4, 4),
(246, NULL, NULL, 111, 4, 4),
(247, NULL, NULL, 112, 4, 4),
(248, NULL, NULL, 113, 4, 4),
(249, NULL, NULL, 114, 4, 4),
(250, NULL, NULL, 115, 4, 4),
(251, NULL, NULL, 116, 4, 4),
(252, NULL, NULL, 118, 4, 4),
(253, NULL, NULL, 119, 4, 4),
(254, NULL, NULL, 120, 4, 4),
(255, NULL, NULL, 121, 4, 7),
(256, NULL, NULL, 122, 4, 6),
(257, NULL, NULL, 122, 4, 4),
(258, NULL, NULL, 122, 4, 8),
(259, NULL, NULL, 123, 4, 6),
(260, NULL, NULL, 124, 4, 6),
(261, NULL, NULL, 125, 4, 6),
(262, NULL, NULL, 126, 4, 6),
(263, NULL, NULL, 127, 4, 4),
(264, NULL, NULL, 128, 4, 8),
(265, NULL, NULL, 129, 4, 8),
(266, NULL, NULL, 130, 4, 1),
(267, NULL, NULL, 131, 4, 6),
(268, NULL, NULL, 131, 4, 4),
(269, NULL, NULL, 131, 4, 8),
(270, NULL, NULL, 132, 4, 6),
(271, NULL, NULL, 132, 4, 4),
(272, NULL, NULL, 132, 4, 8),
(273, NULL, NULL, 133, 4, 6),
(274, NULL, NULL, 133, 4, 4),
(275, NULL, NULL, 133, 4, 8),
(276, NULL, NULL, 134, 4, 6),
(277, NULL, NULL, 134, 4, 4),
(278, NULL, NULL, 134, 4, 8),
(279, NULL, NULL, 135, 4, 6);
INSERT INTO "PUBLIC"."GRANTING_CAPABILITY" VALUES
(280, NULL, NULL, 136, 4, 5),
(281, NULL, NULL, 137, 4, 6),
(282, NULL, NULL, 139, 4, 9),
(283, NULL, NULL, 140, 4, 9),
(284, NULL, NULL, 141, 4, 9);
CREATE MEMORY TABLE "PUBLIC"."MEMBER_ROLE"(
    "ID" BIGINT NOT NULL,
    "USER_LOGIN" VARCHAR(255),
    "ROLE_ID" BIGINT NOT NULL,
    "BUSINESS_UNIT_ID" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."MEMBER_ROLE" ADD CONSTRAINT "PUBLIC"."PK_MEMBER_ROLE" PRIMARY KEY("ID");
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.MEMBER_ROLE;
INSERT INTO "PUBLIC"."MEMBER_ROLE" VALUES
(1, 'aha', 1, 1),
(2, 'jfs', 2, 1),
(3, 'rwi', 2, 1);
CREATE MEMORY TABLE "PUBLIC"."GRANTING_STAGE"(
    "ID" BIGINT NOT NULL,
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255)
);
ALTER TABLE "PUBLIC"."GRANTING_STAGE" ADD CONSTRAINT "PUBLIC"."PK_GRANTING_STAGE" PRIMARY KEY("ID");
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.GRANTING_STAGE;
INSERT INTO "PUBLIC"."GRANTING_STAGE" VALUES
(1, 'ADMIN', 'ADMIN'),
(2, 'APPLY', 'APPLY'),
(3, 'ASSESS', 'ASSESS'),
(4, 'AWARD', 'AWARD'),
(5, 'AQUIT', 'AQUIT');
CREATE MEMORY TABLE "PUBLIC"."GRANTING_SYSTEM"(
    "ID" BIGINT NOT NULL,
    "ACRONYM" VARCHAR(255),
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255)
);
ALTER TABLE "PUBLIC"."GRANTING_SYSTEM" ADD CONSTRAINT "PUBLIC"."PK_GRANTING_SYSTEM" PRIMARY KEY("ID");
-- 11 +/- SELECT COUNT(*) FROM PUBLIC.GRANTING_SYSTEM;
INSERT INTO "PUBLIC"."GRANTING_SYSTEM" VALUES
(1, 'RP1', 'Research Portal', 'Portail de Recherche'),
(2, 'CRM', 'CRM', 'CRM'),
(3, 'SSHRC Online', 'SSHRC Online', 'SSHRC Online'),
(4, 'AMIS', 'AMIS', 'AMIS'),
(5, 'NSERC Online', 'NSERC Online', 'NSERC Online'),
(6, 'NAMIS', 'NAMIS', 'NAMIS'),
(7, 'CIMS', 'Corporate Inventory Management System', 'Corporate Inventory Management System'),
(8, 'ResearchNet', 'ResearchNet', 'ResearchNet'),
(9, 'Convergence', 'Convergence', 'Convergence'),
(10, 'SP Secure Upload', 'SharePoint Secure Upload', STRINGDECODE('T\u00e9l\u00e9chargement S\u00e9curis\u00e9 SharePoint')),
(11, 'SP Review', 'SharePoint Review', 'Examen de SharePoint');
CREATE MEMORY TABLE "PUBLIC"."SYSTEM_FUNDING_OPPORTUNITY"(
    "ID" BIGINT NOT NULL,
    "EXT_ID" VARCHAR(255),
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255),
    "GRANTING_SYSTEM_ID" BIGINT NOT NULL,
    "LINKED_FUNDING_OPPORTUNITY_ID" BIGINT
);
ALTER TABLE "PUBLIC"."SYSTEM_FUNDING_OPPORTUNITY" ADD CONSTRAINT "PUBLIC"."PK_SYSTEM_FUNDING_OPPORTUNITY" PRIMARY KEY("ID");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.SYSTEM_FUNDING_OPPORTUNITY;
INSERT INTO "PUBLIC"."SYSTEM_FUNDING_OPPORTUNITY" VALUES
(1, 'SAMPLE EXT ID', 'NAME EN', 'NAME FR', 1, 1),
(2, 'SAMPLE EXT ID 2', 'NAME EN 2', 'NAME FR 2', 1, NULL);
CREATE MEMORY TABLE "PUBLIC"."SYSTEM_FUNDING_CYCLE"(
    "ID" BIGINT NOT NULL,
    "FISCAL_YEAR" BIGINT,
    "EXT_ID" VARCHAR(255),
    "NUM_APPS_RECEIVED" BIGINT,
    "SYSTEM_FUNDING_OPPORTUNITY_ID" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."SYSTEM_FUNDING_CYCLE" ADD CONSTRAINT "PUBLIC"."PK_SYSTEM_FUNDING_CYCLE" PRIMARY KEY("ID");
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SYSTEM_FUNDING_CYCLE;
CREATE MEMORY TABLE "PUBLIC"."APPLICATION_PARTICIPATION"(
    "ID" BIGINT NOT NULL,
    "APPLICATION_IDENTIFIER" VARCHAR(255),
    "APPLICATION_ID" BIGINT,
    "COMPETITION_YEAR" BIGINT,
    "PROGRAM_ID" VARCHAR(255),
    "PROGRAM_EN" VARCHAR(255),
    "PROGRAM_FR" VARCHAR(255),
    "CREATE_DATE" TIMESTAMP,
    "ROLE_CODE" BIGINT,
    "ROLE_EN" VARCHAR(255),
    "ROLE_FR" VARCHAR(255),
    "FAMILY_NAME" VARCHAR(255),
    "GIVEN_NAME" VARCHAR(255),
    "PERSON_IDENTIFIER" BIGINT,
    "ORGANIZATION_ID" BIGINT,
    "ORGANIZATION_NAME_EN" VARCHAR(255),
    "ORGANIZATION_NAME_FR" VARCHAR(255),
    "FREEFORM_ADDRESS_1" VARCHAR(255),
    "FREEFORM_ADDRESS_2" VARCHAR(255),
    "FREEFORM_ADDRESS_3" VARCHAR(255),
    "FREEFORM_ADDRESS_4" VARCHAR(255),
    "MUNICIPALITY" VARCHAR(255),
    "POSTAL_ZIP_CODE" VARCHAR(255),
    "PROVINCE_STATE_CODE" VARCHAR(255),
    "COUNTRY" VARCHAR(255)
);
ALTER TABLE "PUBLIC"."APPLICATION_PARTICIPATION" ADD CONSTRAINT "PUBLIC"."PK_APPLICATION_PARTICIPATION" PRIMARY KEY("ID");
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.APPLICATION_PARTICIPATION;
CREATE MEMORY TABLE "PUBLIC"."BUSINESS_UNIT"(
    "ID" BIGINT NOT NULL,
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255),
    "ACRONYM_EN" VARCHAR(255),
    "ACRONYM_FR" VARCHAR(255),
    "AGENCY_ID" BIGINT NOT NULL
);
ALTER TABLE "PUBLIC"."BUSINESS_UNIT" ADD CONSTRAINT "PUBLIC"."PK_BUSINESS_UNIT" PRIMARY KEY("ID");
-- 30 +/- SELECT COUNT(*) FROM PUBLIC.BUSINESS_UNIT;
INSERT INTO "PUBLIC"."BUSINESS_UNIT" VALUES
(1, 'EN vulputate velit eu sem.', 'FR integer sem elit, pharetra', 'pretium', 'Phasellus', 3),
(2, 'EN in nec orci. Donec nibh. Quisque nonummy ipsum non', 'FR mi. Aliquam', 'ut', 'enim', 1),
(3, 'EN tincidunt dui augue eu tellus. Phasellus', 'FR malesuada fames ac', 'enim.', 'quam', 2),
(4, 'EN luctus et ultrices posuere cubilia Curae; Donec tincidunt. Donec', 'FR non dui nec', 'nulla.', 'arcu', 2),
(5, 'EN ac orci. Ut semper pretium neque.', 'FR natoque penatibus et magnis dis parturient montes, nascetur', 'montes,', 'magna.', 2),
(6, 'EN dui, nec tempus mauris', 'FR vulputate, risus a ultricies', 'magna.', 'quam.', 2),
(7, 'EN ultrices', 'FR aliquam ultrices iaculis odio.', 'Duis', 'sit', 2),
(8, 'EN tincidunt orci quis lectus. Nullam suscipit, est ac facilisis facilisis,', 'FR volutpat ornare, facilisis eget, ipsum.', 'ullamcorper,', 'Nam', 2),
(9, 'EN nibh.', 'FR ultrices posuere cubilia Curae; Phasellus ornare. Fusce', 'turpis', 'arcu.', 2),
(10, 'EN mauris', 'FR facilisis', 'non,', 'ligula.', 1),
(11, 'EN et, rutrum non, hendrerit id,', 'FR ornare, elit elit fermentum risus, at fringilla purus mauris', 'Donec', 'felis', 2),
(12, 'EN scelerisque sed, sapien. Nunc', 'FR eu', 'vitae', 'Donec', 2),
(13, 'EN ante blandit viverra. Donec tempus, lorem fringilla ornare placerat,', 'FR id, erat. Etiam vestibulum massa', 'purus', 'posuere', 3),
(14, 'EN parturient montes, nascetur ridiculus', 'FR tempor augue ac ipsum. Phasellus vitae mauris sit amet lorem', 'Nunc', 'ut', 1),
(15, 'EN pellentesque,', 'FR arcu. Sed eu nibh vulputate mauris sagittis placerat. Cras', 'Duis', 'egestas.', 2),
(16, 'EN tellus sem mollis dui, in sodales', 'FR donec', 'sed', 'porta', 2),
(17, 'EN ac, feugiat non, lobortis quis, pede. Suspendisse', 'FR nostra, per inceptos', 'diam.', 'Mauris', 2),
(18, 'EN tristique pellentesque, tellus sem mollis', 'FR aenean massa. Integer vitae nibh. Donec', 'Sed', 'Duis', 2),
(19, 'EN aliquam arcu. Aliquam ultrices iaculis odio. Nam interdum enim non', 'FR quisque varius. Nam porttitor scelerisque neque. Nullam nisl.', 'pellentesque', 'velit', 2),
(20, 'EN non, luctus sit amet, faucibus ut, nulla.', 'FR luctus.', 'a', 'eu', 3),
(21, 'EN orci lacus', 'FR pede. Praesent eu dui. Cum sociis natoque penatibus et', 'hendrerit', 'nascetur', 1),
(22, 'EN enim. Etiam imperdiet dictum magna. Ut tincidunt orci', 'FR montes,', 'non,', 'odio', 3),
(23, 'EN ullamcorper,', 'FR proin non massa non ante bibendum', 'Pellentesque', 'luctus', 1),
(24, 'EN eget laoreet posuere, enim nisl elementum purus, accumsan interdum', 'FR auctor non, feugiat nec, diam. Duis mi enim,', 'dapibus', 'odio.', 1),
(25, 'EN nulla. In tincidunt congue turpis.', 'FR nulla semper tellus id nunc interdum', 'nulla', 'velit', 3),
(26, 'EN cursus et, magna. Praesent interdum ligula', 'FR mauris sagittis placerat. Cras dictum ultricies ligula. Nullam enim.', 'velit.', 'feugiat.', 2),
(27, 'EN amet, dapibus id, blandit at,', 'FR id, ante. Nunc mauris', 'risus', 'imperdiet', 3),
(28, 'EN a, aliquet vel, vulputate eu,', 'FR dictum cursus. Nunc mauris elit, dictum eu,', 'Curabitur', 'pede.', 2),
(29, 'EN arcu. Vestibulum ante ipsum primis in faucibus orci', 'FR in, hendrerit consectetuer, cursus et, magna.', 'lectus', 'amet', 1),
(30, 'EN elementum', 'FR posuere cubilia Curae; Donec tincidunt. Donec', 'ultrices.', 'arcu.', 1);
CREATE MEMORY TABLE "PUBLIC"."FUNDING_OPPORTUNITY"(
    "ID" BIGINT NOT NULL,
    "DIVISION" VARCHAR(255),
    "FREQUENCY" VARCHAR(255),
    "FUNDING_TYPE" VARCHAR(255),
    "IS_COMPLEX" BOOLEAN NOT NULL,
    "IS_EDI_REQUIRED" BOOLEAN NOT NULL,
    "IS_JOINT_INITIATIVE" BOOLEAN NOT NULL,
    "NAME_EN" VARCHAR(255),
    "NAME_FR" VARCHAR(255),
    "PARTNER_ORG" VARCHAR(255),
    "PROGRAM_LEAD_DN" VARCHAR(255),
    "PROGRAM_LEAD_NAME" VARCHAR(255),
    "LEAD_AGENCY_ID" BIGINT NOT NULL,
    "ISLOI" BOOLEAN NOT NULL,
    "ISNOI" BOOLEAN NOT NULL,
    "BUSINESS_UNIT_ID" BIGINT
);
ALTER TABLE "PUBLIC"."FUNDING_OPPORTUNITY" ADD CONSTRAINT "PUBLIC"."PK_FUNDING_OPPORTUNITY" PRIMARY KEY("ID");
-- 141 +/- SELECT COUNT(*) FROM PUBLIC.FUNDING_OPPORTUNITY;
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(1, 'MCT', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Collaborative Health Research Projects (CHRP) (5640)', NULL, NULL, NULL, 'Jennifer Mills', 3, FALSE, FALSE, 15),
(2, NULL, NULL, NULL, FALSE, FALSE, FALSE, 'IPPH Urban Housing and Health Trainee Research Awards', NULL, NULL, NULL, 'Allanah Brown', 3, FALSE, FALSE, 1),
(3, 'EER/MCT', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Chairs in Design Engineering (research and salary)', STRINGDECODE('Chaires en g\u00e9nie de la conception'), NULL, NULL, 'Linda Martin', 2, FALSE, FALSE, 3),
(4, 'ICSP', 'Open', 'Research Grants', FALSE, FALSE, FALSE, 'Unique Initiatives Fund (5860)', 'Fonds d''initiatives uniques', NULL, NULL, 'Judy Paron', 2, FALSE, FALSE, 11),
(5, 'SF', 'Open', 'Research Grants', FALSE, FALSE, FALSE, 'Chairs for Women in Science and Engineering (CWSE)', STRINGDECODE('Programme de chaires pour les femmes en sciences et en g\u00e9nie'), NULL, NULL, 'Valerie Harbour', 2, FALSE, FALSE, 14),
(6, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'CMHC Postdoctoral Fellowships', 'Programme de bourses de recherche sur le logement de la SCHL.', NULL, NULL, 'Kristina Dixie', 2, FALSE, FALSE, 1),
(7, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Foreign Study Supplements in Taiwan (FSS-Taiwan) (SIT)  (5050)', STRINGDECODE('Suppl\u00e9ments pour \u00e9tudes \u00e0 Ta\u00efwan'), NULL, NULL, 'Kayla Zavitske', 2, FALSE, FALSE, 16),
(8, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'JSPS - Postdoctoral Fellowships Program (JSPSF) (4933)', STRINGDECODE('Bourses postdoctorales de la Soci\u00e9t\u00e9 japonaise pour la promotion de la science'), NULL, NULL, 'Kayla Zavitske', 2, FALSE, FALSE, 20),
(9, 'ICSP', 'Open', 'Scholarships', FALSE, FALSE, FALSE, 'Aboriginal Ambassadors in the Natural Sciences and Engineering (AANSE) (6610)', STRINGDECODE('Bourse pour ambassadeurs autochtones des sciences naturelles et du g\u00e9nie'), NULL, NULL, 'Gillian Cooper', 2, FALSE, FALSE, 3),
(10, 'ICSP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Brockhouse Canada Prize (5225)', 'Prix Brockhouse du Canada', NULL, NULL, 'Antoine Tente', 2, FALSE, FALSE, 24),
(11, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Canadian Institute for Theoretical Astrophysics Support Program (CITA) (5632)', STRINGDECODE('Programme d''appui \u00e0 l''Institut canadien d''astrophysique th\u00e9orique'), NULL, NULL, 'Emily Diepenveen', 2, FALSE, FALSE, 2),
(12, 'ICSP', '1/YR', 'Fellowships', FALSE, FALSE, FALSE, 'EWR Steacie Fellowships (Research and Salary) (SMFSA-) (5265)', STRINGDECODE('Bourses comm\u00e9moratives E.W.R. Steacie'), NULL, NULL, 'Guy Faubert', 2, FALSE, FALSE, 18),
(13, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'GENDER-NET Plus', 'ERA-NET Cofund', NULL, NULL, 'Valerie Harbour, Catherine Podeszfinski', 2, FALSE, FALSE, 8),
(14, 'ICSP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Herzberg Canada Gold Medal for Science and Engineering (5300)', STRINGDECODE('M\u00e9daille d''or Gerhard-Herzberg en sciences et en g\u00e9nie du Canada'), NULL, NULL, 'Antoine Tente', 2, FALSE, FALSE, 8),
(15, 'ICSP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'John C. Polanyi Award  (5735)', 'Prix John-C.-Polanyi du CRSNG', NULL, NULL, 'Antoine Tente', 2, FALSE, FALSE, 6),
(16, 'ICSP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'NSERC Award for Science Promotion (NASPI)', 'Prix du CRSNG - Promotion des sciences', NULL, NULL, 'Jen Bailey', 2, FALSE, FALSE, 26),
(17, 'ICSP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'NSERC Award for Science Promotion (NASPO) (6780)', 'Prix du CRSNG - Promotion des sciences', NULL, NULL, 'Jen Bailey', 2, FALSE, FALSE, 6),
(18, 'ICSP', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'PromoScience (5390)', 'Promotion des sciences', NULL, NULL, 'Jen Bailey', 2, FALSE, FALSE, 22),
(19, 'ICSP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'PromoScience Supplements (PSC)', NULL, NULL, NULL, 'Judy Paron', 2, FALSE, FALSE, 27),
(20, 'ICSP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Science Literacy Week (SLW) (PSSLW)', 'Semaine de la culture scientifique', NULL, NULL, 'Judy Paron', 2, FALSE, FALSE, 15);
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(21, 'ICSP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Science Odyssey Supplements (SOS) (PSSOS)', STRINGDECODE('Odyss\u00e9e des sciences'), NULL, NULL, 'Judy Paron', 2, FALSE, FALSE, 25),
(22, 'ICSP', NULL, 'Scholarships', FALSE, FALSE, FALSE, 'Student Amassadors (NSA) (MFNSA)  (5370)', STRINGDECODE('\u00c9tudiants ambassadeurs du CRSNG'), NULL, NULL, 'Gillian Cooper', 2, FALSE, FALSE, 16),
(23, 'ICSP', 'Open', 'Scholarships', FALSE, FALSE, FALSE, 'Young Innovators (MFNYI)', NULL, NULL, NULL, 'Gillian Cooper', 2, FALSE, FALSE, 25),
(24, 'EER/MCT', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Collaborative Research and Development Grants (CRD) (6415)', NULL, NULL, NULL, 'Andrei Ionescu', 2, FALSE, FALSE, 18),
(25, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Community & College Social Innovation Fund (CCSIF) (6017)', NULL, NULL, NULL, 'Marie Thibault', 2, FALSE, FALSE, 18),
(26, 'RD also have CCPP', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Engage for Colleges (CARD1) (6006)', NULL, NULL, NULL, 'Jennifer Bean also has Edward Irving', 2, FALSE, FALSE, 26),
(27, 'RD also have CCPP', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Engage for Universities (EGP) (6430)', NULL, NULL, NULL, 'Jennifer Bean also has Edward Irving', 2, FALSE, FALSE, 18),
(28, 'RD also have CCPP', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Engage Plus Grants for Colleges (CEGP2) (6015)', NULL, NULL, NULL, 'Jennifer Bean also has Edward Irving', 2, FALSE, FALSE, 30),
(29, 'RD also have CCPP', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Engage Plus Grants for Universities (EGP2) (6432)', NULL, NULL, NULL, 'Jennifer Bean also has Edward Irving', 2, FALSE, FALSE, 16),
(30, 'RD', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Experience Awards (previously IUSRA)(USRAI) (6030)', NULL, NULL, NULL, 'Sarah Tait', 2, FALSE, FALSE, 5),
(31, 'SF', '1+/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Undergraduate Student Research Awards (USRA) (URU) (6862)', NULL, NULL, NULL, 'Catherine Harrison', 2, FALSE, FALSE, 22),
(32, 'CCPP', '1+/YR', NULL, FALSE, FALSE, FALSE, 'Idea to Innovation (I2I) - (I2IPJ) (6036)', NULL, NULL, NULL, 'France Vaillancourt', 2, FALSE, FALSE, 22),
(33, 'EER/MCT', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Industrial Research Chairs (IRC) (6235)', NULL, NULL, NULL, 'Samir Boughaba', 2, FALSE, FALSE, 16),
(34, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Postdoctoral Fellowships (PDF) (6790)', NULL, NULL, NULL, 'Valerie Harbour, Matt Vincelli', 2, FALSE, FALSE, 16),
(35, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Postgraduate Scholarships - Doctoral  PGS-D2 (Direct and University) (6798)', NULL, NULL, NULL, 'Valerie Harbour, Matt Vincelli', 2, FALSE, FALSE, 24),
(36, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Postgraduate Scholarships - Doctoral  PGS-D3 (Direct and University) (6799)', NULL, NULL, NULL, 'Valerie Harbour, Matt Vincelli', 2, FALSE, FALSE, 24),
(37, 'EER/MCT', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Strategic Parnerships - Networks (SPG-N)', NULL, NULL, NULL, 'Claire McAneney', 2, FALSE, FALSE, 21),
(38, 'EER/MCT', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Strategic Partnerships - Projects (SPG-P)', NULL, NULL, NULL, 'Kristina Archibald', 2, FALSE, FALSE, 29),
(39, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Applied Research Tools and Instruments Grants (ARTI) (CARTI) (6004)', NULL, NULL, NULL, 'Adela Reid', 2, FALSE, FALSE, 25),
(40, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Belmont Forum (BFAOR) (5807)', NULL, NULL, NULL, 'Rawni Sharp', 2, FALSE, FALSE, 2),
(41, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Belmont Forum (BFBIO) (5808)', NULL, NULL, NULL, 'Rawni Sharp', 2, FALSE, FALSE, 23),
(42, 'ICSP', '1/Year', 'Scholarships', FALSE, FALSE, FALSE, 'CREATE (6648)', NULL, NULL, NULL, 'Guy Faubert/Philippe Desjardins', 2, FALSE, FALSE, 1),
(43, 'ELS', 'Open', NULL, FALSE, FALSE, FALSE, 'DND/NSERC Research Partnership (DNDPJ) (6467)', NULL, NULL, NULL, NULL, 2, FALSE, FALSE, 29);
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(44, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Human Frontiers in Science Program (HFSP) (5700)', NULL, NULL, NULL, 'Rawni Sharp', 2, FALSE, FALSE, 16),
(45, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Innovation Enhancement Grants - Build (IE) CCIP) (6013)', NULL, NULL, NULL, 'Adela Reid', 2, FALSE, FALSE, 24),
(46, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Innovation Enhancement Grants - Entry (IE) (CCIPE) (6008)', NULL, NULL, NULL, 'Adela Reid', 2, FALSE, FALSE, 15),
(47, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Innovation Enhancement Grants - Extend (IE) (CCIPX) (6016)', NULL, NULL, NULL, 'Adela Reid', 2, FALSE, FALSE, 10),
(48, 'MEPS', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Ship Time (RGPST) (5822)', NULL, NULL, NULL, 'Rawni Sharp', 2, FALSE, FALSE, 16),
(49, NULL, '1/YR', NULL, FALSE, FALSE, FALSE, 'Strategic Network Grants Program (NETGP) (6070)', NULL, NULL, NULL, NULL, 2, FALSE, FALSE, 2),
(50, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Subatomic Physics - Individual (SAPIN) (5840)', STRINGDECODE('Programme de subventions \u00e0 la d\u00e9couverte en physique subatomique'), NULL, NULL, 'Emily Diepenveen', 2, FALSE, FALSE, 30),
(51, NULL, '1/YR', NULL, FALSE, FALSE, FALSE, 'Synergy Awards (Colleges) CSYN (6477)', NULL, NULL, NULL, NULL, 2, FALSE, FALSE, 17),
(52, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Thematic Resources Support in Mathematics and Statistics (CTRMS)', STRINGDECODE('Programme d''appui aux ressources th\u00e9matiques  en math\u00e9matiques et en statistique'), NULL, NULL, 'Caroline Bicker', 2, FALSE, FALSE, 18),
(53, 'ELS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Discovery Development Grant (DDG)', NULL, NULL, NULL, 'Marie-Claude Caron', 2, FALSE, FALSE, 12),
(54, 'MEPS', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Discovery Grants - Northern Research Supplement (NRS)', STRINGDECODE('Programme de suppl\u00e9ments aux subventions \u00e0 la d\u00e9couverte en recherche nordique'), NULL, NULL, 'Rawni Sharp', 2, FALSE, FALSE, 27),
(55, 'ELS/MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Discovery Grants (DG) (RGPIN)', STRINGDECODE('Programme de subventions \u00e0 la d\u00e9couverte'), NULL, NULL, 'Eniko Megyeri-Lawless', 2, FALSE, FALSE, 25),
(56, 'ELS', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'DND/NSERC Discovery Grant Supplement  (DGDND)', STRINGDECODE('Suppl\u00e9ment aux subventions \u00e0 la d\u00e9couverte MDN-CRSNG'), NULL, NULL, 'Natalie Weiskopf', 2, FALSE, FALSE, 30),
(57, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Research Tools and Instruments (RTI)', NULL, NULL, NULL, 'Caroline Bicker', 2, FALSE, FALSE, 20),
(58, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Subatomic Physics - Projects (SAPPJ) - small and large projects', NULL, NULL, NULL, 'Emily Diepenveen', 2, FALSE, FALSE, 18),
(59, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Subatomic Physics - Research Tools and Instruments (SAP-RTI Cat. 1)', NULL, NULL, NULL, 'Emily Diepenveen', 2, FALSE, FALSE, 1),
(60, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Subatomic Physics - Research Tools and Instruments (SAP-RTI Cat. 2 & 3)', NULL, NULL, NULL, 'Emily Diepenveen', 2, FALSE, FALSE, 5),
(61, 'MEPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Subatomic Physics Major Resources Support (SAPMR)', NULL, NULL, NULL, 'Emily Diepenveen', 2, FALSE, FALSE, 18),
(62, 'RD', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Connect Grants Level 1 for colleges', 'Subventions Connexion  Niveau 1  de College', NULL, NULL, 'Cecile Laplante, Sarah O''Neill, Kathleen Lorenzo', 2, FALSE, FALSE, 22),
(63, 'RD', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Connect Grants Level 1 for universities', 'Subventions Connexion  Niveau 1  des Universities', NULL, NULL, 'Cecile Laplante, Sarah O''Neill, Kathleen Lorenzo', 2, FALSE, FALSE, 15),
(64, 'RD', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Connect Grants Level 2 for colleges', 'Subventions Connexion  Niveau 2  de College', NULL, NULL, 'Cecile Laplante, Sarah O''Neill, Kathleen Lorenzo', 2, FALSE, FALSE, 28);
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(65, 'RD', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Connect Grants Level 2 for universities', 'Subventions Connexion  Niveau 2  des Universities', NULL, NULL, 'Cecile Laplante, Sarah O''Neill, Kathleen Lorenzo', 2, FALSE, FALSE, 8),
(66, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, STRINGDECODE('L''Or\u00e9al-UNESCO For Women in Science \u2013 NSERC Postdoctoral Fellowship Supplement'), STRINGDECODE('Suppl\u00e9ment du CRSNG et de L''Or\u00e9al-UNESCO pour les femmes et la science'), NULL, NULL, 'Joanna Renwick', 2, FALSE, FALSE, 25),
(67, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'STEAM Horizon Awards (6850)', 'Prix Horizon STIAM', NULL, NULL, 'Kristina Dixie', 2, FALSE, FALSE, 3),
(68, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Discovery Frontiers ) (RGPBB)', STRINGDECODE('Fronti\u00e8res de la d\u00e9couverte'), NULL, NULL, 'Elizabeth Boston, Lola CausynRawni Sharp', 2, FALSE, FALSE, 23),
(69, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Discovery Frontiers  (RGPDD)', STRINGDECODE('Fronti\u00e8res de la d\u00e9couverte'), NULL, NULL, 'Elizabeth Boston, Lola CausynRawni Sharp', 2, FALSE, FALSE, 13),
(70, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Discovery Frontiers  (RGPDF)', STRINGDECODE('Fronti\u00e8res de la d\u00e9couverte'), NULL, NULL, 'Elizabeth Boston, Lola CausynRawni Sharp', 2, FALSE, FALSE, 11),
(71, 'MEPS', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Discovery Frontiers  (RGPGR)', STRINGDECODE('Fronti\u00e8res de la d\u00e9couverte'), NULL, NULL, 'Elizabeth Boston, Lola CausynRawni Sharp', 2, FALSE, FALSE, 25),
(72, 'ELS/MEPS', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Discovery Grants - Accelerator Supplement (DAS)', STRINGDECODE('Programme de suppl\u00e9ments d''acc\u00e9l\u00e9ration \u00e0 la d\u00e9couverte'), NULL, NULL, 'Tiffany LancasterCeline Berube', 2, FALSE, FALSE, 1),
(73, NULL, NULL, NULL, FALSE, FALSE, FALSE, 'Science Exposed', 'La preuve par l''image', NULL, NULL, 'Michelle Roy', 2, FALSE, FALSE, 19),
(74, NULL, NULL, NULL, FALSE, FALSE, FALSE, 'Science, Action!', 'Science, Action!', NULL, NULL, 'Quinn Damery, Ian Duchesne', 2, FALSE, FALSE, 24),
(75, NULL, 'Open', NULL, FALSE, FALSE, FALSE, 'College Special Initiatives (CSI)', NULL, NULL, NULL, NULL, 2, FALSE, FALSE, 10),
(76, 'ICSP', '1/Year', 'Fellowships', FALSE, FALSE, FALSE, 'EWR Steacie Fellowships (Research and Salary) (SMFSU) (5265)', STRINGDECODE('Bourses comm\u00e9moratives E.W.R. Steacie'), NULL, NULL, 'Guy Faubert', 2, FALSE, FALSE, 11),
(77, NULL, NULL, 'Industrial Research Chairs (IRCPJ) (6235)', FALSE, FALSE, FALSE, 'IRC', 'professeurs-chercheurs industriels', NULL, NULL, 'Open', 2, FALSE, FALSE, 25),
(78, NULL, NULL, 'Research Grants', FALSE, FALSE, FALSE, 'Industrial Research Chairs (IRCSA) (6235)', 'Subventions de professeurs-chercheurs industriels', NULL, NULL, 'Open', 2, FALSE, FALSE, 9),
(79, NULL, 'Open', NULL, FALSE, FALSE, FALSE, 'Parental Leave - Research Grants', NULL, NULL, NULL, 'Not Applicable', 2, FALSE, FALSE, 16),
(80, NULL, 'Open', NULL, FALSE, FALSE, FALSE, 'Parental Leave - Scholarships & Fellowships', NULL, NULL, NULL, 'Not Applicable', 2, FALSE, FALSE, 22),
(81, NULL, '1/YR', NULL, FALSE, FALSE, FALSE, 'Synergy Awards (Universities) (SYN) (5239)', NULL, NULL, NULL, NULL, 2, FALSE, FALSE, 20),
(82, 'TIP', '2/YR', NULL, FALSE, FALSE, FALSE, 'Canada Research Chairs (CRC)', 'Chaires de recherche du Canada', NULL, NULL, 'Chris Kelly', 1, FALSE, FALSE, 4),
(83, 'RGPD', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Connections (HSSFC)  (650)', STRINGDECODE('Laur\u00e9ate du prix Connexion'), NULL, NULL, STRINGDECODE('Melissa Gavin/Fran\u00e7ois Simard'), 1, FALSE, FALSE, 24),
(84, 'FCD', '1/YR+', 'Research Grants', FALSE, FALSE, FALSE, 'Knowledge Synthesis Grant (872)', STRINGDECODE('Subventions de synth\u00e8se des connaissances'), NULL, NULL, 'Rachel Conlon', 1, FALSE, FALSE, 1),
(85, 'TIPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Canada 150 Research Chairs Program', 'Chaires de recherche Canada 150', NULL, NULL, 'Denis Leclerc', 1, FALSE, FALSE, 28);
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(86, 'TIPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Canada Excellence Research Chairs (CERC)', 'Chaires d''excellence en recherche du Canada', NULL, NULL, 'Alysha Croker', 1, FALSE, FALSE, 25),
(87, 'TIPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Canada First Research Excellence Fund (CFREF)', STRINGDECODE('Fonds d''excellence en recherche Apog\u00e9e Canada'), NULL, NULL, 'Alysha Croker', 1, FALSE, FALSE, 19),
(88, 'RTP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Impact Awards - Connection (780)', 'Prix Impacts du CRSH Prix Connexion', NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 13),
(89, 'RTP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Impact Awards - Gold Medal (758)', STRINGDECODE('Prix Impacts du CRSH M\u00e9daille d''or'), NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 26),
(90, 'RTP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Impact Awards - Insight (781)', 'Prix Impacts du CRSH Prix Savoir', NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 26),
(91, 'RTP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Impact Awards - Partnership 9782)', 'Prix Impacts du CRSH Prix Partenariat', NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 3),
(92, 'RTP', '1/YR', 'Prize', FALSE, FALSE, FALSE, 'Impact Awards - Talent (783)', 'Prix Impacts du CRSH Prix Talent', NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 27),
(93, 'RGPD', '1/Year', NULL, FALSE, FALSE, FALSE, STRINGDECODE('Indigenous Research Capacity and Reconciliation \u2014 Connection Grants'), STRINGDECODE('Appel \u00e0 propositions sp\u00e9cial pour l''attribution des subventions Connexion \u2013 Capacit\u00e9 de recherche autochtone et r\u00e9conciliation'), NULL, NULL, 'Lina Crompton', 1, FALSE, FALSE, 30),
(94, 'RGPD', '1/YR', 'Research Grants', FALSE, FALSE, FALSE, 'Insight Development Grants (430)', NULL, NULL, NULL, STRINGDECODE('Lorraine Anderson/Fran\u00e7ois Simard'), 1, FALSE, FALSE, 30),
(95, 'RTP', '1/YR', NULL, FALSE, FALSE, FALSE, 'Nelson Mandela', NULL, NULL, NULL, 'Stephanie Robertson', 1, FALSE, FALSE, 15),
(96, 'RGPD', '1/YR+', 'Research Grant', FALSE, FALSE, FALSE, 'SSHRC Institutional Grant (633)', 'Subventions institutionnelles du CRSH', NULL, NULL, 'Lorraine Anderson', 1, FALSE, FALSE, 2),
(97, 'Comms', '1/Year', 'Prize', FALSE, FALSE, FALSE, 'StoryTellers', STRINGDECODE('J''ai une histoire \u00e0 raconter'), NULL, NULL, 'Alex Myers, Kenneth Downs', 1, FALSE, FALSE, 17),
(98, 'RTP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Bora Laskin National Fellowship (754)', 'Bourse canadienne Bora-Laskin', NULL, NULL, 'Nadine May', 1, FALSE, FALSE, 8),
(99, 'RTP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, STRINGDECODE('Jules and Gabrielle L\u00e9ger (759)'), NULL, NULL, NULL, 'Nadine May', 1, FALSE, FALSE, 15),
(100, 'RTP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Queen''s Fellowship (220 or 321)', NULL, NULL, NULL, 'Nadine May', 1, FALSE, FALSE, 4),
(101, 'RTP', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Aileen D. Ross (230)', 'Aileen D. Ross', NULL, NULL, 'Nadine May', 1, FALSE, FALSE, 11),
(102, 'RTP', '1/YR', 'Internships', FALSE, FALSE, FALSE, 'Mitacs Elevate', NULL, NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 24),
(103, 'RGPD', '1/YR+', NULL, FALSE, FALSE, FALSE, 'Aid to Scholarly Journals (651)', 'Aide aux revues savantes', NULL, NULL, 'Lorraine Anderson', 1, FALSE, FALSE, 5),
(104, 'RTP', '1/YR', 'Fellowships', FALSE, FALSE, FALSE, 'Centre for International Governance Innovation (CIGI)', NULL, NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 22),
(105, 'RTP', '1/YR', 'Fellowships', FALSE, FALSE, FALSE, 'CMHC Housing research Training Awards', NULL, NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 22),
(106, 'RGPD', '1+/YR', NULL, FALSE, FALSE, FALSE, 'Connection Grants (611)', 'Subventions Connections', NULL, NULL, 'Natalie Hunter/Lina Crompton', 1, FALSE, FALSE, 9),
(107, 'rgpd', 'Open', NULL, FALSE, FALSE, FALSE, 'Department of National Defense Research Initiative (877)', NULL, NULL, NULL, 'Chantal Meda', 1, FALSE, FALSE, 19),
(108, 'RTP', '1/YR', 'Scholarship', FALSE, FALSE, FALSE, 'Doctoral Awards - CGS; Direct; National (752)', NULL, NULL, NULL, 'Stephanie Robertson', 1, FALSE, FALSE, 3);
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(109, 'RGPD', '1+/Yr', 'Research Grants', FALSE, FALSE, FALSE, 'Insight Grants (435)', NULL, NULL, NULL, STRINGDECODE('Lorraine Anderson/ Fran\u00e7ois Simard'), 1, FALSE, FALSE, 16),
(110, 'RGPD', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Partnership Development Grants (890)', NULL, NULL, NULL, 'Mika Oehling/Kim Miller', 1, FALSE, FALSE, 22),
(111, 'RGPD', '1+/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Partnership Engage Grant', NULL, NULL, NULL, STRINGDECODE('\u00c9ric Bergeron/Lauren Matheson'), 1, FALSE, FALSE, 29),
(112, 'RGPD', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Partnership Grants - Stage 1', NULL, NULL, NULL, 'Stefani Di Gaetano', 1, FALSE, FALSE, 19),
(113, 'RGPD', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Partnership Grants - Stage 2', NULL, NULL, NULL, 'Paula Popovici', 1, FALSE, FALSE, 14),
(114, 'RTP', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Postdoctoral Fellowships (756)', NULL, NULL, NULL, 'Illa Carrillo Rodriguez', 1, FALSE, FALSE, 15),
(115, 'RGPD/RTP', 'Open', NULL, FALSE, FALSE, FALSE, 'Sports Participation Research Initative (862)', NULL, NULL, NULL, 'Melissa Gavin', 1, FALSE, FALSE, 6),
(116, 'RTP', 'Open', NULL, FALSE, FALSE, FALSE, 'Parental Leave - Scholarships & Fellowships (SSHRC) (769)', NULL, NULL, NULL, 'Monique Chaumont', 1, FALSE, FALSE, 13),
(117, 'ICSP', 'Open', NULL, FALSE, FALSE, FALSE, 'Parental Leave - Scholarships and Fellowships through grants (SSHRC)', NULL, NULL, NULL, 'Not Applicable', 1, FALSE, FALSE, 13),
(118, 'RTP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Parliamentary Internship Program (750)', 'Programme de stage parlementaire', NULL, NULL, 'Melissa Dubreuil', 1, FALSE, FALSE, 30),
(119, 'RTP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Queen Elizabeth (774)', NULL, NULL, NULL, 'Nadine May', 1, FALSE, FALSE, 17),
(120, NULL, 'Open', NULL, FALSE, FALSE, FALSE, 'Special Initiatives Fund For Research Support And Collaboration', NULL, NULL, NULL, NULL, 1, FALSE, FALSE, 2),
(121, 'TIPS', '1/YR', NULL, FALSE, FALSE, FALSE, 'Research Support Fund (RSF)', NULL, NULL, NULL, 'Denis Leclerc', 2, FALSE, FALSE, 19),
(122, 'NCE', NULL, 'Partnerships Programs', FALSE, FALSE, FALSE, 'CECR Centres of Excellence for Commercialization and Research', ' Programme des centres d''excellence en commercialisation et en recherche', NULL, NULL, 'Denis Godin', 2, FALSE, FALSE, 2),
(123, 'SF', '1+/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Canada Graduate Scholarships Michael Smith Foreign Study Supplements (MSFSS)', STRINGDECODE('Programme de bourses d''\u00e9tudes sup\u00e9rieures du Canada \u2013 Suppl\u00e9ments pour \u00e9tudes \u00e0 l''\u00e9tranger Michael-Smith'), NULL, NULL, 'Kayla Zavitske', 2, FALSE, FALSE, 13),
(124, 'CCPP', 'Open', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Applied Research and Development Grants - Colleges (ARD)', NULL, NULL, NULL, 'Edward Irving', 2, FALSE, FALSE, 19),
(125, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'College - University Idea to Innovation Grants (CU-I2I)', NULL, NULL, NULL, 'Marie Thibault', 2, FALSE, FALSE, 1),
(126, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Industrial Research Chairs for Colleges Grants (IRCC)', NULL, NULL, NULL, 'Marie Thibault', 2, FALSE, FALSE, 2),
(127, 'SF', '1/YR', 'Supplement', FALSE, FALSE, FALSE, 'Alice Wilson', 'Alice Wilson', NULL, NULL, 'Melissa Dubreuil (SSHRC lead)', 2, FALSE, FALSE, 17),
(128, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Banting Postdoctoral Fellowships', 'Bourses postdoctorales Banting', NULL, NULL, 'Frank Zegers (CIHR)', 2, FALSE, FALSE, 16),
(129, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Vanier', NULL, NULL, NULL, 'Frank Zegers (CIHR)', 2, FALSE, FALSE, 16),
(130, 'SF', '1/YR', 'Scholarships', FALSE, FALSE, FALSE, 'Canada Graduate Scholarship - Masters (CGSM)', STRINGDECODE('Bourses d''\u00e9tudes sup\u00e9rieures du Canada'), NULL, NULL, 'Catherine Podeszfinski? (NSERC), Roxanne Dompierre (SSHRC) Jule Conrad (CIHR)', 2, FALSE, FALSE, 20);
INSERT INTO "PUBLIC"."FUNDING_OPPORTUNITY" VALUES
(131, 'NCE', NULL, 'Partnerships Programs', FALSE, FALSE, FALSE, 'Business-Led Networks of Centres of Excellence program', STRINGDECODE('Programme des r\u00e9seaux de centres d''excellence dirig\u00e9s par l''entreprise'), NULL, NULL, 'Denis Godin', 2, FALSE, FALSE, 22),
(132, 'NCE', NULL, 'Partnerships Programs', FALSE, FALSE, FALSE, 'International Knowledge Translation Platforms initiative (IKTP)', 'Initiative de soutien international pour le transfert des connaissances (SITC)', NULL, NULL, 'Brigit Viens', 2, FALSE, FALSE, 28),
(133, 'NCE', NULL, 'Partnerships Programs', FALSE, FALSE, FALSE, 'NCE Knowledge Mobilization (NCE-KM)', 'L''initiative de Mobilisation des connaissances (MC-RCE)', NULL, NULL, 'Monika Michalska', 2, FALSE, FALSE, 1),
(134, 'NCE', NULL, 'Partnerships Programs', FALSE, FALSE, FALSE, 'Network for Centers of Excellence', STRINGDECODE('Programme des r\u00e9seaux de centres d''excellence (RCE)'), NULL, NULL, 'Tia Moffat', 2, FALSE, FALSE, 15),
(135, 'CCPP', '1/YR', 'Partnerships Programs', FALSE, FALSE, FALSE, 'Technology Access Centre (TAC)', STRINGDECODE('subventions d''\u00e9tablissement de centres d''acc\u00e8s \u00e0 la technologie'), NULL, NULL, 'Marie Thibault', 2, FALSE, FALSE, 30),
(136, NULL, '1/YR+', NULL, FALSE, FALSE, FALSE, 'Canadian Initiative on social Statistics', NULL, NULL, NULL, NULL, 2, FALSE, FALSE, 20),
(137, NULL, '1/YR+', NULL, FALSE, FALSE, FALSE, 'Digging into Data', STRINGDECODE('Au c\u0153ur des donn\u00e9es num\u00e9riques'), NULL, NULL, NULL, 2, FALSE, FALSE, 6),
(138, 'EER/MCT/RD', NULL, 'Partnerships Programs', FALSE, FALSE, FALSE, 'Research Partnerships Renewal Project (RPR)', NULL, NULL, NULL, 'TBD', 2, FALSE, FALSE, 6),
(139, 'TIPS', '1/YR for first year; unknown after that', 'Research Grant', FALSE, FALSE, FALSE, 'Frontiers in Research Fund - Stream 1', NULL, NULL, NULL, 'Creed Millman', 1, FALSE, FALSE, 19),
(140, 'TIPS', '1/YR in second year; unknown after that', 'Research Grant', FALSE, FALSE, FALSE, 'Frontiers in Research Fund - Stream 2', NULL, NULL, NULL, 'Creed Millman', 1, FALSE, FALSE, 6),
(141, 'TIPS', 'Unknown, but likely 1/YR in second year', 'Research Grant', FALSE, FALSE, FALSE, 'Frontiers in Research Fund - Special Calls', NULL, NULL, NULL, 'Creed Millman', 1, FALSE, FALSE, 6);
ALTER TABLE "PUBLIC"."FISCAL_YEAR" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" UNIQUE("YEAR");
ALTER TABLE "PUBLIC"."SYSTEM_FUNDING_OPPORTUNITY" ADD CONSTRAINT "PUBLIC"."FK_SYSTEM_FUNDING_OPPORTUNITY_FUNDING_OPPORTUNITY" FOREIGN KEY("LINKED_FUNDING_OPPORTUNITY_ID") REFERENCES "PUBLIC"."FUNDING_OPPORTUNITY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."MEMBER_ROLE" ADD CONSTRAINT "PUBLIC"."FK_MEMBER_ROLE_BUSINESS_UNIT" FOREIGN KEY("BUSINESS_UNIT_ID") REFERENCES "PUBLIC"."BUSINESS_UNIT"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."FUNDING_CYCLE" ADD CONSTRAINT "PUBLIC"."FK_FUNDING_CYCLE_FISCAL_YEAR" FOREIGN KEY("FISCAL_YEAR_ID") REFERENCES "PUBLIC"."FISCAL_YEAR"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."SYSTEM_FUNDING_OPPORTUNITY" ADD CONSTRAINT "PUBLIC"."FK_SYSTEM_FUNDING_OPPORTUNITY_GRANTING_SYSTEM" FOREIGN KEY("GRANTING_SYSTEM_ID") REFERENCES "PUBLIC"."GRANTING_SYSTEM"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."FUNDING_OPPORTUNITY" ADD CONSTRAINT "PUBLIC"."FK_FUNDING_OPPORTUNITY_BUSINESS_UNIT" FOREIGN KEY("BUSINESS_UNIT_ID") REFERENCES "PUBLIC"."BUSINESS_UNIT"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."BUSINESS_UNIT" ADD CONSTRAINT "PUBLIC"."FK_BUSINESS_UNIT_AGENCY" FOREIGN KEY("AGENCY_ID") REFERENCES "PUBLIC"."AGENCY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."MEMBER_ROLE" ADD CONSTRAINT "PUBLIC"."FK_MEMBER_ROLE_ROLE" FOREIGN KEY("ROLE_ID") REFERENCES "PUBLIC"."ROLE"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES" ADD CONSTRAINT "PUBLIC"."FK_FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES_AGENCY" FOREIGN KEY("PARTICIPATING_AGENCIES_ID") REFERENCES "PUBLIC"."AGENCY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."FUNDING_CYCLE" ADD CONSTRAINT "PUBLIC"."FK_FUNDING_CYCLE_FUNDING_OPPORTUNITY" FOREIGN KEY("FUNDING_OPPORTUNITY_ID") REFERENCES "PUBLIC"."FUNDING_OPPORTUNITY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."FUNDING_OPPORTUNITY" ADD CONSTRAINT "PUBLIC"."FK_FUNDING_OPPORTUNITY_AGENCY" FOREIGN KEY("LEAD_AGENCY_ID") REFERENCES "PUBLIC"."AGENCY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES" ADD CONSTRAINT "PUBLIC"."FK_FUNDING_OPPORTUNITY_PARTICIPATING_AGENCIES_FUNDING_OPPORTUNITY" FOREIGN KEY("FUNDING_OPPORTUNITY_ID") REFERENCES "PUBLIC"."FUNDING_OPPORTUNITY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."GRANTING_CAPABILITY" ADD CONSTRAINT "PUBLIC"."FK_GRANTING_CAPABILITY_FUNDING_OPPORTUNITY" FOREIGN KEY("FUNDING_OPPORTUNITY_ID") REFERENCES "PUBLIC"."FUNDING_OPPORTUNITY"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."SYSTEM_FUNDING_CYCLE" ADD CONSTRAINT "PUBLIC"."FK_SYSTEM_FUNDING_CYCLE_SYSTEM_FUNDING_OPPORTUNITY" FOREIGN KEY("SYSTEM_FUNDING_OPPORTUNITY_ID") REFERENCES "PUBLIC"."SYSTEM_FUNDING_OPPORTUNITY"("ID") NOCHECK;