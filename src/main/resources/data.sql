INSERT INTO authority(name) VALUES('ROLE_USER');

INSERT INTO POLL (CREATED_AT, DELETED_AT, ID, UPDATED_AT, A_HASHTAG, A_NAME, B_HASHTAG, B_NAME, A_QUESTION, B_QUESTION, TITLE,A_COUNT,B_COUNT)
VALUES
    ('2024-01-03 12:00:00', NULL, 1, '2024-01-03 12:00:00', '#TagA1', 'NameA1', '#TagB1', 'NameB1', 'QuestionA', 'QuestionB', 'Sample Poll 1',0,0),
    ('2024-01-03 12:30:00', NULL, 2, '2024-01-03 12:30:00', '#TagA2', 'NameA2', '#TagB2', 'NameB2', 'QuestionA'|| CHR(10) ||'문자열 슬라이드'|| CHR(10) ||'테스트용', 'QuestionY', 'Sample Poll 2',0,0)