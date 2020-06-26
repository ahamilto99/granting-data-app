INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (1,'MCT','FR MCT','MCT','MCT',3);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (2,'EER','FR EER','EER','EER',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (3,'ICSP','FR ICSP','ICSP','ICSP',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (4,'SF','FR SF','SF','SF',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (5,'MEPS','FR MEPS','MEPS','MEPS',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (6,'CCPP','FR CCPP','CCPP','CCPP',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (7,'NCE','FR NCE','NCE','NCE',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (8,'RP','FR RP','RP','RP',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (9,'ELS','FR ELS','ELS','ELS',2);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (10,'RGPD','FR RGPD','RGPD','RGPD',1);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (11,'TIPS','FR TIPS','TIPS','TIPS',1);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (12,'FCD','FR FCD','FCD','FCD',1);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (13,'RTP','FR RTP','RTP','RTP',1);
INSERT INTO BUSINESS_UNIT (ID,NAME_EN,NAME_FR,ACRONYM_EN,ACRONYM_FR,AGENCY_ID) VALUES (14,'Comms','FR Comms','COMMS','COMMS',1);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (1, (select id from business_unit where acronym_en like 'MCT'), '1/YR', 'Partnerships Programs', 0, 0, 0, 'Collaborative Health Research Projects (CHRP) (5640)', NULL, NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (2, NULL, NULL, NULL, 0, 0, 0, 'IPPH Urban Housing and Health Trainee Research Awards', NULL, NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (3, select id from business_unit where acronym_en like 'EER', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Chairs in Design Engineering (research and salary)', 'Chaires en génie de la conception', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (4, select id from business_unit where acronym_en like 'ICSP', 'Open', 'Research Grants', 0, 0, 0, 'Unique Initiatives Fund (5860)', 'Fonds d''initiatives uniques', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (5, select id from business_unit where acronym_en like 'SF', 'Open', 'Research Grants', 0, 0, 0, 'Chairs for Women in Science and Engineering (CWSE)', 'Programme de chaires pour les femmes en sciences et en génie', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (6, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'CMHC Postdoctoral Fellowships', 'Programme de bourses de recherche sur le logement de la SCHL.', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (7, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Foreign Study Supplements in Taiwan (FSS-Taiwan) (SIT)  (5050)', 'Suppléments pour études à Taïwan', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (8, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'JSPS - Postdoctoral Fellowships Program (JSPSF) (4933)', 'Bourses postdoctorales de la Société japonaise pour la promotion de la science', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (9, select id from business_unit where acronym_en like 'ICSP', 'Open', 'Scholarships', 0, 0, 0, 'Aboriginal Ambassadors in the Natural Sciences and Engineering (AANSE) (6610)', 'Bourse pour ambassadeurs autochtones des sciences naturelles et du génie', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (10, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Prize', 0, 0, 0, 'Brockhouse Canada Prize (5225)', 'Prix Brockhouse du Canada', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (11, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Canadian Institute for Theoretical Astrophysics Support Program (CITA) (5632)', 'Programme d''appui à l''Institut canadien d''astrophysique théorique', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (12, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Fellowships', 0, 0, 0, 'EWR Steacie Fellowships (Research and Salary) (SMFSA-) (5265)', 'Bourses commémoratives E.W.R. Steacie', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (13, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'GENDER-NET Plus', 'ERA-NET Cofund', NULL, 0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (14, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Prize', 0, 0, 0, 'Herzberg Canada Gold Medal for Science and Engineering (5300)', 'Médaille d''or Gerhard-Herzberg en sciences et en génie du Canada', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (15, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Prize', 0, 0, 0, 'John C. Polanyi Award  (5735)', 'Prix John-C.-Polanyi du CRSNG', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (16, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Prize', 0, 0, 0, 'NSERC Award for Science Promotion (NASPI)', 'Prix du CRSNG - Promotion des sciences', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (17, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Prize', 0, 0, 0, 'NSERC Award for Science Promotion (NASPO) (6780)', 'Prix du CRSNG - Promotion des sciences', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (18, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Scholarships', 0, 0, 0, 'PromoScience (5390)', 'Promotion des sciences', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (19, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Supplement', 0, 0, 0, 'PromoScience Supplements (PSC)', NULL, NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES (20, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Supplement', 0, 0, 0, 'Science Literacy Week (SLW) (PSSLW)', 'Semaine de la culture scientifique', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES
(21, select id from business_unit where acronym_en like 'ICSP', '1/YR', 'Supplement', 0, 0, 0, 'Science Odyssey Supplements (SOS) (PSSOS)', 'Odyssée des sciences', NULL,  0, 0),
(22, select id from business_unit where acronym_en like 'ICSP', NULL, 'Scholarships', 0, 0, 0, 'Student Amassadors (NSA) (MFNSA)  (5370)', 'Étudiants ambassadeurs du CRSNG', NULL,  0, 0),
(23, select id from business_unit where acronym_en like 'ICSP', 'Open', 'Scholarships', 0, 0, 0, 'Young Innovators (MFNYI)', NULL, NULL,  0, 0),
(24, select id from business_unit where acronym_en like 'EER', 'Open', 'Partnerships Programs', 0, 0, 0, 'Collaborative Research and Development Grants (CRD) (6415)', NULL, NULL,  0, 0),
(25, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Community & College Social Innovation Fund (CCSIF) (6017)', NULL, NULL,  0, 0),
(26, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Engage for Colleges (CARD1) (6006)', NULL, NULL, 0, 0),
(27, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Engage for Universities (EGP) (6430)', NULL, NULL, 0, 0),
(28, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Engage Plus Grants for Colleges (CEGP2) (6015)', NULL, NULL, 0, 0),
(29, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Engage Plus Grants for Universities (EGP2) (6432)', NULL, NULL, 0, 0),
(30, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Experience Awards (previously IUSRA)(USRAI) (6030)', NULL, NULL,  0, 0),
(31, select id from business_unit where acronym_en like 'SF', '1+/YR', 'Scholarships', 0, 0, 0, 'Undergraduate Student Research Awards (USRA) (URU) (6862)', NULL, NULL,  0, 0),
(32, select id from business_unit where acronym_en like 'CCPP', '1+/YR', NULL, 0, 0, 0, 'Idea to Innovation (I2I) - (I2IPJ) (6036)', NULL, NULL,  0, 0),
(33, select id from business_unit where acronym_en like 'EER', 'Open', 'Partnerships Programs', 0, 0, 0, 'Industrial Research Chairs (IRC) (6235)', NULL, NULL,  0, 0),
(34, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Postdoctoral Fellowships (PDF) (6790)', NULL, NULL, 0, 0),
(35, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Postgraduate Scholarships - Doctoral  PGS-D2 (Direct and University) (6798)', NULL, NULL, 0, 0),
(36, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Postgraduate Scholarships - Doctoral  PGS-D3 (Direct and University) (6799)', NULL, NULL, 0, 0),
(37, select id from business_unit where acronym_en like 'EER', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Strategic Parnerships - Networks (SPG-N)', NULL, NULL,  0, 0),
(38, select id from business_unit where acronym_en like 'EER', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Strategic Partnerships - Projects (SPG-P)', NULL, NULL,  0, 0),
(39, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Applied Research Tools and Instruments Grants (ARTI) (CARTI) (6004)', NULL, NULL,  0, 0),
(40, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Belmont Forum (BFAOR) (5807)', NULL, NULL,  0, 0),
(41, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Belmont Forum (BFBIO) (5808)', NULL, NULL,  0, 0),
(42, select id from business_unit where acronym_en like 'ICSP', '1/Year', 'Scholarships', 0, 0, 0, 'CREATE (6648)', NULL, NULL, 0, 0),
(43, select id from business_unit where acronym_en like 'ELS', 'Open', NULL, 0, 0, 0, 'DND/NSERC Research Partnership (DNDPJ) (6467)', NULL, NULL,  0, 0),
(44, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Human Frontiers in Science Program (HFSP) (5700)', NULL, NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES
(45, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Innovation Enhancement Grants - Build (IE) CCIP) (6013)', NULL, NULL,  0, 0),
(46, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Innovation Enhancement Grants - Entry (IE) (CCIPE) (6008)', NULL, NULL,  0, 0),
(47, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Innovation Enhancement Grants - Extend (IE) (CCIPX) (6016)', NULL, NULL,  0, 0),
(48, select id from business_unit where acronym_en like 'MEPS', '1/YR', 'Supplement', 0, 0, 0, 'Ship Time (RGPST) (5822)', NULL, NULL,  0, 0),
(49, NULL, '1/YR', NULL, 0, 0, 0, 'Strategic Network Grants Program (NETGP) (6070)', NULL, NULL,  0, 0),
(50, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Subatomic Physics - Individual (SAPIN) (5840)', 'Programme de subventions à la découverte en physique subatomique', NULL,  0, 0),
(51, NULL, '1/YR', NULL, 0, 0, 0, 'Synergy Awards (Colleges) CSYN (6477)', NULL, NULL,  0, 0),
(52, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Thematic Resources Support in Mathematics and Statistics (CTRMS)', 'Programme d''appui aux ressources thématiques  en mathématiques et en statistique', NULL,  0, 0),
(53, select id from business_unit where acronym_en like 'ELS', '1/YR', NULL, 0, 0, 0, 'Discovery Development Grant (DDG)', NULL, NULL, 0, 0),
(54, select id from business_unit where acronym_en like 'MEPS', '1/YR', 'Supplement', 0, 0, 0, 'Discovery Grants - Northern Research Supplement (NRS)', 'Programme de suppléments aux subventions à la découverte en recherche nordique', NULL,  0, 0),
(55, select id from business_unit where acronym_en like 'ELS', '1/YR', NULL, 0, 0, 0, 'Discovery Grants (DG) (RGPIN)', 'Programme de subventions à la découverte', NULL, 0, 0),
(56, select id from business_unit where acronym_en like 'ELS', '1/YR', 'Supplement', 0, 0, 0, 'DND/NSERC Discovery Grant Supplement  (DGDND)', 'Supplément aux subventions à la découverte MDN-CRSNG', NULL,  0, 0),
(57, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Research Tools and Instruments (RTI)', NULL, NULL,  0, 0),
(58, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Subatomic Physics - Projects (SAPPJ) - small and large projects', NULL, NULL,  0, 0),
(59, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Subatomic Physics - Research Tools and Instruments (SAP-RTI Cat. 1)', NULL, NULL,  0, 0),
(60, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Subatomic Physics - Research Tools and Instruments (SAP-RTI Cat. 2 & 3)', NULL, NULL,  0, 0),
(61, select id from business_unit where acronym_en like 'MEPS', '1/YR', NULL, 0, 0, 0, 'Subatomic Physics Major Resources Support (SAPMR)', NULL, NULL,  0, 0),
(62, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Connect Grants Level 1 for colleges', 'Subventions Connexion  Niveau 1  de College', NULL, 0, 0),
(63, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Connect Grants Level 1 for universities', 'Subventions Connexion  Niveau 1  des Universities', NULL, 0, 0),
(64, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Connect Grants Level 2 for colleges', 'Subventions Connexion  Niveau 2  de College', NULL, 0, 0),
(65, select id from business_unit where acronym_en like 'RP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Connect Grants Level 2 for universities', 'Subventions Connexion  Niveau 2  des Universities', NULL, 0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES
(66, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'L''Oréal-UNESCO For Women in Science – NSERC Postdoctoral Fellowship Supplement', 'Supplément du CRSNG et de L''Oréal-UNESCO pour les femmes et la science', NULL,  0, 0),
(67, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'STEAM Horizon Awards (6850)', 'Prix Horizon STIAM', NULL,  0, 0),
(68, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Discovery Frontiers ) (RGPBB)', 'Frontières de la découverte', NULL, 0, 0),
(69, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Discovery Frontiers  (RGPDD)', 'Frontières de la découverte', NULL, 0, 0),
(70, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Discovery Frontiers  (RGPDF)', 'Frontières de la découverte', NULL, 0, 0),
(71, select id from business_unit where acronym_en like 'MEPS', '1/YR+', NULL, 0, 0, 0, 'Discovery Frontiers  (RGPGR)', 'Frontières de la découverte', NULL, 0, 0),
(72, select id from business_unit where acronym_en like 'ELS', '1/YR', 'Supplement', 0, 0, 0, 'Discovery Grants - Accelerator Supplement (DAS)', 'Programme de suppléments d''accélération à la découverte', NULL, 0, 0),
(73, NULL, NULL, NULL, 0, 0, 0, 'Science Exposed', 'La preuve par l''image', NULL,  0, 0),
(74, NULL, NULL, NULL, 0, 0, 0, 'Science, Action!', 'Science, Action!', NULL, 0, 0),
(75, NULL, NULL, NULL, 0, 0, 0, 'College Special Initiatives (CSI)', NULL, NULL,  0, 0),
(76, select id from business_unit where acronym_en like 'ICSP', '1/Year', 'Fellowships', 0, 0, 0, 'EWR Steacie Fellowships (Research and Salary) (SMFSU) (5265)', 'Bourses commémoratives E.W.R. Steacie', NULL,  0, 0),
(77, NULL, NULL, 'Industrial Research Chairs (IRCPJ) (6235)', 0, 0, 0, 'IRC', 'professeurs-chercheurs industriels', NULL,  0, 0),
(78, NULL, NULL, 'Research Grants', 0, 0, 0, 'Industrial Research Chairs (IRCSA) (6235)', 'Subventions de professeurs-chercheurs industriels', NULL,  0, 0),
(79, NULL, NULL, NULL, 0, 0, 0, 'Parental Leave - Research Grants', NULL, NULL,  0, 0),
(80, NULL, NULL, NULL, 0, 0, 0, 'Parental Leave - Scholarships & Fellowships', NULL, NULL,  0, 0),
(81, NULL, '1/YR', NULL, 0, 0, 0, 'Synergy Awards (Universities) (SYN) (5239)', NULL, NULL,  0, 0),
(82, select id from business_unit where acronym_en like 'TIPS', '2/YR', NULL, 0, 0, 0, 'Canada Research Chairs (CRC)', 'Chaires de recherche du Canada', NULL,  0, 0),
(83, select id from business_unit where acronym_en like 'RGPD', '1/YR+', NULL, 0, 0, 0, 'Connections (HSSFC)  (650)', 'Lauréate du prix Connexion', NULL, 0, 0),
(84, select id from business_unit where acronym_en like 'FCD', '1/YR+', 'Research Grants', 0, 0, 0, 'Knowledge Synthesis Grant (872)', 'Subventions de synthèse des connaissances', NULL,  0, 0),
(85, select id from business_unit where acronym_en like 'TIPS', '1/YR', NULL, 0, 0, 0, 'Canada 150 Research Chairs Program', 'Chaires de recherche Canada 150', NULL,  0, 0),
(86, select id from business_unit where acronym_en like 'TIPS', '1/YR', NULL, 0, 0, 0, 'Canada Excellence Research Chairs (CERC)', 'Chaires d''excellence en recherche du Canada', NULL,  0, 0),
(87, select id from business_unit where acronym_en like 'TIPS', '1/YR', NULL, 0, 0, 0, 'Canada First Research Excellence Fund (CFREF)', 'Fonds d''excellence en recherche Apogée Canada', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES
(88, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Prize', 0, 0, 0, 'Impact Awards - Connection (780)', 'Prix Impacts du CRSH Prix Connexion', NULL,  0, 0),
(89, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Prize', 0, 0, 0, 'Impact Awards - Gold Medal (758)', 'Prix Impacts du CRSH Médaille d''or', NULL,  0, 0),
(90, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Prize', 0, 0, 0, 'Impact Awards - Insight (781)', 'Prix Impacts du CRSH Prix Savoir', NULL,  0, 0),
(91, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Prize', 0, 0, 0, 'Impact Awards - Partnership 9782)', 'Prix Impacts du CRSH Prix Partenariat', NULL,  0, 0),
(92, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Prize', 0, 0, 0, 'Impact Awards - Talent (783)', 'Prix Impacts du CRSH Prix Talent', NULL,  0, 0),
(93, select id from business_unit where acronym_en like 'RGPD', '1/Year', NULL, 0, 0, 0, 'Indigenous Research Capacity and Reconciliation — Connection Grants', 'Appel à propositions spécial pour l''attribution des subventions Connexion – Capacité de recherche autochtone et réconciliation', NULL,  0, 0),
(94, select id from business_unit where acronym_en like 'RGPD', '1/YR', 'Research Grants', 0, 0, 0, 'Insight Development Grants (430)', NULL, NULL, 0, 0),
(95, select id from business_unit where acronym_en like 'RTP', '1/YR', NULL, 0, 0, 0, 'Nelson Mandela', NULL, NULL,  0, 0),
(96, select id from business_unit where acronym_en like 'RGPD', '1/YR+', 'Research Grant', 0, 0, 0, 'SSHRC Institutional Grant (633)', 'Subventions institutionnelles du CRSH', NULL,  0, 0),
(97, select id from business_unit where acronym_en like 'COMMS', '1/Year', 'Prize', 0, 0, 0, 'StoryTellers', 'J''ai une histoire à raconter', NULL, 0, 0),
(98, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Supplement', 0, 0, 0, 'Bora Laskin National Fellowship (754)', 'Bourse canadienne Bora-Laskin', NULL,  0, 0),
(99, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Supplement', 0, 0, 0, 'Jules and Gabrielle Léger (759)', NULL, NULL,  0, 0),
(100, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Supplement', 0, 0, 0, 'Queen''s Fellowship (220 or 321)', NULL, NULL,  0, 0),
(101, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Supplement', 0, 0, 0, 'Aileen D. Ross (230)', NULL, NULL, 0, 0),
(102, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Internships', 0, 0, 0, 'Mitacs Elevate', NULL, NULL,  0, 0),
(103, select id from business_unit where acronym_en like 'RGPD', '1/YR+', NULL, 0, 0, 0, 'Aid to Scholarly Journals (651)', 'Aide aux revues savantes', NULL,  0, 0),
(104, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Fellowships', 0, 0, 0, 'Centre for International Governance Innovation (CIGI)', NULL, NULL,  0, 0),
(105, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Fellowships', 0, 0, 0, 'CMHC Housing research Training Awards', NULL, NULL,  0, 0),
(106, select id from business_unit where acronym_en like 'RGPD', '1+/YR', NULL, 0, 0, 0, 'Connection Grants (611)', 'Subventions Connections', NULL, 0, 0),
(107, select id from business_unit where acronym_en like 'RGPD', 'Open', NULL, 0, 0, 0, 'Department of National Defense Research Initiative (877)', NULL, NULL,  0, 0),
(108, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Scholarship', 0, 0, 0, 'Doctoral Awards - CGS; Direct; National (752)', NULL, NULL,  0, 0),
(109, select id from business_unit where acronym_en like 'RGPD', '1+/Yr', 'Research Grants', 0, 0, 0, 'Insight Grants (435)', NULL, NULL, 0, 0),
(110, select id from business_unit where acronym_en like 'RGPD', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Partnership Development Grants (890)', NULL, NULL, 0, 0),
(111, select id from business_unit where acronym_en like 'RGPD', '1+/YR', 'Partnerships Programs', 0, 0, 0, 'Partnership Engage Grant', NULL, NULL, 0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES
(112, select id from business_unit where acronym_en like 'RGPD', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Partnership Grants - Stage 1', NULL, NULL, 0, 0),
(113, select id from business_unit where acronym_en like 'RGPD', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Partnership Grants - Stage 2', NULL, NULL,  0, 0),
(114, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Scholarships', 0, 0, 0, 'Postdoctoral Fellowships (756)', NULL, NULL, 0, 0),
(115, select id from business_unit where acronym_en like 'RGPD', 'Open', NULL, 0, 0, 0, 'Sports Participation Research Initative (862)', NULL, NULL,  0, 0),
(116, select id from business_unit where acronym_en like 'RTP', 'Open', NULL, 0, 0, 0, 'Parental Leave - Scholarships & Fellowships (SSHRC) (769)', NULL, NULL,  0, 0),
(117, select id from business_unit where acronym_en like 'RTP', 'Open', NULL, 0, 0, 0, 'Parental Leave - Scholarships and Fellowships through grants (SSHRC)', NULL, NULL,  0, 0),
(118, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Parliamentary Internship Program (750)', 'Programme de stage parlementaire', NULL,  0, 0),
(119, select id from business_unit where acronym_en like 'RTP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Queen Elizabeth (774)', NULL, NULL,  0, 0),
(120, select id from business_unit where acronym_en like 'TIPS', '1/YR', NULL, 0, 0, 0, 'Special Initiatives Fund For Research Support And Collaboration', NULL, NULL,  0, 0),
(121, select id from business_unit where acronym_en like 'TIPS', '1/YR', NULL, 0, 0, 0, 'Research Support Fund (RSF)', NULL, NULL,  0, 0),
(122, select id from business_unit where acronym_en like 'NCE', NULL, 'Partnerships Programs', 0, 0, 0, 'CECR Centres of Excellence for Commercialization and Research', ' Programme des centres d''excellence en commercialisation et en recherche', NULL, 0, 0),
(123, select id from business_unit where acronym_en like 'SF', '1+/YR', 'Scholarships', 0, 0, 0, 'Canada Graduate Scholarships Michael Smith Foreign Study Supplements (MSFSS)', 'Programme de bourses d''études supérieures du Canada – Suppléments pour études à l''étranger Michael-Smith', NULL, 0, 0),
(124, select id from business_unit where acronym_en like 'CCPP', 'Open', 'Partnerships Programs', 0, 0, 0, 'Applied Research and Development Grants - Colleges (ARD)', NULL, NULL,  0, 0),
(125, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'College - University Idea to Innovation Grants (CU-I2I)', NULL, NULL,  0, 0),
(126, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Industrial Research Chairs for Colleges Grants (IRCC)', NULL, NULL,  0, 0),
(127, select id from business_unit where acronym_en like 'SF', '1/YR', 'Supplement', 0, 0, 0, 'Alice Wilson', 'Alice Wilson', NULL, 0, 0),
(128, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Banting Postdoctoral Fellowships', 'Bourses postdoctorales Banting', NULL, 0, 0),
(129, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Vanier', NULL, NULL, 0, 0),
(130, select id from business_unit where acronym_en like 'SF', '1/YR', 'Scholarships', 0, 0, 0, 'Canada Graduate Scholarship - Masters (CGSM)', 'Bourses d''études supérieures du Canada', NULL, 0, 0),
(131, select id from business_unit where acronym_en like 'NCE', NULL, 'Partnerships Programs', 0, 0, 0, 'Business-Led Networks of Centres of Excellence program', 'Programme des réseaux de centres d''excellence dirigés par l''entreprise', NULL,  0, 0),
(132, select id from business_unit where acronym_en like 'NCE', NULL, 'Partnerships Programs', 0, 0, 0, 'International Knowledge Translation Platforms initiative (IKTP)', 'Initiative de soutien international pour le transfert des connaissances (SITC)', NULL,  0, 0),
(133, select id from business_unit where acronym_en like 'NCE', NULL, 'Partnerships Programs', 0, 0, 0, 'NCE Knowledge Mobilization (NCE-KM)', 'L''initiative de Mobilisation des connaissances (MC-RCE)', NULL,  0, 0);
INSERT INTO "FUNDING_OPPORTUNITY" (id, business_unit_id, frequency, funding_type, is_complex, is_edi_required, is_joint_initiative, name_en, name_fr, partner_org, isloi, isnoi) VALUES
(134, select id from business_unit where acronym_en like 'NCE', NULL, 'Partnerships Programs', 0, 0, 0, 'Network for Centers of Excellence', 'Programme des réseaux de centres d''excellence (RCE)', NULL,  0, 0),
(135, select id from business_unit where acronym_en like 'CCPP', '1/YR', 'Partnerships Programs', 0, 0, 0, 'Technology Access Centre (TAC)', 'subventions d''établissement de centres d''accès à la technologie', NULL,  0, 0),
(136, NULL, '1/YR+', NULL, 0, 0, 0, 'Canadian Initiative on social Statistics', NULL, NULL,  0, 0),
(137, NULL, '1/YR+', NULL, 0, 0, 0, 'Digging into Data', 'Au cœur des données numériques', NULL,  0, 0),
(138, select id from business_unit where acronym_en like 'RP', NULL, 'Partnerships Programs', 0, 0, 0, 'Research Partnerships Renewal Project (RPR)', NULL, NULL, 0, 0),
(139, select id from business_unit where acronym_en like 'TIPS', '1/YR for first year; unknown after that', 'Research Grant', 0, 0, 0, 'Frontiers in Research Fund - Stream 1', NULL, NULL,  0, 0),
(140, select id from business_unit where acronym_en like 'TIPS', '1/YR in second year; unknown after that', 'Research Grant', 0, 0, 0, 'Frontiers in Research Fund - Stream 2', NULL, NULL,  0, 0),
(141, select id from business_unit where acronym_en like 'TIPS', 'Unknown, but likely 1/YR in second year', 'Research Grant', 0, 0, 0, 'Frontiers in Research Fund - Special Calls', NULL, NULL,  0, 0);
