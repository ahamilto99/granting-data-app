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

INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (1,'2021-03-01',6404,'TRUE','2023-08-11',1,89,'2024-12-20','2021-03-26','2021-03-29','2023-12-10'),(2,'2021-04-20',3033,'FALSE','2021-11-13',2,90,'2022-04-04','2021-01-30','2025-03-24','2022-03-02'),(3,'2021-04-26',5736,'TRUE','2023-06-25',3,6,'2022-05-21','2021-02-10','2023-01-18','2024-05-08'),(4,'2021-02-14',8446,'TRUE','2024-06-13',3,103,'2021-01-12','2021-01-05','2021-08-24','2025-04-08'),(5,'2021-04-08',5064,'FALSE','2021-05-07',3,89,'2023-04-01','2021-04-04','2024-11-20','2024-05-06'),(6,'2021-01-30',7527,'FALSE','2024-03-19',2,93,'2021-12-17','2021-04-26','2025-02-18','2022-05-07'),(7,'2021-01-14',1426,'TRUE','2024-05-09',3,21,'2025-02-05','2021-02-18','2024-03-04','2023-05-31'),(8,'2021-04-02',907,'TRUE','2021-10-25',1,130,'2022-03-30','2021-04-01','2025-04-05','2021-10-14'),(9,'2021-03-30',100,'FALSE','2021-06-02',2,123,'2023-12-06','2021-03-29','2024-10-29','2023-01-10'),(10,'2021-04-21',8957,'FALSE','2023-03-03',1,132,'2023-08-11','2021-03-30','2022-09-08','2024-09-21');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (11,'2021-02-04',5802,'TRUE','2024-04-23',1,120,'2024-11-14','2021-01-29','2021-05-05','2023-05-19'),(12,'2021-04-20',388,'TRUE','2024-02-09',1,92,'2023-06-01','2021-03-13','2022-05-13','2021-09-25'),(13,'2021-03-24',6118,'FALSE','2024-02-07',2,68,'2022-08-13','2021-03-08','2023-05-28','2021-01-12'),(14,'2021-03-30',4554,'FALSE','2021-08-12',1,52,'2022-12-05','2021-01-24','2023-12-27','2023-07-14'),(15,'2021-01-15',6452,'TRUE','2022-02-02',3,11,'2022-10-27','2021-03-24','2024-11-21','2024-11-02'),(16,'2021-04-15',2409,'FALSE','2024-07-06',3,126,'2023-08-08','2021-02-19','2023-05-25','2022-02-20'),(17,'2021-02-20',2862,'TRUE','2022-07-21',1,72,'2023-04-21','2021-01-22','2023-11-23','2024-07-15'),(18,'2021-02-14',2441,'FALSE','2023-12-28',3,35,'2024-12-17','2021-02-09','2024-03-30','2025-01-04'),(19,'2021-03-08',8174,'FALSE','2023-12-11',2,134,'2023-02-13','2021-02-14','2023-04-28','2023-11-14'),(20,'2021-02-06',6003,'FALSE','2024-08-09',3,64,'2022-09-12','2021-01-06','2025-02-23','2021-11-16');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (21,'2021-01-26',1679,'FALSE','2023-07-24',1,104,'2023-03-13','2021-02-10','2023-04-25','2023-09-29'),(22,'2021-04-19',1218,'TRUE','2024-05-15',3,57,'2023-09-28','2021-04-21','2021-10-21','2023-04-03'),(23,'2021-03-26',7207,'TRUE','2024-02-02',2,116,'2023-02-23','2021-04-28','2024-05-18','2024-05-11'),(24,'2021-03-24',2941,'FALSE','2023-02-28',1,57,'2022-10-01','2021-02-09','2022-04-13','2022-10-06'),(25,'2021-03-04',7335,'TRUE','2023-04-13',3,55,'2021-08-18','2021-01-08','2025-03-15','2023-10-02'),(26,'2021-03-30',4170,'TRUE','2021-04-23',3,122,'2021-03-31','2021-04-18','2024-07-20','2022-02-27'),(27,'2021-01-22',3588,'TRUE','2021-04-11',3,95,'2024-05-04','2021-01-25','2023-07-24','2022-02-15'),(28,'2021-01-31',775,'TRUE','2023-03-16',2,101,'2021-04-17','2021-04-26','2022-01-09','2024-09-02'),(29,'2021-02-14',3179,'TRUE','2021-09-25',2,108,'2025-03-17','2021-04-19','2025-04-10','2023-02-14'),(30,'2021-02-08',7809,'FALSE','2022-08-19',2,56,'2021-05-31','2021-01-15','2023-09-25','2022-01-31');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (31,'2021-04-26',2352,'FALSE','2022-07-31',2,59,'2024-10-07','2021-03-02','2024-07-11','2021-07-12'),(32,'2021-04-04',4293,'TRUE','2023-11-07',1,26,'2023-12-20','2021-03-10','2022-04-30','2021-04-21'),(33,'2021-02-03',7402,'FALSE','2024-10-04',3,72,'2021-04-12','2021-03-23','2021-08-04','2022-10-08'),(34,'2021-01-27',1995,'TRUE','2021-05-14',1,76,'2024-07-22','2021-04-14','2021-10-29','2025-02-12'),(35,'2021-03-19',6723,'FALSE','2021-11-15',2,136,'2023-08-05','2021-02-13','2024-11-04','2022-09-04'),(36,'2021-03-20',3376,'FALSE','2023-02-28',1,17,'2023-09-20','2021-04-05','2022-04-29','2024-12-07'),(37,'2021-03-27',8173,'FALSE','2025-01-25',3,85,'2021-06-03','2021-04-10','2021-06-06','2024-04-18'),(38,'2021-02-17',7011,'TRUE','2021-06-09',3,80,'2022-01-01','2021-04-24','2021-02-14','2021-08-09'),(39,'2021-01-27',6054,'TRUE','2023-11-26',1,8,'2023-11-12','2021-04-06','2022-03-21','2024-08-06'),(40,'2021-01-20',9474,'TRUE','2022-08-12',3,34,'2022-08-17','2021-03-11','2025-03-02','2023-07-25');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (41,'2021-01-20',1811,'FALSE','2022-03-09',3,53,'2021-02-11','2021-01-20','2021-06-09','2021-10-08'),(42,'2021-04-01',3841,'FALSE','2022-05-14',3,102,'2023-09-21','2021-04-18','2023-08-21','2022-04-05'),(43,'2021-04-17',6554,'FALSE','2022-05-05',3,99,'2022-02-14','2021-04-01','2021-06-03','2024-04-07'),(44,'2021-02-24',5601,'FALSE','2021-06-12',2,110,'2021-10-19','2021-01-23','2024-12-07','2022-06-22'),(45,'2021-03-18',120,'TRUE','2025-01-17',3,62,'2021-02-01','2021-03-18','2024-03-20','2022-09-27'),(46,'2021-04-02',7516,'FALSE','2022-06-21',3,35,'2022-01-08','2021-02-05','2024-06-20','2022-06-01'),(47,'2021-03-29',7609,'FALSE','2024-02-27',3,9,'2022-05-22','2021-01-12','2021-05-16','2023-03-20'),(48,'2021-03-19',1660,'FALSE','2021-10-08',1,93,'2021-05-16','2021-02-12','2025-02-22','2024-01-28'),(49,'2021-01-31',4542,'FALSE','2022-04-23',3,118,'2023-08-10','2021-04-03','2021-12-24','2025-04-07'),(50,'2021-02-15',2476,'FALSE','2022-05-18',1,118,'2022-11-05','2021-03-03','2022-08-02','2024-06-23');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (51,'2021-04-03',1247,'FALSE','2024-03-01',2,111,'2022-01-26','2021-02-16','2021-03-11','2023-01-11'),(52,'2021-02-19',665,'TRUE','2025-02-17',2,2,'2022-10-27','2021-03-18','2024-02-17','2023-09-03'),(53,'2021-02-19',2689,'TRUE','2024-07-21',2,53,'2024-06-19','2021-04-23','2022-08-25','2024-07-08'),(54,'2021-03-20',9190,'TRUE','2024-05-08',2,58,'2023-01-31','2021-01-04','2022-05-06','2022-03-22'),(55,'2021-01-12',5483,'TRUE','2023-04-06',2,78,'2022-06-26','2021-01-09','2023-03-13','2024-11-12'),(56,'2021-04-13',19,'TRUE','2022-11-25',2,44,'2022-12-23','2021-02-05','2023-10-03','2023-03-05'),(57,'2021-03-27',6336,'FALSE','2024-01-01',1,14,'2024-12-31','2021-03-03','2024-10-20','2021-12-12'),(58,'2021-01-31',6234,'FALSE','2023-10-15',1,98,'2022-09-12','2021-01-11','2021-11-28','2025-01-05'),(59,'2021-02-21',2656,'TRUE','2021-03-04',1,70,'2021-08-13','2021-03-06','2024-02-23','2021-08-01'),(60,'2021-03-04',3182,'FALSE','2023-04-15',1,21,'2022-01-29','2021-02-18','2023-11-12','2021-09-09');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (61,'2021-02-19',6486,'FALSE','2022-10-15',3,54,'2022-05-04','2021-02-11','2023-11-04','2024-04-15'),(62,'2021-01-07',2270,'FALSE','2024-11-09',1,9,'2024-11-08','2021-02-26','2024-02-23','2024-06-27'),(63,'2021-01-25',3799,'TRUE','2022-02-02',2,130,'2021-05-08','2021-03-29','2023-10-07','2023-11-06'),(64,'2021-02-05',4077,'FALSE','2023-01-26',1,24,'2021-10-02','2021-01-05','2021-09-22','2022-02-21'),(65,'2021-03-06',4712,'TRUE','2024-07-21',3,85,'2021-04-03','2021-02-11','2023-03-15','2022-03-19'),(66,'2021-02-04',2919,'TRUE','2022-03-18',2,16,'2025-03-20','2021-04-15','2021-06-15','2021-06-02'),(67,'2021-04-10',1156,'TRUE','2024-02-26',1,48,'2021-07-14','2021-04-01','2021-12-27','2024-11-16'),(68,'2021-02-21',3021,'FALSE','2021-11-02',3,47,'2024-02-27','2021-03-17','2021-10-17','2021-04-22'),(69,'2021-01-27',4882,'TRUE','2021-10-01',2,12,'2022-02-07','2021-03-31','2022-07-18','2022-10-31'),(70,'2021-03-13',9371,'FALSE','2024-01-08',2,24,'2021-12-09','2021-04-26','2024-02-26','2022-11-24');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (71,'2021-03-15',3200,'FALSE','2022-06-10',2,69,'2024-09-20','2021-01-28','2024-09-20','2025-02-16'),(72,'2021-01-01',3218,'TRUE','2023-04-23',3,72,'2024-03-30','2021-04-22','2022-01-16','2024-11-30'),(73,'2021-03-21',6237,'TRUE','2025-01-31',1,66,'2023-10-08','2021-03-22','2024-04-17','2021-01-20'),(74,'2021-01-04',4729,'FALSE','2024-04-01',1,42,'2023-05-15','2021-02-01','2023-12-27','2023-08-06'),(75,'2021-03-15',1926,'TRUE','2024-10-24',2,141,'2021-06-28','2021-01-29','2022-08-05','2023-06-10'),(76,'2021-03-15',6223,'TRUE','2021-10-12',2,129,'2024-12-31','2021-04-08','2022-10-09','2022-05-10'),(77,'2021-03-31',6162,'TRUE','2022-04-02',2,15,'2024-06-12','2021-03-18','2022-09-30','2021-02-09'),(78,'2021-02-15',185,'TRUE','2021-02-10',3,84,'2023-06-03','2021-01-20','2023-11-14','2024-10-10'),(79,'2021-02-14',643,'FALSE','2022-09-14',3,46,'2024-12-04','2021-04-03','2022-02-21','2021-07-23'),(80,'2021-01-20',5462,'TRUE','2023-09-25',2,59,'2024-05-22','2021-03-12','2021-08-10','2022-11-19');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (81,'2021-04-20',4997,'TRUE','2024-03-07',3,37,'2021-04-16','2021-04-21','2023-01-28','2023-03-09'),(82,'2021-02-19',3995,'FALSE','2021-11-13',1,104,'2024-06-17','2021-02-09','2023-01-03','2024-11-26'),(83,'2021-02-16',6331,'TRUE','2024-03-14',1,54,'2021-09-23','2021-04-22','2023-03-23','2022-03-20'),(84,'2021-02-15',3882,'FALSE','2021-10-29',1,24,'2021-07-26','2021-02-15','2021-01-26','2021-08-19'),(85,'2021-01-17',9174,'FALSE','2023-10-15',2,51,'2024-05-06','2021-02-07','2022-03-31','2024-10-07'),(86,'2021-02-23',2935,'FALSE','2021-06-07',2,100,'2024-04-16','2021-04-01','2024-06-22','2024-06-08'),(87,'2021-02-26',1345,'FALSE','2021-11-20',3,33,'2024-07-01','2021-01-02','2022-05-13','2021-10-24'),(88,'2021-01-02',3040,'TRUE','2021-10-30',3,7,'2024-04-27','2021-01-08','2022-08-30','2022-08-28'),(89,'2021-04-28',9375,'TRUE','2025-02-16',3,12,'2025-02-03','2021-01-22','2021-04-22','2022-01-05'),(90,'2021-01-19',589,'FALSE','2022-02-15',3,15,'2025-03-26','2021-01-18','2021-05-01','2021-01-26');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (91,'2021-03-25',7223,'FALSE','2021-05-08',2,113,'2024-04-03','2021-02-23','2021-01-19','2021-01-20'),(92,'2021-04-18',7989,'TRUE','2023-02-05',3,24,'2021-02-05','2021-02-22','2023-07-12','2021-05-29'),(93,'2021-03-18',4219,'TRUE','2024-09-19',1,29,'2024-07-29','2021-03-26','2022-05-19','2024-10-12'),(94,'2021-04-20',8514,'FALSE','2024-06-29',2,138,'2024-02-25','2021-01-23','2022-12-16','2025-02-07'),(95,'2021-04-10',1445,'TRUE','2023-12-06',1,8,'2022-11-02','2021-01-08','2023-06-11','2024-03-20'),(96,'2021-01-09',9395,'TRUE','2023-07-07',3,14,'2021-03-17','2021-03-02','2022-07-04','2022-08-24'),(97,'2021-03-08',5999,'TRUE','2025-02-28',3,103,'2022-10-09','2021-02-25','2021-06-04','2025-02-17'),(98,'2021-01-24',9104,'FALSE','2021-12-28',1,65,'2023-11-11','2021-03-08','2023-04-27','2021-02-02'),(99,'2021-02-22',6523,'TRUE','2021-04-13',3,20,'2022-09-05','2021-03-06','2021-08-31','2023-12-28'),(100,'2021-03-09',6199,'FALSE','2024-08-26',1,28,'2025-03-01','2021-03-08','2022-09-24','2023-02-13');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (101,'2021-02-14',4226,'TRUE','2022-01-03',2,116,'2024-03-25','2021-01-02','2024-09-23','2025-04-08'),(102,'2021-03-14',2718,'FALSE','2021-03-13',1,108,'2022-08-18','2021-03-01','2021-11-07','2023-08-05'),(103,'2021-03-23',3311,'FALSE','2021-04-06',1,141,'2023-07-30','2021-02-22','2022-09-06','2023-09-27'),(104,'2021-01-19',1146,'TRUE','2024-08-23',2,133,'2025-01-11','2021-01-20','2023-08-22','2022-02-26'),(105,'2021-02-19',6239,'TRUE','2021-10-12',1,96,'2025-03-04','2021-04-12','2024-07-15','2024-01-13'),(106,'2021-02-20',4339,'TRUE','2021-07-10',3,6,'2024-10-29','2021-04-02','2021-03-20','2023-06-25'),(107,'2021-02-15',2792,'TRUE','2024-10-02',1,32,'2023-09-29','2021-01-03','2023-08-17','2021-08-12'),(108,'2021-04-12',7364,'TRUE','2021-04-10',2,57,'2021-09-29','2021-01-30','2023-06-25','2023-02-24'),(109,'2021-01-06',1365,'TRUE','2022-10-20',3,107,'2022-11-20','2021-03-19','2025-03-24','2024-01-25'),(110,'2021-02-06',5706,'FALSE','2024-11-13',1,79,'2022-03-28','2021-01-28','2023-09-08','2022-11-14');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (111,'2021-03-27',2009,'FALSE','2023-08-06',3,83,'2021-03-18','2021-01-18','2023-11-08','2024-01-13'),(112,'2021-03-31',6371,'FALSE','2024-07-27',3,62,'2021-10-20','2021-01-18','2024-11-04','2023-02-21'),(113,'2021-02-24',9689,'TRUE','2021-03-07',1,27,'2023-08-24','2021-01-27','2021-09-28','2022-01-09'),(114,'2021-04-22',5824,'TRUE','2023-09-01',1,133,'2023-07-12','2021-01-05','2023-10-01','2023-09-22'),(115,'2021-02-13',6687,'TRUE','2023-10-31',1,8,'2022-02-24','2021-02-07','2022-03-10','2022-09-02'),(116,'2021-02-09',437,'FALSE','2023-09-15',2,11,'2022-04-23','2021-03-13','2024-03-08','2021-12-01'),(117,'2021-02-21',7728,'FALSE','2022-10-17',2,44,'2023-09-13','2021-01-14','2021-02-06','2023-08-14'),(118,'2021-01-02',473,'TRUE','2024-05-28',1,75,'2024-07-13','2021-04-09','2022-03-08','2024-08-13'),(119,'2021-02-13',3923,'TRUE','2025-01-14',3,58,'2023-02-13','2021-04-20','2021-05-24','2024-09-05'),(120,'2021-04-09',1771,'FALSE','2021-08-02',1,121,'2021-08-21','2021-02-17','2024-03-03','2025-02-12');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (121,'2021-03-26',6912,'TRUE','2023-01-31',1,12,'2022-03-23','2021-01-10','2021-06-04','2022-09-07'),(122,'2021-03-13',4133,'TRUE','2021-08-25',2,87,'2023-04-09','2021-01-24','2022-02-02','2021-05-27'),(123,'2021-01-12',8520,'FALSE','2024-06-29',3,62,'2023-10-26','2021-02-12','2025-03-21','2024-11-23'),(124,'2021-03-16',8989,'FALSE','2021-01-20',2,130,'2023-01-12','2021-04-09','2022-03-26','2024-02-24'),(125,'2021-01-28',1179,'TRUE','2021-06-16',2,18,'2024-10-15','2021-02-04','2023-10-09','2022-05-20'),(126,'2021-01-24',8320,'FALSE','2022-12-08',2,15,'2021-02-27','2021-01-06','2022-04-05','2022-09-01'),(127,'2021-01-08',9151,'FALSE','2023-07-11',3,137,'2024-06-19','2021-04-14','2024-03-03','2022-08-24'),(128,'2021-01-20',2121,'TRUE','2024-07-18',3,92,'2024-09-18','2021-04-24','2023-09-21','2024-03-31'),(129,'2021-02-22',8034,'FALSE','2022-07-16',2,105,'2024-02-26','2021-01-18','2024-12-08','2025-04-07'),(130,'2021-02-11',6296,'TRUE','2023-03-26',2,108,'2024-06-25','2021-03-28','2024-02-21','2022-11-30');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (131,'2021-04-26',1091,'FALSE','2023-07-23',3,86,'2021-03-25','2021-04-05','2024-03-25','2022-10-18'),(132,'2021-04-25',5588,'FALSE','2025-01-07',3,116,'2021-05-16','2021-03-08','2021-03-25','2022-03-13'),(133,'2021-01-27',6309,'FALSE','2023-04-02',1,64,'2021-06-04','2021-01-13','2024-05-22','2023-04-26'),(134,'2021-04-11',3754,'FALSE','2021-02-09',2,40,'2023-06-17','2021-03-03','2022-04-02','2025-02-14'),(135,'2021-04-21',9483,'FALSE','2024-10-17',1,78,'2021-02-17','2021-03-20','2021-06-27','2021-03-01'),(136,'2021-01-04',4636,'TRUE','2023-05-03',3,109,'2021-01-08','2021-04-27','2023-06-04','2022-02-11'),(137,'2021-01-02',5853,'TRUE','2023-12-20',3,31,'2024-03-12','2021-04-12','2024-10-10','2023-11-09'),(138,'2021-01-03',1417,'FALSE','2024-08-26',2,141,'2022-10-13','2021-01-22','2021-08-31','2024-01-15'),(139,'2021-01-28',5755,'FALSE','2024-12-31',3,22,'2023-08-09','2021-02-18','2022-08-02','2023-04-12'),(140,'2021-01-26',8104,'FALSE','2024-08-03',2,135,'2024-12-15','2021-04-04','2023-06-21','2023-12-21');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (141,'2021-02-01',6329,'TRUE','2024-08-05',2,18,'2023-10-30','2021-01-13','2022-04-05','2021-11-20'),(142,'2021-03-08',3905,'FALSE','2024-05-15',3,80,'2024-01-19','2021-01-21','2022-12-06','2023-06-01'),(143,'2021-04-25',1767,'TRUE','2022-05-01',3,114,'2022-02-24','2021-02-11','2022-01-29','2023-01-14'),(144,'2021-01-14',95,'FALSE','2023-05-29',1,3,'2023-05-12','2021-01-24','2021-02-13','2022-06-23'),(145,'2021-04-25',9571,'FALSE','2022-04-09',3,19,'2024-01-27','2021-02-03','2022-02-25','2022-10-03'),(146,'2021-02-14',1645,'TRUE','2021-04-04',1,126,'2022-07-01','2021-03-18','2021-12-16','2023-04-06'),(147,'2021-04-13',3466,'FALSE','2023-01-07',2,46,'2022-04-07','2021-03-02','2023-11-02','2021-02-03'),(148,'2021-04-18',1625,'TRUE','2022-06-16',1,126,'2024-12-03','2021-01-02','2022-01-19','2023-04-04'),(149,'2021-01-05',6072,'FALSE','2021-09-07',1,42,'2021-01-22','2021-01-15','2023-12-26','2023-11-22'),(150,'2021-03-29',244,'FALSE','2023-09-02',3,3,'2022-08-26','2021-02-13','2023-09-29','2024-05-19');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (151,'2021-01-21',6931,'FALSE','2024-07-01',3,74,'2021-12-16','2021-01-23','2023-03-27','2024-06-17'),(152,'2021-04-09',6124,'FALSE','2022-06-12',1,122,'2022-01-25','2021-03-01','2024-06-13','2023-10-17'),(153,'2021-03-06',4973,'FALSE','2021-11-23',1,22,'2022-11-15','2021-01-03','2022-05-10','2023-03-01'),(154,'2021-03-18',4206,'TRUE','2021-05-06',1,68,'2022-10-21','2021-02-08','2024-02-23','2023-10-09'),(155,'2021-03-05',9911,'TRUE','2021-01-04',1,29,'2024-12-28','2021-01-07','2023-10-17','2023-01-13'),(156,'2021-02-15',7194,'TRUE','2023-12-08',1,74,'2022-04-22','2021-03-07','2024-08-08','2023-11-29'),(157,'2021-01-12',9577,'FALSE','2023-08-15',3,44,'2021-01-12','2021-04-20','2022-05-12','2024-05-04'),(158,'2021-02-20',5239,'FALSE','2024-02-21',2,61,'2023-01-21','2021-02-02','2024-05-04','2022-09-03'),(159,'2021-01-24',6731,'FALSE','2021-10-03',2,134,'2022-05-19','2021-01-15','2022-05-18','2023-11-16'),(160,'2021-03-06',8530,'FALSE','2024-04-15',1,10,'2023-10-23','2021-04-16','2022-09-29','2023-05-10');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (161,'2021-01-27',2890,'TRUE','2021-09-29',2,12,'2022-02-25','2021-03-02','2021-07-06','2025-04-03'),(162,'2021-01-15',1568,'TRUE','2023-11-17',2,58,'2021-08-21','2021-01-19','2021-06-04','2024-03-01'),(163,'2021-03-31',8977,'FALSE','2022-05-09',2,57,'2024-07-06','2021-01-24','2023-01-20','2021-04-25'),(164,'2021-03-27',4841,'FALSE','2022-06-28',1,116,'2021-01-18','2021-03-11','2024-04-18','2022-10-19'),(165,'2021-02-28',5710,'FALSE','2022-04-30',2,11,'2023-06-30','2021-03-01','2023-12-21','2022-09-10'),(166,'2021-02-03',9294,'FALSE','2023-12-17',3,8,'2022-04-11','2021-03-07','2021-03-16','2023-12-15'),(167,'2021-03-04',3406,'TRUE','2022-12-06',2,62,'2021-01-05','2021-02-03','2022-06-02','2021-01-30'),(168,'2021-02-15',8971,'FALSE','2023-01-07',1,6,'2022-06-14','2021-03-07','2024-01-08','2021-03-30'),(169,'2021-01-08',3048,'TRUE','2021-08-15',2,108,'2024-03-01','2021-02-22','2024-12-12','2022-08-18'),(170,'2021-03-24',3267,'FALSE','2022-06-29',2,75,'2023-01-03','2021-04-19','2024-07-09','2021-10-25');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (171,'2021-02-24',8635,'FALSE','2024-07-30',2,62,'2025-01-04','2021-01-27','2023-07-04','2023-07-23'),(172,'2021-01-03',8951,'TRUE','2022-07-14',2,13,'2023-01-23','2021-04-20','2024-11-22','2023-02-02'),(173,'2021-02-16',2182,'TRUE','2025-02-06',1,42,'2023-04-17','2021-03-15','2023-04-21','2022-12-14'),(174,'2021-01-21',3832,'TRUE','2023-02-03',2,141,'2021-10-08','2021-01-07','2021-02-06','2023-04-06'),(175,'2021-01-29',4941,'TRUE','2021-05-29',3,139,'2022-02-22','2021-02-16','2021-10-18','2023-01-05'),(176,'2021-02-18',6638,'FALSE','2024-12-10',3,72,'2021-11-08','2021-02-26','2022-10-14','2021-07-29'),(177,'2021-03-12',5786,'FALSE','2024-04-21',1,72,'2023-09-05','2021-03-16','2024-07-08','2021-01-07'),(178,'2021-03-26',4236,'FALSE','2023-03-16',3,105,'2021-07-26','2021-02-20','2022-01-24','2024-09-23'),(179,'2021-04-04',6052,'TRUE','2022-12-24',2,100,'2024-10-15','2021-02-01','2021-06-24','2023-06-27'),(180,'2021-03-15',138,'TRUE','2023-04-30',2,47,'2021-08-26','2021-02-01','2021-04-12','2021-10-21');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (181,'2021-02-24',2733,'FALSE','2024-10-12',2,54,'2022-03-04','2021-01-05','2021-02-28','2025-02-14'),(182,'2021-01-03',623,'FALSE','2023-06-03',1,20,'2025-03-04','2021-01-28','2021-07-28','2022-10-11'),(183,'2021-02-21',1197,'TRUE','2023-10-04',1,125,'2021-09-08','2021-03-20','2022-12-09','2022-02-14'),(184,'2021-01-05',1488,'TRUE','2024-09-15',1,92,'2022-11-17','2021-01-23','2021-11-14','2024-08-15'),(185,'2021-03-03',6823,'TRUE','2025-03-08',1,30,'2022-08-06','2021-02-03','2024-04-06','2022-01-20'),(186,'2021-04-19',5326,'FALSE','2021-01-04',1,101,'2024-11-20','2021-02-13','2023-07-31','2024-08-06'),(187,'2021-03-20',3795,'TRUE','2022-11-02',2,34,'2022-01-29','2021-03-30','2021-03-06','2024-04-05'),(188,'2021-02-11',5958,'FALSE','2022-06-21',2,41,'2022-06-09','2021-03-12','2022-09-24','2023-10-10'),(189,'2021-03-21',6672,'FALSE','2022-07-29',1,2,'2021-09-27','2021-03-05','2024-01-23','2021-12-16'),(190,'2021-03-01',690,'TRUE','2024-12-29',2,30,'2021-04-25','2021-01-23','2024-09-08','2023-04-29');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (191,'2021-03-01',9706,'TRUE','2022-11-01',1,4,'2023-10-10','2021-04-18','2022-02-09','2023-07-16'),(192,'2021-03-21',6623,'FALSE','2022-05-02',3,68,'2024-05-10','2021-04-27','2022-03-01','2022-03-28'),(193,'2021-03-19',993,'TRUE','2022-12-27',3,33,'2024-04-08','2021-01-13','2021-04-30','2023-02-18'),(194,'2021-03-06',7447,'TRUE','2021-03-23',3,46,'2024-05-17','2021-02-17','2021-02-09','2022-10-28'),(195,'2021-04-27',2956,'TRUE','2022-11-30',1,40,'2024-09-09','2021-02-18','2024-03-02','2023-01-12'),(196,'2021-04-07',3762,'FALSE','2022-05-17',1,8,'2022-02-12','2021-03-19','2023-07-13','2023-09-04'),(197,'2021-03-27',637,'TRUE','2024-08-11',2,33,'2023-11-24','2021-01-14','2025-03-15','2024-05-15'),(198,'2021-02-15',9087,'FALSE','2022-05-12',3,90,'2025-01-20','2021-04-16','2022-03-03','2021-01-11'),(199,'2021-03-09',3773,'FALSE','2023-08-27',1,64,'2021-08-28','2021-02-07','2021-03-18','2024-02-10'),(200,'2021-01-09',8638,'TRUE','2021-09-10',2,77,'2024-07-13','2021-04-18','2023-03-02','2022-10-15');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (201,'2021-01-24',3727,'TRUE','2024-09-24',2,63,'2024-11-03','2021-02-20','2021-09-28','2023-08-27'),(202,'2021-04-06',6189,'FALSE','2021-09-21',2,115,'2024-06-12','2021-01-02','2024-08-15','2024-08-17'),(203,'2021-02-22',719,'FALSE','2025-02-27',2,93,'2023-09-19','2021-02-27','2024-08-03','2022-04-25'),(204,'2021-04-18',208,'FALSE','2022-07-18',2,82,'2023-09-11','2021-04-12','2024-09-26','2023-07-19'),(205,'2021-03-01',1409,'TRUE','2023-04-10',1,12,'2021-12-04','2021-01-01','2023-08-01','2022-02-19'),(206,'2021-03-16',9094,'TRUE','2023-05-24',2,99,'2022-07-08','2021-04-06','2024-09-13','2022-12-28'),(207,'2021-04-20',3105,'TRUE','2021-09-09',3,28,'2021-08-29','2021-01-09','2022-03-20','2025-01-08'),(208,'2021-03-16',2912,'FALSE','2021-03-03',2,45,'2023-10-13','2021-02-28','2023-08-17','2022-02-04'),(209,'2021-02-19',3436,'FALSE','2023-02-17',3,16,'2022-05-22','2021-04-18','2024-06-17','2024-07-11'),(210,'2021-02-03',4342,'FALSE','2024-09-12',2,58,'2024-05-26','2021-04-17','2023-08-07','2025-02-12');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (211,'2021-04-24',4018,'TRUE','2022-03-30',3,111,'2022-02-12','2021-03-25','2021-12-20','2022-10-02'),(212,'2021-03-24',6642,'FALSE','2022-04-20',1,4,'2021-04-01','2021-03-05','2021-02-17','2023-02-05'),(213,'2021-01-25',4287,'FALSE','2024-01-21',2,135,'2022-11-22','2021-02-24','2022-01-02','2021-04-07'),(214,'2021-01-31',1946,'FALSE','2022-05-01',2,58,'2021-03-23','2021-01-29','2022-03-16','2022-06-22'),(215,'2021-02-08',2439,'TRUE','2024-07-11',3,87,'2023-10-05','2021-04-12','2021-01-09','2021-12-15'),(216,'2021-02-12',5530,'TRUE','2024-04-24',3,41,'2021-01-28','2021-01-12','2025-01-07','2025-01-12'),(217,'2021-04-01',8781,'FALSE','2023-04-02',1,26,'2021-09-03','2021-02-14','2022-09-01','2023-09-23'),(218,'2021-04-07',1358,'TRUE','2025-01-21',2,79,'2025-02-15','2021-02-14','2023-04-09','2023-02-23'),(219,'2021-03-17',3665,'TRUE','2022-05-23',2,75,'2022-02-14','2021-01-06','2024-02-18','2023-01-07'),(220,'2021-02-24',2635,'TRUE','2021-09-30',2,68,'2022-03-27','2021-01-09','2021-12-23','2024-08-20');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (221,'2021-02-15',8949,'TRUE','2022-12-07',3,59,'2022-05-19','2021-02-15','2022-02-08','2022-11-20'),(222,'2021-02-12',5113,'FALSE','2023-03-04',3,76,'2024-06-06','2021-01-13','2021-07-01','2022-03-31'),(223,'2021-03-08',6877,'FALSE','2021-08-15',2,95,'2023-10-26','2021-03-30','2022-04-19','2024-08-31'),(224,'2021-01-18',1895,'TRUE','2022-06-15',3,89,'2023-10-15','2021-04-09','2021-11-13','2024-02-17'),(225,'2021-02-11',4804,'FALSE','2023-08-10',1,32,'2024-01-01','2021-01-28','2023-08-04','2022-02-08'),(226,'2021-02-25',5491,'TRUE','2025-02-25',1,123,'2023-05-01','2021-03-20','2025-01-06','2022-12-08'),(227,'2021-04-08',355,'TRUE','2023-03-23',1,38,'2023-07-30','2021-04-27','2023-11-25','2024-05-18'),(228,'2021-01-13',6810,'TRUE','2023-11-11',2,112,'2022-02-25','2021-01-07','2023-10-18','2021-08-10'),(229,'2021-02-07',6849,'FALSE','2022-01-10',2,73,'2022-01-31','2021-01-05','2023-05-23','2022-12-07'),(230,'2021-03-02',9138,'FALSE','2023-08-18',2,90,'2023-03-12','2021-01-18','2021-10-06','2023-08-02');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (231,'2021-03-11',6214,'FALSE','2022-08-08',1,30,'2024-02-20','2021-01-03','2025-02-18','2023-06-07'),(232,'2021-02-04',404,'FALSE','2021-04-24',2,98,'2021-02-10','2021-03-09','2023-11-13','2021-04-14'),(233,'2021-01-10',2267,'FALSE','2024-11-25',1,89,'2024-05-24','2021-02-05','2023-11-20','2022-10-14'),(234,'2021-02-21',8829,'TRUE','2023-03-21',1,79,'2024-12-02','2021-03-19','2024-12-03','2021-08-07'),(235,'2021-01-12',641,'FALSE','2022-03-09',2,111,'2021-11-09','2021-03-06','2022-06-14','2024-04-17'),(236,'2021-01-01',5006,'FALSE','2024-11-16',1,77,'2021-12-01','2021-04-06','2021-01-21','2021-08-08'),(237,'2021-01-25',3973,'FALSE','2024-11-18',2,89,'2023-05-14','2021-03-06','2024-10-18','2024-09-20'),(238,'2021-01-16',8126,'TRUE','2024-08-17',1,108,'2024-09-18','2021-02-19','2024-07-08','2024-07-25'),(239,'2021-03-03',1917,'TRUE','2024-08-11',2,74,'2024-01-19','2021-03-01','2025-02-16','2022-04-04'),(240,'2021-04-21',2497,'TRUE','2022-09-23',3,109,'2024-12-05','2021-03-23','2023-11-17','2023-03-09');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (241,'2021-03-13',530,'TRUE','2022-08-30',3,135,'2024-09-10','2021-02-03','2021-05-11','2022-11-21'),(242,'2021-03-12',2763,'TRUE','2022-08-31',3,10,'2023-02-19','2021-04-11','2021-02-23','2025-02-15'),(243,'2021-04-28',664,'TRUE','2024-01-10',3,107,'2024-11-20','2021-01-02','2024-07-14','2024-08-22'),(244,'2021-04-11',9510,'FALSE','2022-07-03',2,103,'2021-09-17','2021-02-27','2023-12-28','2024-10-01'),(245,'2021-01-20',381,'TRUE','2021-08-16',2,61,'2023-06-29','2021-04-02','2021-04-15','2022-03-15'),(246,'2021-01-27',1912,'FALSE','2025-03-14',1,56,'2025-01-16','2021-02-26','2022-08-10','2023-08-16'),(247,'2021-01-22',7719,'FALSE','2024-11-19',3,102,'2022-11-02','2021-04-28','2023-03-04','2025-01-21'),(248,'2021-02-26',3762,'FALSE','2022-09-27',1,33,'2022-02-16','2021-04-07','2024-10-16','2024-11-23'),(249,'2021-02-03',8247,'FALSE','2023-07-17',2,48,'2021-04-19','2021-01-09','2023-11-03','2022-04-20'),(250,'2021-01-28',7806,'TRUE','2024-02-24',2,111,'2024-12-16','2021-03-05','2024-07-29','2022-06-16');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (251,'2021-04-12',1301,'FALSE','2021-11-25',3,19,'2024-02-04','2021-02-09','2022-02-08','2024-02-02'),(252,'2021-04-09',4367,'TRUE','2024-10-24',3,94,'2024-06-17','2021-03-09','2022-10-16','2023-04-03'),(253,'2021-01-28',4857,'FALSE','2025-02-07',2,139,'2023-12-28','2021-04-02','2023-12-31','2024-10-13'),(254,'2021-04-09',4161,'FALSE','2023-03-05',2,103,'2023-07-27','2021-04-03','2022-12-09','2021-10-29'),(255,'2021-04-19',3572,'TRUE','2022-06-06',1,62,'2021-02-21','2021-03-09','2021-05-09','2023-08-24'),(256,'2021-04-11',6085,'TRUE','2023-05-28',3,125,'2024-11-05','2021-01-14','2023-12-06','2024-01-23'),(257,'2021-03-05',3048,'TRUE','2025-02-27',3,116,'2022-01-07','2021-02-25','2024-06-15','2021-05-07'),(258,'2021-03-03',158,'TRUE','2022-12-01',3,131,'2021-10-16','2021-02-18','2021-08-24','2021-05-27'),(259,'2021-04-03',6812,'FALSE','2022-08-27',2,72,'2022-02-02','2021-01-03','2025-01-02','2024-07-28'),(260,'2021-04-12',2981,'TRUE','2021-05-22',2,115,'2023-11-13','2021-02-22','2024-02-18','2021-07-20');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (261,'2021-02-04',293,'FALSE','2022-10-15',3,67,'2023-10-27','2021-04-05','2021-01-14','2024-04-09'),(262,'2021-01-24',1078,'TRUE','2023-07-19',2,40,'2021-09-08','2021-02-10','2024-07-20','2023-09-20'),(263,'2021-03-18',3939,'FALSE','2023-03-05',3,25,'2021-07-22','2021-03-25','2022-01-20','2022-12-22'),(264,'2021-04-28',9367,'TRUE','2025-03-18',2,62,'2021-04-18','2021-04-22','2022-08-29','2022-08-08'),(265,'2021-04-11',1272,'TRUE','2024-06-27',2,6,'2022-02-12','2021-02-14','2025-01-12','2024-05-30'),(266,'2021-01-18',3032,'TRUE','2023-06-21',1,38,'2022-11-14','2021-03-31','2025-03-19','2024-03-17'),(267,'2021-02-22',7730,'FALSE','2022-12-01',2,122,'2023-12-07','2021-02-04','2024-06-17','2023-01-07'),(268,'2021-01-13',7325,'FALSE','2025-03-24',1,79,'2024-10-26','2021-03-25','2023-08-11','2024-10-25'),(269,'2021-04-12',9969,'TRUE','2021-08-03',1,13,'2025-03-02','2021-03-12','2023-11-22','2021-05-11'),(270,'2021-04-03',1446,'TRUE','2023-10-15',3,37,'2023-08-11','2021-04-02','2021-08-10','2021-06-11');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (271,'2021-03-12',1869,'FALSE','2024-06-20',1,68,'2024-04-01','2021-04-13','2023-07-08','2022-07-06'),(272,'2021-03-06',1976,'TRUE','2022-08-16',2,7,'2023-01-16','2021-04-10','2023-08-28','2025-01-23'),(273,'2021-04-15',4300,'TRUE','2021-04-01',2,109,'2022-10-08','2021-03-04','2022-09-28','2022-10-15'),(274,'2021-04-20',5369,'FALSE','2023-11-03',2,29,'2021-05-16','2021-03-15','2024-08-05','2022-09-24'),(275,'2021-02-24',7057,'FALSE','2023-12-28',1,30,'2023-02-13','2021-03-02','2022-07-07','2023-09-01'),(276,'2021-03-30',353,'TRUE','2025-02-25',1,20,'2023-01-19','2021-02-16','2023-08-12','2022-06-06'),(277,'2021-03-21',7453,'TRUE','2024-03-30',3,135,'2025-01-17','2021-01-13','2023-08-29','2024-11-24'),(278,'2021-01-19',8356,'TRUE','2022-03-12',2,37,'2021-05-09','2021-03-01','2021-08-21','2023-04-13'),(279,'2021-02-03',5439,'FALSE','2022-11-15',3,2,'2024-02-14','2021-03-05','2021-06-22','2021-10-02'),(280,'2021-02-28',141,'FALSE','2021-10-09',2,69,'2023-11-10','2021-02-02','2021-11-02','2021-02-16');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (281,'2021-04-08',3331,'FALSE','2021-09-13',3,137,'2023-07-09','2021-01-30','2021-07-03','2021-01-07'),(282,'2021-02-04',9165,'TRUE','2022-10-05',1,3,'2023-06-11','2021-02-11','2021-10-04','2021-02-15'),(283,'2021-01-13',1548,'FALSE','2024-04-13',1,96,'2024-05-02','2021-02-05','2023-01-29','2021-07-04'),(284,'2021-02-15',3566,'TRUE','2021-11-19',3,141,'2022-07-05','2021-04-24','2022-04-21','2024-09-27'),(285,'2021-04-11',4804,'FALSE','2022-05-10',3,62,'2021-12-28','2021-01-07','2024-07-17','2024-09-08'),(286,'2021-03-04',5721,'FALSE','2024-11-21',2,65,'2022-05-16','2021-02-09','2022-03-31','2021-11-28'),(287,'2021-03-29',3466,'TRUE','2024-08-29',3,65,'2024-10-07','2021-03-06','2022-07-06','2023-06-16'),(288,'2021-02-25',775,'FALSE','2024-04-19',2,123,'2024-12-18','2021-03-08','2022-07-30','2022-10-05'),(289,'2021-02-09',9214,'FALSE','2023-10-07',3,132,'2023-10-15','2021-04-07','2024-09-13','2021-01-30'),(290,'2021-01-20',4551,'FALSE','2021-10-25',3,87,'2023-10-13','2021-02-07','2023-12-03','2022-07-02');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (291,'2021-03-21',4937,'FALSE','2024-01-03',1,32,'2021-07-16','2021-02-19','2022-02-17','2024-06-22'),(292,'2021-02-18',7692,'FALSE','2024-01-26',3,35,'2021-12-27','2021-04-26','2021-03-02','2024-08-20'),(293,'2021-03-24',5365,'FALSE','2024-12-02',3,67,'2025-04-09','2021-02-13','2024-01-13','2022-02-12'),(294,'2021-02-14',9927,'TRUE','2023-08-26',2,11,'2022-03-05','2021-03-17','2021-03-06','2023-12-26'),(295,'2021-01-22',8306,'FALSE','2021-05-29',2,125,'2024-02-05','2021-02-03','2021-09-21','2022-03-24'),(296,'2021-01-31',4527,'FALSE','2024-05-05',3,88,'2022-12-25','2021-02-01','2022-10-27','2022-03-12'),(297,'2021-02-10',8698,'TRUE','2022-10-29',2,130,'2021-10-26','2021-04-07','2022-04-22','2022-01-14'),(298,'2021-02-02',5292,'TRUE','2025-04-09',3,22,'2023-01-17','2021-03-05','2024-05-21','2023-10-14'),(299,'2021-03-22',5762,'FALSE','2022-04-22',3,26,'2022-02-04','2021-02-28','2024-05-22','2023-01-16'),(300,'2021-04-07',6326,'FALSE','2021-10-24',2,133,'2024-08-21','2021-01-15','2023-08-27','2024-11-04');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (301,'2021-02-14',7441,'FALSE','2024-06-30',1,3,'2022-02-05','2021-03-31','2021-11-11','2023-08-01'),(302,'2021-04-18',3314,'TRUE','2024-02-09',3,31,'2024-12-28','2021-02-10','2023-06-08','2024-01-08'),(303,'2021-03-19',4769,'TRUE','2024-09-29',3,140,'2022-04-05','2021-01-04','2024-11-07','2022-04-04'),(304,'2021-03-30',7713,'TRUE','2022-08-16',2,96,'2021-09-17','2021-03-15','2024-12-27','2024-10-13'),(305,'2021-02-27',6749,'FALSE','2024-04-15',3,82,'2022-09-01','2021-02-20','2023-08-27','2022-04-27'),(306,'2021-03-06',739,'TRUE','2022-07-24',2,100,'2024-04-17','2021-04-02','2021-03-23','2021-12-20'),(307,'2021-02-14',888,'TRUE','2023-03-24',1,51,'2022-07-11','2021-03-05','2023-11-02','2023-03-17'),(308,'2021-02-08',4715,'TRUE','2022-04-29',2,129,'2025-01-17','2021-03-27','2021-05-19','2022-07-11'),(309,'2021-01-16',7472,'TRUE','2024-10-15',3,114,'2023-06-18','2021-03-16','2025-01-14','2023-07-05'),(310,'2021-02-07',6321,'TRUE','2023-04-18',1,74,'2021-03-07','2021-02-05','2022-12-29','2022-11-05');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (311,'2021-02-14',404,'TRUE','2022-07-03',3,136,'2022-05-09','2021-04-02','2024-06-09','2024-05-11'),(312,'2021-01-06',9738,'TRUE','2024-02-09',2,137,'2022-09-28','2021-02-11','2024-01-18','2022-10-23'),(313,'2021-03-28',6477,'TRUE','2023-07-30',2,79,'2024-10-10','2021-03-01','2021-05-05','2024-10-06'),(314,'2021-01-04',9846,'FALSE','2024-01-29',2,110,'2024-03-02','2021-02-19','2024-05-15','2021-07-31'),(315,'2021-03-02',993,'TRUE','2021-01-07',1,74,'2024-03-15','2021-04-16','2024-01-21','2024-09-26'),(316,'2021-03-25',640,'FALSE','2023-06-24',1,120,'2021-12-19','2021-03-22','2021-12-08','2025-01-07'),(317,'2021-04-19',879,'FALSE','2022-03-30',2,92,'2023-11-06','2021-04-25','2021-04-05','2023-12-01'),(318,'2021-02-13',5707,'FALSE','2024-06-08',3,137,'2024-11-28','2021-03-15','2023-11-09','2024-01-17'),(319,'2021-04-21',9013,'FALSE','2022-01-29',1,90,'2024-08-17','2021-01-04','2023-06-17','2022-10-24'),(320,'2021-04-13',4753,'TRUE','2022-01-29',1,8,'2023-10-31','2021-04-14','2025-02-19','2022-09-11');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (321,'2021-03-10',3681,'FALSE','2021-02-07',3,22,'2025-01-15','2021-01-30','2023-04-05','2023-03-10'),(322,'2021-01-23',1874,'FALSE','2024-02-01',3,2,'2024-06-04','2021-03-20','2021-07-13','2024-10-02'),(323,'2021-01-05',4261,'TRUE','2024-08-16',3,71,'2023-05-12','2021-03-18','2022-05-19','2022-04-03'),(324,'2021-02-24',9323,'TRUE','2024-11-23',1,115,'2024-11-04','2021-02-13','2023-07-26','2021-08-11'),(325,'2021-01-05',1782,'FALSE','2021-09-28',2,86,'2023-07-20','2021-03-04','2021-04-02','2025-01-29'),(326,'2021-01-24',9224,'FALSE','2022-09-10',2,19,'2023-10-07','2021-03-30','2021-06-26','2023-11-21'),(327,'2021-01-18',3704,'FALSE','2021-10-23',1,75,'2021-09-08','2021-03-15','2021-03-02','2024-01-13'),(328,'2021-04-25',2276,'TRUE','2023-12-21',1,6,'2021-11-28','2021-03-20','2023-11-21','2023-01-03'),(329,'2021-03-02',6472,'TRUE','2022-06-14',3,133,'2022-06-10','2021-03-20','2022-01-15','2022-09-24'),(330,'2021-03-17',5923,'FALSE','2024-03-27',3,121,'2024-01-26','2021-01-16','2024-09-08','2022-10-16');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (331,'2021-02-06',780,'TRUE','2022-12-25',3,29,'2024-06-29','2021-02-04','2025-02-24','2022-04-11'),(332,'2021-04-01',4900,'TRUE','2021-10-31',3,38,'2021-11-04','2021-02-06','2025-03-30','2024-12-13'),(333,'2021-04-09',9024,'TRUE','2023-12-15',3,106,'2023-03-07','2021-02-28','2022-02-26','2021-09-06'),(334,'2021-03-03',5133,'TRUE','2023-09-25',3,56,'2022-02-23','2021-03-19','2021-05-08','2021-01-10'),(335,'2021-04-16',1470,'TRUE','2024-06-30',1,32,'2021-12-21','2021-04-24','2024-09-28','2021-01-10'),(336,'2021-03-17',5258,'FALSE','2024-04-16',3,57,'2024-07-24','2021-03-09','2021-01-07','2021-10-07'),(337,'2021-04-05',3820,'TRUE','2021-09-26',2,31,'2024-12-10','2021-03-03','2025-02-07','2023-12-20'),(338,'2021-03-31',4300,'TRUE','2024-06-02',2,106,'2021-09-21','2021-01-19','2023-06-06','2021-01-04'),(339,'2021-03-14',1682,'FALSE','2024-10-03',2,37,'2023-08-17','2021-04-01','2025-03-20','2021-01-31'),(340,'2021-03-26',8302,'FALSE','2025-03-21',2,118,'2023-08-17','2021-02-16','2024-09-24','2024-05-23');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (341,'2021-04-27',8455,'TRUE','2023-06-01',3,124,'2021-09-03','2021-02-19','2022-07-25','2023-04-23'),(342,'2021-01-15',9325,'FALSE','2024-09-18',3,109,'2022-11-27','2021-04-03','2021-06-21','2024-01-30'),(343,'2021-02-04',7942,'TRUE','2022-12-30',3,130,'2023-11-23','2021-01-03','2021-08-21','2024-12-26'),(344,'2021-01-13',2126,'FALSE','2022-12-14',2,51,'2022-10-25','2021-03-20','2021-01-04','2025-03-25'),(345,'2021-02-03',4601,'TRUE','2024-07-01',3,120,'2023-07-31','2021-04-11','2021-06-09','2022-06-04'),(346,'2021-04-09',7852,'TRUE','2024-03-21',1,94,'2022-04-05','2021-02-21','2023-01-02','2022-09-23'),(347,'2021-01-21',6318,'FALSE','2024-02-28',3,131,'2022-03-08','2021-01-11','2023-08-01','2024-03-15'),(348,'2021-01-25',9093,'TRUE','2021-11-27',2,11,'2023-10-11','2021-03-10','2022-05-27','2022-10-10'),(349,'2021-01-21',5203,'FALSE','2024-11-15',3,83,'2023-11-11','2021-01-15','2024-01-12','2023-06-17'),(350,'2021-04-03',4837,'FALSE','2024-03-15',3,122,'2022-11-29','2021-02-08','2024-02-11','2021-08-26');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (351,'2021-02-26',2527,'TRUE','2023-02-24',3,52,'2023-06-19','2021-04-08','2025-01-03','2023-08-23'),(352,'2021-01-18',2248,'TRUE','2023-06-02',3,57,'2024-12-21','2021-04-14','2021-12-30','2023-12-10'),(353,'2021-02-17',7990,'TRUE','2024-12-25',3,30,'2021-11-12','2021-02-16','2024-05-23','2022-10-12'),(354,'2021-04-09',79,'FALSE','2024-07-12',1,119,'2022-08-30','2021-01-30','2024-05-03','2022-10-28'),(355,'2021-02-17',6022,'FALSE','2023-12-28',1,67,'2025-04-08','2021-02-22','2023-05-07','2024-10-31'),(356,'2021-02-20',5185,'TRUE','2022-04-05',1,128,'2024-10-26','2021-01-15','2024-09-04','2024-04-20'),(357,'2021-04-07',5076,'FALSE','2021-12-02',1,129,'2021-12-13','2021-03-04','2021-03-13','2025-01-05'),(358,'2021-03-13',9622,'FALSE','2021-12-31',3,89,'2023-02-21','2021-01-17','2022-08-04','2022-09-19'),(359,'2021-02-20',657,'TRUE','2023-05-25',1,131,'2022-09-07','2021-03-14','2024-12-25','2024-02-29'),(360,'2021-03-21',7106,'FALSE','2024-01-23',1,116,'2024-03-24','2021-03-21','2024-03-01','2021-11-02');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (361,'2021-01-11',1913,'FALSE','2024-09-16',2,27,'2024-11-22','2021-01-30','2022-08-26','2022-04-24'),(362,'2021-02-25',4628,'FALSE','2022-05-09',2,111,'2021-09-17','2021-04-25','2021-05-25','2024-09-28'),(363,'2021-03-23',4503,'TRUE','2024-12-30',3,104,'2022-11-27','2021-01-26','2023-09-09','2023-11-10'),(364,'2021-02-16',1151,'FALSE','2023-11-26',3,141,'2024-06-15','2021-02-09','2023-03-26','2021-12-09'),(365,'2021-01-03',8313,'FALSE','2025-03-12',2,39,'2023-02-16','2021-04-17','2024-09-03','2022-10-17'),(366,'2021-02-17',4507,'FALSE','2021-12-30',1,117,'2021-06-03','2021-02-25','2023-09-13','2021-07-06'),(367,'2021-03-17',2450,'FALSE','2025-01-02',2,57,'2022-01-28','2021-01-19','2024-05-28','2022-05-12'),(368,'2021-04-01',4412,'FALSE','2022-06-04',3,84,'2022-10-10','2021-01-23','2021-05-28','2023-06-06'),(369,'2021-01-01',8018,'TRUE','2021-03-28',1,64,'2021-12-06','2021-02-06','2021-05-22','2021-03-09'),(370,'2021-02-09',3269,'TRUE','2023-03-12',1,91,'2021-01-20','2021-01-02','2024-05-27','2023-09-18');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (371,'2021-02-26',7473,'FALSE','2022-03-17',3,75,'2023-01-23','2021-02-11','2022-01-31','2021-11-08'),(372,'2021-02-02',2041,'FALSE','2023-08-31',1,115,'2022-11-05','2021-01-18','2021-11-02','2025-03-05'),(373,'2021-01-26',6854,'TRUE','2021-06-24',1,24,'2022-02-23','2021-03-24','2022-07-20','2021-06-27'),(374,'2021-01-19',5631,'FALSE','2021-11-14',1,47,'2025-01-08','2021-02-03','2021-03-05','2021-06-26'),(375,'2021-04-08',553,'FALSE','2022-06-28',1,13,'2021-08-16','2021-03-31','2021-08-26','2025-02-03'),(376,'2021-01-26',8872,'FALSE','2024-11-07',2,121,'2024-10-30','2021-02-16','2021-08-17','2021-05-23'),(377,'2021-03-10',8247,'FALSE','2024-02-07',2,86,'2023-10-05','2021-02-28','2021-04-01','2023-02-04'),(378,'2021-02-16',3305,'FALSE','2021-02-03',2,23,'2024-10-25','2021-04-09','2022-02-14','2023-05-14'),(379,'2021-01-13',9891,'FALSE','2021-12-19',2,71,'2023-01-30','2021-02-24','2021-07-15','2021-12-04'),(380,'2021-01-17',4451,'FALSE','2024-11-17',2,10,'2025-03-19','2021-04-19','2024-08-20','2024-09-12');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (381,'2021-04-06',3270,'FALSE','2025-03-19',1,9,'2022-02-21','2021-02-01','2025-03-13','2024-08-06'),(382,'2021-02-07',6029,'FALSE','2022-05-31',3,25,'2022-10-25','2021-01-23','2025-03-05','2024-05-26'),(383,'2021-01-14',6478,'TRUE','2021-01-21',3,63,'2024-02-21','2021-04-19','2021-06-03','2023-08-04'),(384,'2021-02-23',9561,'FALSE','2024-08-30',2,88,'2023-06-13','2021-03-17','2024-04-05','2022-05-03'),(385,'2021-04-01',196,'FALSE','2021-12-13',2,38,'2023-11-13','2021-01-28','2022-02-23','2022-01-30'),(386,'2021-04-12',8291,'FALSE','2021-05-08',1,109,'2023-06-11','2021-01-10','2024-02-20','2021-10-31'),(387,'2021-03-22',5705,'FALSE','2021-12-20',3,48,'2023-08-04','2021-03-20','2025-02-21','2021-05-22'),(388,'2021-01-08',3969,'FALSE','2022-08-23',3,66,'2024-11-23','2021-03-27','2023-11-11','2024-09-30'),(389,'2021-01-21',3000,'TRUE','2022-09-15',2,32,'2024-06-18','2021-02-09','2022-09-10','2021-07-20'),(390,'2021-01-15',4809,'TRUE','2021-08-07',3,7,'2021-07-11','2021-03-13','2023-02-20','2021-07-19');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (391,'2021-03-01',2679,'TRUE','2021-08-25',2,103,'2022-03-29','2021-01-02','2024-12-18','2021-09-14'),(392,'2021-01-18',2166,'TRUE','2024-05-09',2,127,'2024-10-05','2021-01-10','2024-06-02','2022-06-11'),(393,'2021-03-26',2776,'TRUE','2024-05-29',1,44,'2022-05-26','2021-01-21','2023-10-07','2023-03-25'),(394,'2021-04-17',8705,'FALSE','2022-11-26',1,19,'2023-12-21','2021-02-09','2023-03-04','2023-04-29'),(395,'2021-04-10',7361,'TRUE','2023-09-12',3,86,'2024-06-11','2021-04-17','2021-08-04','2025-02-15'),(396,'2021-01-09',1605,'FALSE','2022-09-27',2,8,'2024-10-19','2021-01-01','2024-09-30','2021-09-09'),(397,'2021-02-26',4385,'TRUE','2022-04-04',1,46,'2024-11-29','2021-04-06','2024-11-17','2022-01-05'),(398,'2021-02-15',8977,'FALSE','2022-05-19',2,71,'2022-07-04','2021-04-12','2021-09-10','2024-03-15'),(399,'2021-03-17',17,'TRUE','2022-10-22',2,15,'2022-02-26','2021-01-19','2024-08-10','2024-04-08'),(400,'2021-04-26',8345,'TRUE','2021-07-18',1,3,'2023-05-09','2021-04-26','2022-01-17','2025-03-17');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (401,'2021-01-26',9883,'TRUE','2024-03-19',2,40,'2024-03-30','2021-01-14','2024-03-28','2023-05-20'),(402,'2021-01-24',387,'FALSE','2021-12-05',2,75,'2023-07-18','2021-01-27','2024-09-29','2024-01-30'),(403,'2021-04-27',977,'FALSE','2022-11-20',2,6,'2024-11-27','2021-02-01','2021-03-02','2025-03-23'),(404,'2021-02-05',2848,'TRUE','2022-07-07',1,110,'2022-09-20','2021-03-17','2022-02-13','2021-09-01'),(405,'2021-01-25',8658,'TRUE','2022-08-22',2,120,'2023-09-14','2021-04-05','2023-03-18','2022-10-02'),(406,'2021-04-21',9103,'FALSE','2022-01-05',3,34,'2022-01-28','2021-01-25','2023-12-30','2023-04-20'),(407,'2021-03-09',1229,'FALSE','2022-03-31',2,12,'2021-01-14','2021-02-03','2021-11-23','2021-02-15'),(408,'2021-04-17',5449,'TRUE','2022-03-09',1,98,'2025-03-04','2021-03-30','2021-04-02','2023-10-19'),(409,'2021-02-17',7504,'FALSE','2021-03-13',2,34,'2022-12-10','2021-03-24','2025-01-28','2021-04-01'),(410,'2021-01-11',7025,'TRUE','2021-03-14',2,41,'2025-04-08','2021-03-20','2021-03-02','2022-12-18');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (411,'2021-02-12',8004,'TRUE','2024-07-16',2,36,'2021-12-17','2021-02-16','2023-04-19','2025-01-09'),(412,'2021-02-25',4978,'FALSE','2023-02-27',3,48,'2024-08-31','2021-04-25','2023-10-04','2021-05-04'),(413,'2021-03-17',6919,'TRUE','2023-06-01',1,77,'2021-03-24','2021-02-11','2024-03-17','2021-05-14'),(414,'2021-03-09',2827,'TRUE','2021-06-27',3,124,'2023-11-08','2021-04-26','2024-08-24','2023-05-18'),(415,'2021-03-27',882,'TRUE','2023-06-27',2,19,'2022-10-06','2021-01-20','2022-09-19','2022-01-19'),(416,'2021-02-13',2635,'TRUE','2024-04-19',3,133,'2024-02-01','2021-01-12','2022-04-04','2024-06-23'),(417,'2021-01-16',5033,'FALSE','2022-07-06',1,117,'2022-09-17','2021-04-28','2025-01-27','2022-05-24'),(418,'2021-04-10',204,'TRUE','2023-01-31',2,103,'2021-07-01','2021-02-20','2022-05-18','2023-04-03'),(419,'2021-04-18',8803,'FALSE','2022-04-10',3,104,'2023-06-02','2021-04-04','2023-04-17','2022-02-12'),(420,'2021-03-31',5024,'TRUE','2022-11-24',2,31,'2023-08-26','2021-02-11','2024-10-04','2023-08-20');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (421,'2021-03-24',2398,'FALSE','2022-01-31',1,55,'2024-06-05','2021-01-22','2022-01-23','2024-09-03'),(422,'2021-01-03',6995,'FALSE','2022-09-05',3,95,'2022-07-12','2021-01-04','2024-05-01','2022-07-08'),(423,'2021-01-30',2630,'TRUE','2022-09-04',3,65,'2022-06-18','2021-04-26','2021-08-28','2022-10-11'),(424,'2021-04-24',9335,'TRUE','2023-02-16',1,46,'2024-12-31','2021-04-26','2025-03-25','2024-07-10'),(425,'2021-03-15',64,'FALSE','2023-10-07',3,64,'2023-12-17','2021-04-18','2023-08-15','2023-02-24'),(426,'2021-01-18',6148,'FALSE','2021-08-29',3,128,'2024-03-02','2021-04-20','2024-03-19','2022-04-21'),(427,'2021-01-16',7791,'FALSE','2023-09-29',3,99,'2021-12-01','2021-04-15','2024-11-02','2024-01-21'),(428,'2021-04-18',6334,'TRUE','2023-11-06',1,113,'2023-12-30','2021-04-08','2024-06-10','2021-02-06'),(429,'2021-01-31',1107,'TRUE','2022-04-03',2,17,'2025-01-28','2021-02-15','2024-07-08','2021-06-18'),(430,'2021-03-05',8803,'TRUE','2022-01-06',2,109,'2023-02-09','2021-03-07','2025-04-07','2022-04-27');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (431,'2021-03-09',9221,'FALSE','2023-04-14',1,36,'2021-09-03','2021-03-14','2021-09-02','2024-07-10'),(432,'2021-03-07',2784,'TRUE','2021-07-20',1,109,'2022-07-28','2021-02-11','2022-05-21','2023-08-24'),(433,'2021-02-14',7283,'FALSE','2024-08-10',3,115,'2022-11-21','2021-01-02','2023-11-03','2023-05-12'),(434,'2021-04-17',7616,'TRUE','2023-10-07',2,49,'2022-02-04','2021-03-17','2021-02-27','2021-06-19'),(435,'2021-02-25',6948,'TRUE','2023-02-26',1,120,'2022-01-19','2021-04-23','2022-03-31','2021-10-10'),(436,'2021-03-15',7424,'FALSE','2021-02-08',1,47,'2021-03-29','2021-01-04','2022-10-17','2024-04-06'),(437,'2021-02-12',4526,'FALSE','2022-03-10',2,24,'2022-04-04','2021-03-02','2022-05-07','2022-06-15'),(438,'2021-01-10',3365,'FALSE','2021-01-22',2,82,'2023-05-19','2021-02-07','2022-04-23','2021-06-23'),(439,'2021-03-31',3336,'TRUE','2021-04-16',1,90,'2022-03-05','2021-01-26','2021-09-17','2025-03-13'),(440,'2021-01-05',9401,'FALSE','2024-01-18',3,85,'2024-05-03','2021-03-24','2024-03-19','2024-08-27');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (441,'2021-03-16',7199,'FALSE','2022-08-09',1,15,'2025-01-04','2021-01-24','2022-01-27','2022-11-26'),(442,'2021-03-26',2116,'FALSE','2024-09-11',3,28,'2021-10-19','2021-04-05','2024-09-19','2024-07-22'),(443,'2021-03-18',4687,'FALSE','2024-05-06',3,22,'2025-04-02','2021-04-14','2022-10-26','2023-08-27'),(444,'2021-03-08',9952,'TRUE','2021-06-08',2,55,'2021-01-28','2021-03-20','2022-10-20','2022-12-12'),(445,'2021-03-04',3855,'FALSE','2021-05-09',1,70,'2025-03-22','2021-01-30','2022-08-21','2023-09-11'),(446,'2021-01-26',3452,'FALSE','2022-05-01',2,33,'2021-11-22','2021-01-15','2023-06-24','2022-02-18'),(447,'2021-01-03',3526,'TRUE','2021-10-28',2,81,'2022-11-17','2021-03-31','2024-05-27','2021-02-12'),(448,'2021-01-29',6729,'FALSE','2023-03-25',2,82,'2022-01-23','2021-02-28','2022-05-12','2021-02-22'),(449,'2021-01-20',1122,'FALSE','2024-12-10',1,84,'2024-01-23','2021-02-01','2022-12-06','2022-08-24'),(450,'2021-02-03',5816,'FALSE','2023-02-26',1,2,'2023-06-18','2021-03-20','2022-08-07','2025-03-21');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (451,'2021-04-09',8351,'FALSE','2021-06-29',3,62,'2023-05-15','2021-04-13','2024-08-28','2024-01-01'),(452,'2021-02-24',7575,'FALSE','2023-07-10',3,52,'2023-09-26','2021-01-20','2022-04-09','2021-01-19'),(453,'2021-03-13',887,'TRUE','2025-02-16',2,28,'2023-01-22','2021-03-21','2024-01-15','2023-01-14'),(454,'2021-04-16',9139,'TRUE','2021-11-09',2,57,'2022-05-09','2021-01-02','2023-07-23','2021-02-10'),(455,'2021-04-28',7066,'TRUE','2025-04-09',2,4,'2022-11-11','2021-01-12','2022-03-11','2023-04-03'),(456,'2021-02-04',9459,'TRUE','2024-04-29',2,62,'2023-07-09','2021-03-10','2024-03-05','2021-03-01'),(457,'2021-02-26',6936,'TRUE','2023-02-25',1,35,'2022-09-16','2021-04-12','2023-06-28','2023-03-29'),(458,'2021-01-10',444,'FALSE','2023-09-01',2,90,'2023-12-07','2021-03-09','2021-02-11','2025-03-14'),(459,'2021-01-14',9988,'FALSE','2024-07-29',1,22,'2021-05-01','2021-01-09','2022-12-07','2023-02-11'),(460,'2021-01-28',601,'FALSE','2021-09-10',2,129,'2022-03-04','2021-04-17','2022-02-14','2022-10-27');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (461,'2021-02-25',9690,'TRUE','2021-09-28',2,109,'2021-03-27','2021-03-07','2024-01-19','2025-03-04'),(462,'2021-01-28',6732,'TRUE','2023-11-02',2,118,'2024-11-21','2021-04-12','2024-02-29','2022-02-15'),(463,'2021-04-14',1842,'FALSE','2021-06-13',3,124,'2021-10-14','2021-04-09','2023-03-10','2023-11-14'),(464,'2021-04-20',3516,'TRUE','2022-04-21',1,42,'2023-07-02','2021-01-10','2024-08-15','2021-10-28'),(465,'2021-01-06',8761,'TRUE','2023-10-27',2,5,'2022-06-03','2021-01-24','2022-06-04','2022-02-16'),(466,'2021-02-11',4752,'TRUE','2024-02-14',1,74,'2022-05-12','2021-04-09','2022-03-03','2023-04-26'),(467,'2021-01-21',176,'FALSE','2021-01-06',2,15,'2024-11-08','2021-03-08','2021-02-05','2023-10-13'),(468,'2021-03-02',9713,'TRUE','2025-01-18',1,108,'2022-06-27','2021-01-17','2023-08-23','2022-10-26'),(469,'2021-04-13',6501,'FALSE','2025-02-19',3,49,'2023-04-10','2021-04-16','2021-12-22','2021-09-16'),(470,'2021-01-28',1023,'FALSE','2023-10-14',1,115,'2022-10-21','2021-03-14','2024-10-11','2025-02-28');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (471,'2021-03-17',1075,'TRUE','2024-08-27',3,52,'2024-02-28','2021-01-25','2024-01-30','2021-05-12'),(472,'2021-02-17',5533,'TRUE','2021-05-29',1,96,'2024-03-21','2021-02-13','2022-07-02','2024-10-10'),(473,'2021-01-26',5785,'FALSE','2021-01-13',3,94,'2025-03-15','2021-02-18','2023-10-14','2023-04-27'),(474,'2021-02-13',8893,'FALSE','2022-05-27',2,43,'2021-11-24','2021-03-07','2021-01-06','2024-11-30'),(475,'2021-02-11',1702,'TRUE','2021-06-30',3,24,'2023-04-02','2021-02-19','2023-12-09','2025-02-13'),(476,'2021-01-17',4845,'TRUE','2023-04-14',3,113,'2021-06-01','2021-01-21','2024-02-08','2021-03-31'),(477,'2021-03-10',8652,'TRUE','2024-04-01',2,100,'2022-09-30','2021-02-15','2022-09-10','2022-05-04'),(478,'2021-02-01',7392,'TRUE','2021-03-18',2,2,'2021-04-13','2021-04-16','2024-10-03','2024-09-12'),(479,'2021-03-27',7822,'FALSE','2022-11-15',3,26,'2022-03-08','2021-04-27','2021-12-16','2023-10-11'),(480,'2021-03-30',4951,'FALSE','2024-07-08',3,77,'2021-01-16','2021-03-04','2022-08-18','2023-02-15');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (481,'2021-03-28',2892,'FALSE','2021-08-05',3,30,'2024-08-21','2021-01-16','2022-03-19','2025-01-22'),(482,'2021-04-24',4172,'FALSE','2021-01-25',3,91,'2022-12-02','2021-01-22','2024-09-22','2023-10-21'),(483,'2021-02-06',6140,'TRUE','2025-03-28',1,110,'2022-01-21','2021-04-14','2021-11-01','2023-04-19'),(484,'2021-03-13',1058,'TRUE','2022-03-28',1,11,'2025-01-25','2021-03-11','2024-11-15','2021-06-29'),(485,'2021-03-01',8023,'TRUE','2022-02-01',3,40,'2023-02-03','2021-01-23','2022-08-17','2021-10-01'),(486,'2021-02-03',9200,'TRUE','2023-06-29',3,12,'2022-07-14','2021-01-16','2022-07-18','2022-10-10'),(487,'2021-04-16',6713,'TRUE','2022-10-23',3,118,'2023-08-03','2021-01-27','2023-01-26','2021-10-08'),(488,'2021-01-24',7492,'TRUE','2021-05-05',1,32,'2022-04-22','2021-02-27','2025-02-21','2024-09-18'),(489,'2021-02-18',9513,'FALSE','2021-03-03',2,74,'2021-06-08','2021-04-15','2021-02-08','2021-07-18'),(490,'2021-02-05',4863,'FALSE','2021-04-07',1,139,'2025-01-18','2021-04-18','2021-05-29','2024-05-20');
INSERT INTO "FUNDING_CYCLE" (ID,END_DATE,EXPECTED_APPLICATIONS,IS_OPEN,START_DATE,FISCAL_YEAR_ID,FUNDING_OPPORTUNITY_ID,END_DATELOI,END_DATENOI,START_DATELOI,START_DATENOI) VALUES (491,'2021-04-06',2894,'TRUE','2022-07-13',3,20,'2022-07-15','2021-04-01','2021-03-01','2023-05-15'),(492,'2021-04-16',6124,'FALSE','2021-05-22',2,45,'2025-03-29','2021-01-11','2022-05-31','2022-03-31'),(493,'2021-04-10',6551,'TRUE','2022-08-27',3,80,'2024-04-19','2021-03-01','2021-06-03','2021-06-18'),(494,'2021-03-02',3030,'FALSE','2023-02-22',1,23,'2021-11-30','2021-04-08','2021-10-31','2023-04-13'),(495,'2021-01-03',4452,'FALSE','2023-10-03',1,109,'2024-06-27','2021-04-26','2022-07-28','2022-07-24'),(496,'2021-02-10',1444,'FALSE','2024-10-22',1,60,'2023-07-20','2021-04-22','2024-06-26','2024-10-27'),(497,'2021-02-10',6770,'FALSE','2023-03-04',2,100,'2024-06-08','2021-03-13','2021-04-19','2022-12-06'),(498,'2021-01-10',4420,'TRUE','2025-02-24',3,78,'2024-10-10','2021-03-03','2023-03-01','2021-09-22'),(499,'2021-02-27',7303,'FALSE','2023-07-18',3,79,'2022-02-24','2021-01-30','2021-03-15','2023-12-02'),(500,'2021-02-03',4485,'TRUE','2022-10-28',1,118,'2022-12-02','2021-02-27','2024-12-11','2021-10-28');