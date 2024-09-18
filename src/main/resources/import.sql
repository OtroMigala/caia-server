--Roles
INSERT INTO roles (id, role, created_at) VALUES (1, 'ORGANIZER', NOW());
INSERT INTO roles (id, role, created_at) VALUES (2, 'AUTHOR', NOW());
INSERT INTO roles (id, role, created_at) VALUES (3, 'REVIEWER', NOW());
-----------------

--Permissions
----Organizer permissions
INSERT INTO permissions (permission, role_id, created_at) VALUES ('MUTATE_CONFERENCES', 1, NOW());
INSERT INTO permissions (permission, role_id, created_at) VALUES ('READ_PAPERS', 1, NOW());
INSERT INTO permissions (permission, role_id, created_at) VALUES ('READ_AUTHORS', 1, NOW());
INSERT INTO permissions (permission, role_id, created_at) VALUES ('READ_REVIEWERS', 1, NOW());

----Author permissions
INSERT INTO permissions (permission, role_id, created_at) VALUES ('MUTATE_PAPERS', 2, NOW());
INSERT INTO permissions (permission, role_id, created_at) VALUES ('READ_PAPERS', 2, NOW());

----Reviewers permissions
INSERT INTO permissions (permission, role_id, created_at) VALUES ('READ_AUTHORS', 3, NOW());
INSERT INTO permissions (permission, role_id, created_at) VALUES ('CUALIFY_PAPERS', 3, NOW());
INSERT INTO permissions (permission, role_id, created_at) VALUES ('READ_PAPERS', 3, NOW());
------------------

--Users-----------
INSERT INTO users (first_name, last_name, email, affiliation, password, token, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES ('John', 'Doe', 'cristian@email.com', 'Some Affiliation', '$2a$10$5jNphEWJKhYK.kEgaTgpju6/WVrlekyxNgBoOwp.F4/EMSDoj05aa', NULL, TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (first_name, last_name, email, affiliation, password, token, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES ('Alice', 'Smith', 'alice@email.com', 'Some Affiliation', '$2a$10$5jNphEWJKhYK.kEgaTgpju6/WVrlekyxNgBoOwp.F4/EMSDoj05aa', NULL, TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (first_name, last_name, email, affiliation, password, token, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES ('Bob', 'Johnson', 'bob@email.com', 'Some Affiliation', '$2a$10$5jNphEWJKhYK.kEgaTgpju6/WVrlekyxNgBoOwp.F4/EMSDoj05aa', NULL, TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (first_name, last_name, email, affiliation, password, token, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES ('Charlie', 'Brown', 'charlie@email.com', 'Some Affiliation', '$2a$10$5jNphEWJKhYK.kEgaTgpju6/WVrlekyxNgBoOwp.F4/EMSDoj05aa', NULL, TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (first_name, last_name, email, affiliation, password, token, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES ('David', 'Wilson', 'david@email.com', 'Some Affiliation', '$2a$10$5jNphEWJKhYK.kEgaTgpju6/WVrlekyxNgBoOwp.F4/EMSDoj05aa', NULL, TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (first_name, last_name, email, affiliation, password, token, is_enabled, account_no_expired, account_no_locked, credentials_no_expired) VALUES ('Eve', 'Davis', 'eve@email.com', 'Some Affiliation', '$2a$10$5jNphEWJKhYK.kEgaTgpju6/WVrlekyxNgBoOwp.F4/EMSDoj05aa', NULL, TRUE, TRUE, TRUE, TRUE);
-------------------

--Conferences
INSERT INTO conferences (id, name, description, slug, created_at) VALUES (1, 'Conference 1', 'Description 1', 'conference-1', NOW());
INSERT INTO conferences (id, name, description, slug, created_at) VALUES (2, 'Conference 2', 'Description 2', 'conference-2', NOW());
INSERT INTO conferences (id, name, description, slug, created_at) VALUES (3, 'Conference 3', 'Description 3', 'conference-3', NOW());
INSERT INTO conferences (id, name, description, slug, created_at) VALUES (4, 'Conference 4', 'Description 4', 'conference-4', NOW());
INSERT INTO conferences (id, name, description, slug, created_at) VALUES (5, 'Conference 5', 'Description 5', 'conference-5', NOW());
-------------------

--Members
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (1, 1, 1, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (1, 2, 1, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (1, 3, 2, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (1, 3, 3, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (2, 3, 2, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (2, 4, 3, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (2, 5, 2, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (3, 5, 1, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (3, 4, 1, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (3, 1, 3, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (4, 1, 2, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (4, 5, 1, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (5, 1, 3, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (5, 2, 3, NOW());
INSERT INTO members (user_id, conference_id, role_id, created_at) VALUES (5, 4, 3, NOW());
-----------------