SET FOREIGN_KEY_CHECKS=0;
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
    sourcePage INT DEFAULT -1 NOT NULL,
    task_run_id INT DEFAULT -1 NOT NULL
);
CREATE TABLE rule
(
    site VARCHAR(256) NOT NULL,
    rule LONGTEXT NOT NULL,
    version INT DEFAULT 0 NOT NULL,
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    status INT
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
    start_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    cron VARCHAR(64),
    is_enabled TINYINT
);
CREATE TABLE t_task
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    start_at DATETIME NOT NULL,
    schedule_source_id INT,
    status INT,
    message LONGTEXT,
    is_enabled TINYINT,
    finish_at DATETIME,
    run_type INT NOT NULL,
    site_id INT NOT NULL
);
ALTER TABLE th_attribute_data ADD FOREIGN KEY (rule_id) REFERENCES th_rule (id);
ALTER TABLE th_page ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
CREATE UNIQUE INDEX rule_id_uindex ON rule (id);
CREATE UNIQUE INDEX site_id_uindex ON site (id);
ALTER TABLE task_run ADD FOREIGN KEY (schedule_source_id) REFERENCES task_schedule (id);

SET FOREIGN_KEY_CHECKS=1;
