CREATE TABLE attribute_data
(
    site_id INT,
    page_id INT,
    attribute_name VARCHAR(64) NOT NULL,
    attribute_value LONGTEXT,
    attribute_index INT,
    attribute_type VARCHAR(32),
    attribute_format VARCHAR(256),
    rule_id INT
);
CREATE TABLE page
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    url VARCHAR(512),
    site_id INT,
    visited_at DATETIME,
    type VARCHAR(32),
    version INT DEFAULT 0 NOT NULL,
    registered_at DATETIME,
    sourcePage INT DEFAULT -1 NOT NULL
);
CREATE TABLE rule
(
    site VARCHAR(256) NOT NULL,
    rule LONGTEXT NOT NULL,
    version INT DEFAULT 0 NOT NULL,
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);
CREATE TABLE site
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    url VARCHAR(256),
    name VARCHAR(128)
);
CREATE TABLE t_schedule
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    siteId INT,
    start_page VARCHAR(256) NOT NULL,
    page_type VARCHAR(64) NOT NULL,
    start_date DATETIME NOT NULL,
    cron VARCHAR(64),
    is_enabled TINYINT
);
CREATE TABLE t_task
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    schedule_source_id INT NOT NULL,
    start_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
    finish_at TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
    status INT,
    message LONGTEXT,
    canseled TINYINT DEFAULT 0 NOT NULL
);
ALTER TABLE attribute_data ADD FOREIGN KEY (rule_id) REFERENCES rule (id);
ALTER TABLE page ADD FOREIGN KEY (site_id) REFERENCES site (id);
CREATE UNIQUE INDEX rule_id_uindex ON rule (id);
CREATE UNIQUE INDEX site_id_uindex ON site (id);
ALTER TABLE t_task ADD FOREIGN KEY (schedule_source_id) REFERENCES t_schedule (id);
CREATE INDEX t_task_t_schedule_id_fk ON t_task (schedule_source_id);
