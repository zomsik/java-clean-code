
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.account
(
    id                     serial      NOT NULL,
    username               varchar(50) NOT NULL,
    password               varchar(512) NOT NULL,
    email                  varchar(50) NOT NULL,
    role                   varchar(2) NOT NULL default '1',
    birth_date             date NOT NULL,
    register_date          timestamp without time zone NOT NULL default now()
    );

CREATE TABLE IF NOT EXISTS public.role
(
    role        varchar(2)  NOT NULL,
    description varchar(100) NOT NULL
    );

ALTER TABLE public.account
    ADD CONSTRAINT PK_account PRIMARY KEY (id);
CREATE INDEX IXFK_account_role ON public.role (role ASC);

ALTER TABLE public.role
    ADD CONSTRAINT PK_role PRIMARY KEY (role);

ALTER TABLE public.account
    ADD CONSTRAINT FK_account_role FOREIGN KEY (role) REFERENCES public.role (role) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO public.role(role, description) values ('1', 'USER');
INSERT INTO public.role(role, description) values ('2', 'MODERATOR');
INSERT INTO public.role(role, description) values ('3', 'ADMIN');

CREATE TABLE IF NOT EXISTS public.post
(
    id                     serial      NOT NULL,
    account_id             Integer     NOT NULL,
    text                   text        NOT NULL,
    category_id            integer     NOT NULL,
    image                  bytea,
    create_date            timestamp without time zone NOT NULL,
    deleted                boolean      NOT NULL,
    deleted_by             Integer,
    deleted_date           timestamp without time zone
);

CREATE TABLE IF NOT EXISTS public.category
(
    id                     serial      NOT NULL,
    name                   varchar(100)     NOT NULL
    );



CREATE TABLE IF NOT EXISTS public.comment
(
    id                     serial      NOT NULL,
    account_id             Integer     NOT NULL,
    post_id                Integer     NOT NULL,
    text                   text        NOT NULL,
    create_date            timestamp without time zone NOT NULL,
    deleted                boolean  NOT NULL,
    deleted_by             Integer,
    deleted_date           timestamp without time zone
);


CREATE TABLE IF NOT EXISTS public.post_likes
(
    id                     serial      NOT NULL,
    account_id             Integer     NOT NULL,
    post_id                Integer     NOT NULL,
    create_date            timestamp without time zone NOT NULL default now()
    );

CREATE TABLE IF NOT EXISTS public.comment_likes
(
    id                     serial      NOT NULL,
    account_id             Integer     NOT NULL,
    comment_id                Integer     NOT NULL,
    create_date            timestamp without time zone NOT NULL default now()
    );


ALTER TABLE public.post
    ADD CONSTRAINT PK_post PRIMARY KEY (id);


ALTER TABLE public.category
    ADD CONSTRAINT PK_category PRIMARY KEY (id);



ALTER TABLE public.comment
    ADD CONSTRAINT PK_comment PRIMARY KEY (id);

ALTER TABLE public.post_likes
    ADD CONSTRAINT PK_post_likes PRIMARY KEY (id);

ALTER TABLE public.comment_likes
    ADD CONSTRAINT PK_comment_likes PRIMARY KEY (id);


INSERT INTO public.category(id, name) values (1, 'none');
INSERT INTO public.category(id, name) values (2, 'technologia');
INSERT INTO public.category(id, name) values (3, 'informacje');
INSERT INTO public.category(id, name) values (4, 'ciekawostki');
INSERT INTO public.category(id, name) values (5, 'motoryzacja');
INSERT INTO public.category(id, name) values (6, 'ukraina');
INSERT INTO public.category(id, name) values (7, 'podroze');
INSERT INTO public.category(id, name) values (8, 'sport');
INSERT INTO public.category(id, name) values (9, 'rozrywka');
INSERT INTO public.category(id, name) values (10, 'gospodarka');


INSERT INTO public.account(id, username, password, email, role, birth_date, register_date)  values (1, 'zomsik', 'test', 'test@gmai.com', '3', '2000-02-02', '2023-02-02');

INSERT INTO public.post(id, account_id, text, category_id, image, create_date, deleted)  values (1, 1, 'test test test test', 1, null, '2023-02-02', false);

INSERT INTO public.comment(id, account_id, post_id, text, create_date, deleted)  values (1, 1, 1, 'Komentarz 1', '2023-02-02', false);
INSERT INTO public.comment(id, account_id, post_id, text, create_date, deleted)  values (2, 1, 1, 'Komentarz 2', '2023-02-02', false);


