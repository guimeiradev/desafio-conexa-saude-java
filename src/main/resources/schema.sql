DROP TABLE IF EXISTS profissional_convenio;
DROP TABLE IF EXISTS consulta_agendada;
DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS profissional;
DROP TABLE IF EXISTS convenio;

CREATE TABLE convenio
(
    id    bigint NOT NULL AUTO_INCREMENT,
    cnpj  varchar(255) DEFAULT NULL,
    name  varchar(255) DEFAULT NULL,
    price decimal(12, 2),
    PRIMARY KEY (id)
);

CREATE TABLE paciente
(
    id                bigint NOT NULL AUTO_INCREMENT,
    cpf               varchar(255) DEFAULT NULL,
    full_name         varchar(255) DEFAULT NULL,
    online_status     varchar(255) DEFAULT NULL,
    password          varchar(255) DEFAULT NULL,
    total_appointment int          DEFAULT NULL,
    username          varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE profissional
(
    id        bigint NOT NULL AUTO_INCREMENT,
    crm       varchar(255) DEFAULT NULL,
    full_name varchar(255) DEFAULT NULL,
    status    int          DEFAULT NULL,
    avaliacao    int       DEFAULT NULL,
    PRIMARY KEY (id)
);

alter table paciente
    add convenio_id bigint null;

alter table paciente
    add constraint paciente_convenio_fk
        foreign key (convenio_id) references convenio (id);

CREATE TABLE profissional_convenio
(
    profissional_id bigint NOT NULL,
    convenio_id     bigint NOT NULL,
    PRIMARY KEY (profissional_id, convenio_id),
    foreign key (convenio_id) references convenio (id),
    foreign key (profissional_id) references profissional (id)
);

CREATE TABLE consulta
(
    id bigint NOT NULL AUTO_INCREMENT,
    profissional_id bigint NOT NULL,
    paciente_id     bigint NOT NULL,
    schedule_datetime DATETIME NOT NULL,
    PRIMARY KEY (id),
    foreign key (paciente_id) references paciente (id),
    foreign key (profissional_id) references profissional (id)
);