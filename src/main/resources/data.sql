INSERT INTO TASKLIST (NAME) VALUES ('My List');

INSERT INTO TASK (CREATED, DUE, PRIORITY, STATUS, TITLE, TASKLIST_FK)
VALUES
('2019-08-21 15:00:00','2019-09-21 15:00:00',0, 0, 'Log every operation', 1),
('2019-06-21 15:00:00','2019-07-21 15:00:00',1, 1, 'Review services components', 1),
('2019-02-11 15:00:00','2019-02-27 15:00:00',1, 1, 'Make the unit tests', 1),
('2019-01-21 15:00:00','2019-02-21 15:00:00',2, 1, 'Deploy the application in heroku', 1),
('2019-05-21 15:00:00','2019-06-21 15:00:00',2, 1, 'Handle every exception properly', 1);


INSERT INTO TASKLIST (NAME) VALUES ('Another List');

INSERT INTO TASK (CREATED, DUE, PRIORITY, STATUS, TITLE, TASKLIST_FK)
VALUES
('2019-08-21 15:00:00','2019-09-21 15:00:00',0, 0, 'task 1', 2),
('2019-06-21 15:00:00','2019-07-21 15:00:00',1, 1, 'task 2', 2),
('2019-05-21 15:00:00','2019-06-21 15:00:00',1, 1, 'task 3', 2),
('2019-04-21 15:00:00','2019-05-21 15:00:00',2, 1, 'task 4', 2),
('2019-05-21 15:00:00','2019-06-21 15:00:00',2, 1, 'task 5', 2);