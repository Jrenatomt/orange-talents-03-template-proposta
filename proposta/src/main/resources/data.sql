INSERT INTO PROPOSTA (id, documento, email, endereco, criado_em, nome, salario, status) 
VALUES (1, '36192211035', 'maria@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477438', 'Maria do Teste Silva', '1800', 'NAO_ELEGIVEL');

INSERT INTO PROPOSTA (id, documento, email, endereco, criado_em, nome, salario, status) 
VALUES (2,'67783457053', 'carlos@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477448', 'Carlos do Teste Silva', '2800', 'ELEGIVEL');

INSERT INTO PROPOSTA (id, documento, email, endereco, criado_em, nome, salario, status) 
VALUES (3,'11211887707', 'email@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477478', 'renato do Teste Silva', '2900', 'ELEGIVEL');

INSERT INTO CARTAO(id, numero_cartao, emitido_em, titular, limite, proposta_id, status) 
VALUES (1,'5209-1622-1164-6666','2021-04-22T13:22:08.08929', 'Carlos do Teste Silva', '3511', 2, 'BLOQUEADO');

INSERT INTO CARTAO(id, numero_cartao, emitido_em, titular, limite, proposta_id, status) 
VALUES (2,'5209-1622-1164-1234','2021-04-22T13:22:08.04929', 'renato do Teste Silva', '3511', 3, 'ATIVO');









