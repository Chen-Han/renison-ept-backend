--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: answer; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE answer (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    content character varying(255) NOT NULL,
    is_correct boolean NOT NULL,
    question_id bigint
);


ALTER TABLE answer OWNER TO hanchen;

--
-- Name: answer_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE answer_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE answer_seq OWNER TO hanchen;

--
-- Name: category; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE category (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    test_id bigint NOT NULL,
    ordering integer,
    timeallowed double precision
);


ALTER TABLE category OWNER TO hanchen;

--
-- Name: category_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE category_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE category_seq OWNER TO hanchen;

--
-- Name: free_text; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE free_text (
    id bigint NOT NULL
);


ALTER TABLE free_text OWNER TO hanchen;

--
-- Name: html_component; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE html_component (
    content character varying(255),
    id bigint NOT NULL
);


ALTER TABLE html_component OWNER TO hanchen;

--
-- Name: multiple_choice; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE multiple_choice (
    id bigint NOT NULL
);


ALTER TABLE multiple_choice OWNER TO hanchen;

--
-- Name: pencil; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE pencil (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    pencil_type integer,
    test_taker_id bigint
);


ALTER TABLE pencil OWNER TO hanchen;

--
-- Name: pencil_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE pencil_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pencil_seq OWNER TO hanchen;

--
-- Name: progress; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE progress (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    end_at timestamp with time zone NOT NULL,
    start_at timestamp with time zone NOT NULL,
    category_id bigint NOT NULL,
    test_session_id bigint
);


ALTER TABLE progress OWNER TO hanchen;

--
-- Name: progress_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE progress_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE progress_seq OWNER TO hanchen;

--
-- Name: question; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE question (
    id bigint NOT NULL,
    content character varying(255),
    ordering integer
);


ALTER TABLE question OWNER TO hanchen;

--
-- Name: question_answer; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE question_answer (
    question_id bigint NOT NULL,
    answer_id bigint NOT NULL
);


ALTER TABLE question_answer OWNER TO hanchen;

--
-- Name: question_response; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE question_response (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    question_id bigint NOT NULL,
    test_session_id bigint NOT NULL
);


ALTER TABLE question_response OWNER TO hanchen;

--
-- Name: question_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE question_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE question_seq OWNER TO hanchen;

--
-- Name: questionresponse_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE questionresponse_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE questionresponse_seq OWNER TO hanchen;

--
-- Name: response_content; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE response_content (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    text character varying(255),
    answer_id bigint,
    question_response_id bigint NOT NULL
);


ALTER TABLE response_content OWNER TO hanchen;

--
-- Name: responsecontent_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE responsecontent_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE responsecontent_seq OWNER TO hanchen;

--
-- Name: schema_version; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE schema_version (
    version_rank integer NOT NULL,
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE schema_version OWNER TO hanchen;

--
-- Name: school; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE school (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    name character varying(255)
);


ALTER TABLE school OWNER TO hanchen;

--
-- Name: school_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE school_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE school_seq OWNER TO hanchen;

--
-- Name: short_answer; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE short_answer (
    id bigint NOT NULL
);


ALTER TABLE short_answer OWNER TO hanchen;

--
-- Name: test; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE test (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    active boolean NOT NULL,
    description character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE test OWNER TO hanchen;

--
-- Name: test_component; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE test_component (
    component_type character varying(31) NOT NULL,
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    ordering integer,
    category_id bigint NOT NULL,
    test_id bigint NOT NULL
);


ALTER TABLE test_component OWNER TO hanchen;

--
-- Name: test_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE test_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE test_seq OWNER TO hanchen;

--
-- Name: test_session; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE test_session (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    last_active_at timestamp with time zone NOT NULL,
    score double precision
);


ALTER TABLE test_session OWNER TO hanchen;

--
-- Name: test_taker; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE test_taker (
    id bigint NOT NULL,
    create_timestamp timestamp with time zone NOT NULL,
    update_timestamp timestamp with time zone NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    school_id bigint
);


ALTER TABLE test_taker OWNER TO hanchen;

--
-- Name: testcomponent_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE testcomponent_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE testcomponent_seq OWNER TO hanchen;

--
-- Name: testsession_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE testsession_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE testsession_seq OWNER TO hanchen;

--
-- Name: testtaker_seq; Type: SEQUENCE; Schema: public; Owner: hanchen
--

CREATE SEQUENCE testtaker_seq
    START WITH 1000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE testtaker_seq OWNER TO hanchen;

--
-- Name: true_false; Type: TABLE; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE TABLE true_false (
    id bigint NOT NULL,
    answer boolean NOT NULL
);


ALTER TABLE true_false OWNER TO hanchen;

--
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY answer (id, create_timestamp, update_timestamp, content, is_correct, question_id) FROM stdin;
1083	2016-02-27 17:30:52.202-08	2016-02-27 17:30:52.202-08	Great!	t	\N
1084	2016-02-27 17:30:52.202-08	2016-02-27 17:30:52.202-08	Good!	f	\N
1085	2016-02-27 17:30:52.202-08	2016-02-27 17:30:52.202-08	So so!	f	\N
1086	2016-02-27 17:33:25.311-08	2016-02-27 17:33:25.311-08	Great!	t	\N
1087	2016-02-27 17:33:25.312-08	2016-02-27 17:33:25.312-08	Good!	f	\N
1088	2016-02-27 17:33:25.312-08	2016-02-27 17:33:25.312-08	So so!	f	\N
1090	2016-03-01 19:31:21.894-08	2016-03-01 19:31:21.894-08	Great!	t	\N
1091	2016-03-01 19:31:21.894-08	2016-03-01 19:31:21.894-08	Good!	f	\N
1092	2016-03-01 19:31:21.894-08	2016-03-01 19:31:21.894-08	So so!	f	\N
1093	2016-03-01 19:31:50.703-08	2016-03-01 19:31:50.703-08	for	t	\N
1094	2016-03-01 19:31:58.759-08	2016-03-01 19:31:58.759-08	up	t	\N
1095	2016-03-01 19:31:58.759-08	2016-03-01 19:31:58.759-08	into	t	\N
\.


--
-- Name: answer_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('answer_seq', 1090, true);


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY category (id, create_timestamp, update_timestamp, test_id, ordering, timeallowed) FROM stdin;
1060	2016-02-20 15:37:54.708-08	2016-02-20 15:37:54.708-08	1060	0	10
1080	2016-02-27 15:11:15.134-08	2016-02-27 15:11:15.134-08	1060	2	30.5
\.


--
-- Name: category_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('category_seq', 1080, true);


--
-- Data for Name: free_text; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY free_text (id) FROM stdin;
\.


--
-- Data for Name: html_component; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY html_component (content, id) FROM stdin;
<h1>Word</h1>	1100
<h1>Word</h1>	1110
\.


--
-- Data for Name: multiple_choice; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY multiple_choice (id) FROM stdin;
1131
1140
1150
1160
1170
1180
1190
1200
1210
1220
1230
1240
1250
1251
1252
1261
\.


--
-- Data for Name: pencil; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY pencil (id, create_timestamp, update_timestamp, pencil_type, test_taker_id) FROM stdin;
\.


--
-- Name: pencil_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('pencil_seq', 1000, false);


--
-- Data for Name: progress; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY progress (id, create_timestamp, update_timestamp, end_at, start_at, category_id, test_session_id) FROM stdin;
\.


--
-- Name: progress_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('progress_seq', 1000, false);


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY question (id, content, ordering) FROM stdin;
1131	How was your life?	1
1140	How was your day?	1
1150	How was your day?	1
1160	How was your day?	1
1170	How was your day?	1
1180	How was your day?	1
1190	How was your day?	1
1200	How was your day?	1
1210	How was your day?	1
1220	How was your day?	1
1230	How was your day?	1
1240	How was your day?	1
1250	How was your day?	1
1251	How was your life?	1
1252	How was your life?	1
1260	The color of sky is blue	7
1261	lorem ipsum blah bling was running interceptedly	1
1262	The speakder refers to his own company	7
1263	I lost my dog. I have to look ___ it.	7
1264	Can you look ___ this problem?	7
\.


--
-- Data for Name: question_answer; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY question_answer (question_id, answer_id) FROM stdin;
1251	1083
1251	1084
1251	1085
1252	1086
1252	1087
1252	1088
1261	1090
1261	1091
1261	1092
1263	1093
1264	1094
1264	1095
\.


--
-- Data for Name: question_response; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY question_response (id, create_timestamp, update_timestamp, question_id, test_session_id) FROM stdin;
\.


--
-- Name: question_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('question_seq', 1000, false);


--
-- Name: questionresponse_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('questionresponse_seq', 1000, false);


--
-- Data for Name: response_content; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY response_content (id, create_timestamp, update_timestamp, text, answer_id, question_response_id) FROM stdin;
\.


--
-- Name: responsecontent_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('responsecontent_seq', 1000, false);


--
-- Data for Name: schema_version; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY schema_version (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	1	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	hanchen	2016-02-11 14:20:15.618561	0	t
2	2	2016.02.10.1150	create question answer	SQL	V2016.02.10.1150__create_question_answer.sql	-2020958134	hanchen	2016-02-11 14:20:50.03819	37	t
\.


--
-- Data for Name: school; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY school (id, create_timestamp, update_timestamp, name) FROM stdin;
1	2016-02-25 00:00:00-08	2016-02-25 00:00:00-08	UofWaterloo
1000	2016-02-27 15:00:49.157-08	2016-02-27 15:00:49.157-08	Hello
\.


--
-- Name: school_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('school_seq', 1000, true);


--
-- Data for Name: short_answer; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY short_answer (id) FROM stdin;
1263
1264
\.


--
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY test (id, create_timestamp, update_timestamp, active, description, name) FROM stdin;
1060	2016-02-20 15:37:54.689-08	2016-02-20 15:37:54.689-08	f	\N	hello
\.


--
-- Data for Name: test_component; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY test_component (component_type, id, create_timestamp, update_timestamp, ordering, category_id, test_id) FROM stdin;
HTML	1100	2016-02-27 15:53:55.108-08	2016-02-27 15:53:55.108-08	6	1060	1060
HTML	1110	2016-02-27 15:55:37.813-08	2016-02-27 15:55:37.813-08	6	1060	1060
MULTIPLE_CHOICE	1131	2016-02-27 16:37:27.046-08	2016-02-27 16:37:27.046-08	1	1060	1060
MULTIPLE_CHOICE	1140	2016-02-27 16:40:16.92-08	2016-02-27 16:40:16.92-08	1	1060	1060
MULTIPLE_CHOICE	1150	2016-02-27 16:44:53.895-08	2016-02-27 16:44:53.895-08	1	1060	1060
MULTIPLE_CHOICE	1160	2016-02-27 16:45:50.531-08	2016-02-27 16:45:50.531-08	1	1060	1060
MULTIPLE_CHOICE	1170	2016-02-27 16:50:22.224-08	2016-02-27 16:50:22.224-08	1	1060	1060
MULTIPLE_CHOICE	1180	2016-02-27 16:50:52.164-08	2016-02-27 16:50:52.164-08	1	1060	1060
MULTIPLE_CHOICE	1190	2016-02-27 16:52:12.374-08	2016-02-27 16:52:12.374-08	1	1060	1060
MULTIPLE_CHOICE	1200	2016-02-27 16:58:53.721-08	2016-02-27 16:58:53.721-08	1	1060	1060
MULTIPLE_CHOICE	1210	2016-02-27 17:00:21.742-08	2016-02-27 17:00:21.742-08	1	1060	1060
MULTIPLE_CHOICE	1220	2016-02-27 17:03:46.601-08	2016-02-27 17:03:46.601-08	1	1060	1060
MULTIPLE_CHOICE	1230	2016-02-27 17:04:53.372-08	2016-02-27 17:04:53.372-08	1	1060	1060
MULTIPLE_CHOICE	1240	2016-02-27 17:12:59.649-08	2016-02-27 17:12:59.649-08	1	1060	1060
MULTIPLE_CHOICE	1250	2016-02-27 17:13:38.328-08	2016-02-27 17:13:38.328-08	1	1060	1060
MULTIPLE_CHOICE	1251	2016-02-27 17:30:52.201-08	2016-02-27 17:30:52.201-08	1	1060	1060
MULTIPLE_CHOICE	1252	2016-02-27 17:33:25.311-08	2016-02-27 17:33:25.311-08	1	1060	1060
TRUE_FALSE	1260	2016-03-01 19:31:06.167-08	2016-03-01 19:31:06.167-08	7	1060	1060
MULTIPLE_CHOICE	1261	2016-03-01 19:31:21.893-08	2016-03-01 19:31:21.893-08	1	1060	1060
TRUE_FALSE	1262	2016-03-01 19:31:39.716-08	2016-03-01 19:31:39.716-08	7	1060	1060
SHORT_ANSWER	1263	2016-03-01 19:31:50.703-08	2016-03-01 19:31:50.703-08	7	1060	1060
SHORT_ANSWER	1264	2016-03-01 19:31:58.759-08	2016-03-01 19:31:58.759-08	7	1060	1060
\.


--
-- Name: test_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('test_seq', 1060, true);


--
-- Data for Name: test_session; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY test_session (id, create_timestamp, update_timestamp, last_active_at, score) FROM stdin;
\.


--
-- Data for Name: test_taker; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY test_taker (id, create_timestamp, update_timestamp, first_name, last_name, school_id) FROM stdin;
1	2016-02-25 00:00:00-08	2016-02-25 00:00:00-08	han	chen	1
1010	2016-02-26 18:13:06.118-08	2016-02-26 18:13:06.118-08	John	Chen	\N
1020	2016-02-26 18:21:49.339-08	2016-02-26 18:21:49.339-08	John	Chen	1
1021	2016-02-26 18:23:10.781-08	2016-02-26 18:23:10.781-08	John	Chen	\N
1000	2016-02-26 18:06:00.544-08	2016-02-26 18:23:42.276-08	Han	Chen	1
1030	2016-02-27 10:39:37.363-08	2016-02-27 10:39:37.363-08	Steven	\N	1
1040	2016-02-27 13:50:23.265-08	2016-02-27 13:50:23.265-08	Eve	Anderson	1
1050	2016-02-27 14:00:32.121-08	2016-02-27 14:00:32.121-08	Johnason	Anderson	1
1060	2016-02-27 14:45:02.692-08	2016-02-27 14:45:02.692-08	Johnason	Anderson	1
\.


--
-- Name: testcomponent_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('testcomponent_seq', 1260, true);


--
-- Name: testsession_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('testsession_seq', 1000, false);


--
-- Name: testtaker_seq; Type: SEQUENCE SET; Schema: public; Owner: hanchen
--

SELECT pg_catalog.setval('testtaker_seq', 1060, true);


--
-- Data for Name: true_false; Type: TABLE DATA; Schema: public; Owner: hanchen
--

COPY true_false (id, answer) FROM stdin;
1260	t
1262	f
\.


--
-- Name: answer_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: free_text_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY free_text
    ADD CONSTRAINT free_text_pkey PRIMARY KEY (id);


--
-- Name: html_component_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY html_component
    ADD CONSTRAINT html_component_pkey PRIMARY KEY (id);


--
-- Name: multiple_choice_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY multiple_choice
    ADD CONSTRAINT multiple_choice_pkey PRIMARY KEY (id);


--
-- Name: pencil_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY pencil
    ADD CONSTRAINT pencil_pkey PRIMARY KEY (id);


--
-- Name: progress_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY progress
    ADD CONSTRAINT progress_pkey PRIMARY KEY (id);


--
-- Name: question_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: question_response_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY question_response
    ADD CONSTRAINT question_response_pkey PRIMARY KEY (id);


--
-- Name: response_content_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY response_content
    ADD CONSTRAINT response_content_pkey PRIMARY KEY (id);


--
-- Name: schema_version_pk; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY schema_version
    ADD CONSTRAINT schema_version_pk PRIMARY KEY (version);


--
-- Name: school_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY school
    ADD CONSTRAINT school_pkey PRIMARY KEY (id);


--
-- Name: short_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY short_answer
    ADD CONSTRAINT short_answer_pkey PRIMARY KEY (id);


--
-- Name: test_component_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY test_component
    ADD CONSTRAINT test_component_pkey PRIMARY KEY (id);


--
-- Name: test_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- Name: test_session_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY test_session
    ADD CONSTRAINT test_session_pkey PRIMARY KEY (id);


--
-- Name: test_taker_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY test_taker
    ADD CONSTRAINT test_taker_pkey PRIMARY KEY (id);


--
-- Name: true_false_pkey; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY true_false
    ADD CONSTRAINT true_false_pkey PRIMARY KEY (id);


--
-- Name: uk_1chowmbf27f0cbp7on9ysvhjo; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY test
    ADD CONSTRAINT uk_1chowmbf27f0cbp7on9ysvhjo UNIQUE (name);


--
-- Name: uk_pp3ml2kmf8vlgciq2vewu4xea; Type: CONSTRAINT; Schema: public; Owner: hanchen; Tablespace: 
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT uk_pp3ml2kmf8vlgciq2vewu4xea UNIQUE (answer_id);


--
-- Name: schema_version_ir_idx; Type: INDEX; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE INDEX schema_version_ir_idx ON schema_version USING btree (installed_rank);


--
-- Name: schema_version_s_idx; Type: INDEX; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE INDEX schema_version_s_idx ON schema_version USING btree (success);


--
-- Name: schema_version_vr_idx; Type: INDEX; Schema: public; Owner: hanchen; Tablespace: 
--

CREATE INDEX schema_version_vr_idx ON schema_version USING btree (version_rank);


--
-- Name: fk5lv1uo722ab6597dcrpf9e7q4; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY question_response
    ADD CONSTRAINT fk5lv1uo722ab6597dcrpf9e7q4 FOREIGN KEY (test_session_id) REFERENCES test_session(id);


--
-- Name: fk7l5ew8hphy9ixj9ra72tfawbw; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY question
    ADD CONSTRAINT fk7l5ew8hphy9ixj9ra72tfawbw FOREIGN KEY (id) REFERENCES test_component(id);


--
-- Name: fk8e5vu93xyxjwb9lgbsr0qjwkv; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY free_text
    ADD CONSTRAINT fk8e5vu93xyxjwb9lgbsr0qjwkv FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fk8frr4bcabmmeyyu60qt7iiblo; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT fk8frr4bcabmmeyyu60qt7iiblo FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fk_2kgna3u1oquljad77hrjfunho; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY free_text
    ADD CONSTRAINT fk_2kgna3u1oquljad77hrjfunho FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fk_4p2g997lywoqedyr1fkkrbo15; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY short_answer
    ADD CONSTRAINT fk_4p2g997lywoqedyr1fkkrbo15 FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fk_pk1126d2na8kt0iw27w53xadg; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY multiple_choice
    ADD CONSTRAINT fk_pk1126d2na8kt0iw27w53xadg FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fk_rjlkcl0dtru03s095t6vtlm01; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY true_false
    ADD CONSTRAINT fk_rjlkcl0dtru03s095t6vtlm01 FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fkb2x9s9ffg4vtyhk1t40dwdepe; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY html_component
    ADD CONSTRAINT fkb2x9s9ffg4vtyhk1t40dwdepe FOREIGN KEY (id) REFERENCES test_component(id);


--
-- Name: fkc7p4fdbt5hmtla7jmxr6imer5; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY multiple_choice
    ADD CONSTRAINT fkc7p4fdbt5hmtla7jmxr6imer5 FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fkdntar9aibbtcwwg8cnl1h8844; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY progress
    ADD CONSTRAINT fkdntar9aibbtcwwg8cnl1h8844 FOREIGN KEY (test_session_id) REFERENCES test_session(id);


--
-- Name: fkflwcda2rengsndju5f1deywok; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT fkflwcda2rengsndju5f1deywok FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fkhdsmf287pcvg7fabbudyeffsu; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY test_taker
    ADD CONSTRAINT fkhdsmf287pcvg7fabbudyeffsu FOREIGN KEY (school_id) REFERENCES school(id);


--
-- Name: fkil3pbbv488omhx2gkakco46yl; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT fkil3pbbv488omhx2gkakco46yl FOREIGN KEY (answer_id) REFERENCES answer(id);


--
-- Name: fkiyouge03ev5tbesn70iofwubp; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY question_response
    ADD CONSTRAINT fkiyouge03ev5tbesn70iofwubp FOREIGN KEY (question_id) REFERENCES question(id);


--
-- Name: fkjfcmfsn2m0ggkpjpedbdva60i; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY category
    ADD CONSTRAINT fkjfcmfsn2m0ggkpjpedbdva60i FOREIGN KEY (test_id) REFERENCES test(id);


--
-- Name: fkm22q8qw73egr6l4j88lb1e432; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY progress
    ADD CONSTRAINT fkm22q8qw73egr6l4j88lb1e432 FOREIGN KEY (category_id) REFERENCES category(id);


--
-- Name: fkmd09ma9fev35bw4acpkclxc6j; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY response_content
    ADD CONSTRAINT fkmd09ma9fev35bw4acpkclxc6j FOREIGN KEY (question_response_id) REFERENCES question_response(id);


--
-- Name: fknsxf6auvg8wyh8i9fkb0oqm6y; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY short_answer
    ADD CONSTRAINT fknsxf6auvg8wyh8i9fkb0oqm6y FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fkoh0wlh2bcpc0f3a3q72i6mt9v; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY pencil
    ADD CONSTRAINT fkoh0wlh2bcpc0f3a3q72i6mt9v FOREIGN KEY (test_taker_id) REFERENCES test_taker(id);


--
-- Name: fkq2w74jr7875f3js2d58a8atgy; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY response_content
    ADD CONSTRAINT fkq2w74jr7875f3js2d58a8atgy FOREIGN KEY (answer_id) REFERENCES answer(id);


--
-- Name: fkqwp79ndey2g9lruffahspm2lf; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY test_component
    ADD CONSTRAINT fkqwp79ndey2g9lruffahspm2lf FOREIGN KEY (test_id) REFERENCES test(id);


--
-- Name: fkriyt9iw8w5nusyiw900n9k81; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY true_false
    ADD CONSTRAINT fkriyt9iw8w5nusyiw900n9k81 FOREIGN KEY (id) REFERENCES question(id);


--
-- Name: fkw7d5flioi8dxipqh39b4s0dt; Type: FK CONSTRAINT; Schema: public; Owner: hanchen
--

ALTER TABLE ONLY test_component
    ADD CONSTRAINT fkw7d5flioi8dxipqh39b4s0dt FOREIGN KEY (category_id) REFERENCES category(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

