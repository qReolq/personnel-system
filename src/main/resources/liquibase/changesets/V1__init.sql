create table if not exists departments
(
    id        bigserial primary key,
    name      varchar(255) not null,
    parent_id bigint,
    create_at date         not null,
    constraint fk_departments_departments foreign key (parent_id) references departments (id) on delete cascade on update no action
);

create table if not exists employees
(
    id            bigserial primary key,
    full_name     VARCHAR(255) NOT NULL,
    department_id bigint,
    constraint fk_employees_departments foreign key (department_id) references departments (id) on delete cascade on update no action
);

create table if not exists department_assignments
(
    id            bigserial primary key,
    department_id bigint not null,
    employee_id   bigint not null,
    hired_date    date   not null,
    fired_date    date,
    constraint fk_department_assignments_employee foreign key (employee_id) references employees (id) on delete cascade on update no action,
    constraint fk_department_assignments_departments foreign key (department_id) references departments (id) on delete cascade on update no action

)