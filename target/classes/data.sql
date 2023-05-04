INSERT INTO events (eventName, ticketCost) VALUES
    ('Sydney Animal Shelter Dog Wash', 10.0),
	('Chanie Wenjack Fund Summer Concert', 14.99), 
	('Women''s Shelter Craft Fair', 5.0),
	('The Arquives Live Exhibit', 20.0);
	
INSERT INTO transactions (event, numTickets, contact) VALUES 
    (1, 2, 'arti@cat.com'),
    (4, 4, 'wsj@me.com'),
    (1, 4, 'wsj@me.com'),
    (3, 7, 'ptoms@foo.com'),
    (1, 1, 'wendi@cat.com');
    
INSERT INTO users (username, encryptedpassword, enabled)
VALUES ('foofoo', '$2a$10$OpARYXO2pG2fqEU8H77A/eY5fZuWCaLUuVD.u37ArgpgC7YYCJIIS', 1);
INSERT INTO users (username, encryptedpassword, enabled)
VALUES ('barbar', '$2a$10$yXffyCCwmKEO74Tok1eiRehnkrkjqerlFdYNLUjwwaRHm5xOub1P.', 1);

INSERT INTO roles (rolename)
VALUES ('ROLE_MANAGER');
INSERT INTO roles (rolename)
VALUES ('ROLE_GUEST');

INSERT INTO user_role (userid, roleid)
VALUES (1, 1); 
INSERT INTO user_role (userid, roleid)
VALUES (1, 2);
INSERT INTO user_role (userid, roleid)
VALUES (2, 2);