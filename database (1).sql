use eventmanagement;
CREATE TABLE User(
    user_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    age INT ,
    phone INT ,
    email VARCHAR(100)
);

CREATE TABLE Event(
    event_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    manager_id INT UNSIGNED NOT NULL,
    private_event TINYINT(1) NOT NULL,
    description VARCHAR(100) ,
    start_time DATETIME NOT NULL,
    end_time DATETIME ,
    location VARCHAR(50) NOT NULL,
    link_app_event VARCHAR(100) ,
    num_attendee INT ,
    num_org INT 
);

CREATE TABLE Organisers(
    organiser_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED NOT NULL,
    event_id INT NOT NULL,
    org_name VARCHAR(30) ,
    job_id INT UNSIGNED NOT NULL
);

CREATE TABLE Job(
    job_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    job_title VARCHAR(50) ,
    event_id INT UNSIGNED,
    job_rent decimal(8, 2) 
);

CREATE TABLE Tickets(
    event_id INT UNSIGNED NOT NULL,
    attendee_id INT UNSIGNED NOT NULL ,
    edit_by_org_id INT ,
    max_num_ticket INT ,
    min_num_ticket INT ,
    description VARCHAR(100) ,
    title VARCHAR(100) ,
    fee decimal(8, 2) NULL,
    PRIMARY KEY(event_id)
);

CREATE TABLE Attendee(
    attendee_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED NOT NULL,
    event_id INT NOT NULL,
    organiser_id INT UNSIGNED,
    att_name VARCHAR(30) ,
    att_job VARCHAR(50) ,
    priority INT 
);

CREATE TABLE Cost(
    event_id INT UNSIGNED NOT NULL PRIMARY KEY,
    fee_ticket decimal(8, 2) ,
    total_rent_human decimal(8, 2) ,
    rent_room decimal(8, 2) ,
    total_rent_equipment decimal(8, 2) ,
    total decimal(8, 2) NOT NULL
);

ALTER TABLE Event ADD CONSTRAINT event_manager_id_foreign 
FOREIGN KEY(manager_id) REFERENCES organisers(organiser_id);

ALTER TABLE Attendee ADD CONSTRAINT attendee_user_id_foreign
FOREIGN KEY(user_id) REFERENCES user(user_id);

ALTER TABLE tickets ADD CONSTRAINT tickets_attendee_id_foreign 
FOREIGN KEY(attendee_id) REFERENCES attendee(attendee_id);

ALTER TABLE organisers ADD CONSTRAINT organisers_user_id_foreign 
FOREIGN KEY(user_id) REFERENCES user(user_id);

ALTER TABLE job ADD CONSTRAINT job_event_id_foreign 
FOREIGN KEY(event_id) REFERENCES event(event_id);

ALTER TABLE tickets ADD CONSTRAINT tickets_event_id_foreign 
FOREIGN KEY(event_id) REFERENCES event(event_id);

ALTER TABLE job ADD CONSTRAINT job_event_id_foreign_1
FOREIGN KEY(event_id) REFERENCES cost(event_id);

ALTER TABLE attendee ADD CONSTRAINT attendee_organiser_id_foreign 
FOREIGN KEY(organiser_id) REFERENCES organisers(organiser_id);

ALTER TABLE organisers ADD CONSTRAINT organisers_job_id_foreign 
FOREIGN KEY(job_id) REFERENCES job(job_id);

ALTER TABLE tickets ADD CONSTRAINT tickets_event_id_foreign_1
FOREIGN KEY(event_id) REFERENCES cost(event_id);