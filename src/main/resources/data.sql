INSERT INTO convenio (name,cnpj,price) VALUES ('SulAmérica','24.566.426/0001-75',250.90);
INSERT INTO convenio (name,cnpj,price) VALUES ('NorteDame','98.666.426/0001-75',124.90);
INSERT INTO convenio (name,cnpj,price) VALUES ('Bradesco','58.266.826/0001-75',400.00);
INSERT INTO convenio (name,cnpj,price) VALUES ('Prevent Senior','36.886.579/0001-75',450.90);

INSERT INTO paciente (cpf, full_name, online_status, password, total_appointment, username, convenio_id) VALUES ('1111111111', 'Guilherme Meira', '1', '$2a$12$3QN9QJmbEaMEqmnfAHCZweUAT3YypMXJHaHo7xgZbpYV06RVG3W1G', 0, 'gui', 1);

INSERT INTO profissional (crm, full_name, status, avaliacao) VALUES ('1234', 'Bill Gates', 1, 3);

INSERT INTO profissional_convenio (profissional_id, convenio_id) VALUES (1, 1);
INSERT INTO profissional_convenio (profissional_id, convenio_id) VALUES (1, 2);
INSERT INTO profissional_convenio (profissional_id, convenio_id) VALUES (1, 3);


