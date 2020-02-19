INSERT INTO USER (id, name, last_name, email) VALUES (11111111, 'Tomasz', 'Gol', 'tomggg1986@gmail.com');
INSERT INTO TASK (id, owner) VALUES (33333333, 11111111);
INSERT INTO NOTE (id, name, text, owner, task) VALUES (22222222, 'pranie', 'zrób pranie', 11111111, 33333333);
INSERT INTO TASK_PARTICIPANTS (TASK_ID, PARTICIPANTS_ID) VALUES (33333333, 11111111);
INSERT INTO SCHEDULER_TASK (id, name, crone, text, task) VALUES (111, 'TASK_1', '0 0/2 0 0', 'message 1', 33333333);
INSERT INTO SCHEDULER_TASK (id, name, crone, text,  task) VALUES (222, 'TASK_2', '0 50 0 0', 'message 2', 33333333);