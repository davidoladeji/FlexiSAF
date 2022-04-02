create table departments
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) null,
    created_at datetime     null,
    updated_at datetime     null
);

create table my_sequence_name
(
    next_val bigint null
);

create table students
(
    id            bigint       not null
        primary key,
    matric_number varchar(255) null,
    first_name    varchar(255) null,
    last_name     varchar(255) null,
    other_name    varchar(255) null,
    full_name     varchar(255) null,
    gender        varchar(255) null,
    date_birth    datetime     null,
    created_by    varchar(255) null,
    created_at    datetime     null,
    updated_at    datetime     null,
    department_id bigint       null,
    constraint FKalgc33nsolpmegw14o3h6g6rr
        foreign key (department_id) references departments (id)
);


