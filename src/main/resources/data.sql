INSERT INTO TASKLIST (NAME) VALUES ('My List');

INSERT INTO TASK (PRIORITY, STATUS, TITLE, TASKLIST_FK)
VALUES
(0, 0, 'Log every operation', 1),
(1, 1, 'Review services components', 1),
(1, 1, 'Make the unit tests', 1),
(2, 1, 'Deploy the application in heroku', 1),
(2, 2, 'Handle every exception properly', 1);