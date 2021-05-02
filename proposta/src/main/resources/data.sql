INSERT INTO PROPOSTA (id, documento, Hash_Documento, email, endereco, criado_em, nome, salario, status) 
VALUES (1, '6654666dea87191d741e247a0750d81ed528ebd2eed358c91c385983ec0b52c1', 'f67fd1180950e14d1d5287eb70fde13ecca83444dc98179116de4921a0aa3674',
 'maria@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477438', 'Maria da Silva', '1800', 'NAO_ELEGIVEL');

INSERT INTO PROPOSTA (id, documento, Hash_Documento, email, endereco, criado_em, nome, salario, status) 
VALUES (2, '96c34bb759b5adfa68a07f2750e19df8d19b1c9192a8bfdd4a30c615a6a95696', '0f4ac552a86951028eaaabb839ad6065a305162b8345c8a82b45cbd899031340', 
'carlos@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477448', 'Carlos da Silva', '2800', 'ELEGIVEL');

INSERT INTO PROPOSTA (id, documento, Hash_Documento, email, endereco, criado_em, nome, salario, status) 
VALUES (3, '0b234dd60f8c5d5c95b7c77b5e69db2fa79e0033cff3804e8d5d7e989a18832b', 'c5eeee02ea91d21b30b62593952adac854ed536790cf3dd611b56ca0aa121e47', 
'email@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477478', 'renato da Silva', '2900', 'ELEGIVEL');

INSERT INTO PROPOSTA (id, documento, Hash_Documento, email, endereco, criado_em, nome, salario, status) 
VALUES (4, '2f08228133eb4ca57a9888e2783a6a7352218b50fda310ca5c3379c03238aa2f', '0e5f570fda9bf6a50a6d8707ebaf8080dbac8e1d863f7219368a768275fc30e6', 
'email@email.com', 'Rua indiana, 11', '2021-03-14 15:35:58.477478', 'ana da Silva', '2900', 'ELEGIVEL');

INSERT INTO CARTAO(id, numero_cartao, emitido_em, titular, limite, proposta_id, status) 
VALUES (1,'5209-1622-1164-6666','2021-04-22T13:22:08.08929', 'Carlos da Silva', '3511', 2, 'BLOQUEADO');

INSERT INTO CARTAO(id, numero_cartao, emitido_em, titular, limite, proposta_id, status) 
VALUES (2,'5209-1622-1164-1234','2021-04-22T13:22:08.04929', 'renato da Silva', '3511', 3, 'ATIVO');

INSERT INTO CARTAO(id, numero_cartao, emitido_em, titular, limite, proposta_id, status) 
VALUES (3,'5209-1622-1164-5678','2021-04-22T13:22:08.04929', 'joao da Silva', '3511', 4, 'ATIVO');

INSERT INTO CARTEIRA_DIGITAL(id, ASSOCIADA_EM, CARTEIRA, Email, ID_ASSOCIACAO, CARTAO_ID  ) 
VALUES (1,'2021-04-22T13:22:08.04929', 'PAYPAL', 'jose@email.com', 'ug3t-p7nc-16gr-12lk', 3 );