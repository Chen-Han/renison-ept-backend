    create table answer (
        id int8 not null,
        create_timestamp timestamp with time zone not null,
        update_timestamp timestamp with time zone not null,
        content varchar(255) not null,
        is_correct boolean not null,
        primary key (id)
    );

    create table free_text (
        id int8 not null,
        primary key (id)
    );

    create table multiple_choice (
        id int8 not null,
        primary key (id)
    );

    create table question (
        id int8 not null,
        create_timestamp timestamp with time zone not null,
        update_timestamp timestamp with time zone not null,
        content varchar(255),
        ordering int4,
        type varchar(255),
        primary key (id)
    );

    create table short_answer (
        id int8 not null,
        primary key (id)
    );

    create table true_false (
        id int8 not null,
        primary key (id)
    );

    alter table free_text 
        add constraint FK_2kgna3u1oquljad77hrjfunho 
        foreign key (id) 
        references question;

    alter table multiple_choice 
        add constraint FK_pk1126d2na8kt0iw27w53xadg 
        foreign key (id) 
        references question;

    alter table short_answer 
        add constraint FK_4p2g997lywoqedyr1fkkrbo15 
        foreign key (id) 
        references question;

    alter table true_false 
        add constraint FK_rjlkcl0dtru03s095t6vtlm01 
        foreign key (id) 
        references question;

    create sequence Answer_SEQ start 1000 increment 10;

    create sequence Question_SEQ start 1000 increment 10;