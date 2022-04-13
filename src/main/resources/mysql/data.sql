insert into accounts (user_name, pass_word) values ('user1', '1111');
insert into accounts (user_name, pass_word) values ('user2', '2222');

insert into users (account_id, email_address) values (1, 'user1@gmail.com');
insert into users (account_id, email_address) values (2, 'user2@gmail.com');

--insert into questions (creator_id, title, description, create_time) values (1, 'SMU', 'Where is SMU?', '2022-4-11');
--insert into questions (creator_id, title, description, create_time) values (1, 'Dallas', 'What is the population of Dallas?', '2022-4-11');

--insert into answers (question_id, answerer_id, description, answer_time) values (1, 2, 'Southern Methodist University, Dallas, TX 75205','2022-4-11');

