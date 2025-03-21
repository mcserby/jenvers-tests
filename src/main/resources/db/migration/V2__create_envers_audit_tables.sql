CREATE SEQUENCE IF NOT EXISTS public.revinfo_seq INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS public.revinfo (
    rev integer NOT NULL DEFAULT nextval('public.revinfo_seq'),
    revtstmp bigint,
    CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
);

CREATE TABLE IF NOT EXISTS public.question_aud (
    rev integer NOT NULL,
    revtype smallint,
    id bigint NOT NULL,
    text character varying(255),
    CONSTRAINT question_aud_pkey PRIMARY KEY (rev, id),
    CONSTRAINT fk7b7vnhne6wrg69pvdmjtvutq0 FOREIGN KEY (rev) REFERENCES public.revinfo (rev)
);

CREATE TABLE IF NOT EXISTS public.questionnaire_aud (
    rev integer NOT NULL,
    revtype smallint,
    id bigint NOT NULL,
    owner_id bigint,
    title character varying(255),
    CONSTRAINT questionnaire_aud_pkey PRIMARY KEY (rev, id),
    CONSTRAINT fkbfnb1gi70ibcxdwuh7bn5fh7x FOREIGN KEY (rev) REFERENCES public.revinfo (rev)
);

CREATE TABLE IF NOT EXISTS public.questionnaire_editors_aud (
    rev integer NOT NULL,
    revtype smallint,
    editor_id bigint NOT NULL,
    questionnaire_id bigint NOT NULL,
    CONSTRAINT questionnaire_editors_aud_pkey PRIMARY KEY (rev, editor_id, questionnaire_id),
    CONSTRAINT fko2iqj2bcputk050tox6rrurmq FOREIGN KEY (rev) REFERENCES public.revinfo (rev)
);

CREATE TABLE IF NOT EXISTS public.questionnaire_questions_aud (
    rev integer NOT NULL,
    revtype smallint,
    question_id bigint NOT NULL,
    questionnaire_id bigint NOT NULL,
    CONSTRAINT questionnaire_questions_aud_pkey PRIMARY KEY (rev, question_id, questionnaire_id),
    CONSTRAINT fkg3n1kb9x2frkr18q378q4tssi FOREIGN KEY (rev) REFERENCES public.revinfo (rev)
);

CREATE TABLE IF NOT EXISTS public.user_aud (
    rev integer NOT NULL,
    revtype smallint,
    id bigint NOT NULL,
    email character varying(255),
    username character varying(255),
    CONSTRAINT user_aud_pkey PRIMARY KEY (rev, id),
    CONSTRAINT fk89ntto9kobwahrwxbne2nqcnr FOREIGN KEY (rev) REFERENCES public.revinfo (rev)
);

