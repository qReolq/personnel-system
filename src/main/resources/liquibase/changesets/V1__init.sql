create table if not exists departments
(
    id        bigserial primary key,
    name      varchar(255) not null,
    parent_id bigint,
    constraint fk_departments_departments foreign key (parent_id) references departments (id) on delete cascade on update no action
);

create table if not exists employees
(
    id            bigserial primary key,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    middle_name   VARCHAR(255),
    department_id bigint,
    hired_date    DATE         NOT NULL,
    fired_date    DATE,
    constraint fk_employees_departments foreign key (department_id) references departments (id) on delete cascade on update no action
);