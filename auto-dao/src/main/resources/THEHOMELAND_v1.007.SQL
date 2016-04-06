SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE connector_consumer
(
    id INT PRIMARY KEY NOT NULL,
    domain VARCHAR(1024) NOT NULL,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TABLE connector_sync_main_log
(
    id INT PRIMARY KEY NOT NULL,
    consumer_id INT,
    sync_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    count_new INT NOT NULL,
    count_total INT NOT NULL,
    latest_page_id BIGINT
);
CREATE TABLE connector_sync_page_log
(
    id INT PRIMARY KEY NOT NULL,
    page_id INT NOT NULL,
    consumer VARCHAR(128) NOT NULL,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE TABLE task_run
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
CREATE TABLE task_schedule
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    site_id INT,
    start_page VARCHAR(256) NOT NULL,
    page_type VARCHAR(64) NOT NULL,
    start_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    cron VARCHAR(64),
    is_enabled TINYINT
);
CREATE TABLE th_attribute_data
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
CREATE TABLE th_page
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    authority VARCHAR(512),
    site_id INT,
    visited_at DATETIME,
    type VARCHAR(32),
    version INT DEFAULT 0 NOT NULL,
    registered_at DATETIME,
    sourcePage INT DEFAULT -1 NOT NULL,
    task_run_id INT DEFAULT -1 NOT NULL,
    page_url VARCHAR(1024) NOT NULL
);
CREATE TABLE th_rule
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    rule LONGTEXT NOT NULL,
    version INT DEFAULT 0 NOT NULL,
    status INT,
    authority LONGTEXT NOT NULL
);
CREATE TABLE th_site
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    authority VARCHAR(256),
    name VARCHAR(128)
);
CREATE UNIQUE INDEX unique_id ON connector_consumer (id);
ALTER TABLE connector_sync_main_log ADD FOREIGN KEY (consumer_id) REFERENCES connector_consumer (id);
ALTER TABLE connector_sync_page_log ADD FOREIGN KEY (page_id) REFERENCES th_page (id);
ALTER TABLE task_run ADD FOREIGN KEY (schedule_source_id) REFERENCES task_schedule (id);
ALTER TABLE task_run ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
ALTER TABLE task_schedule ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
ALTER TABLE th_attribute_data ADD FOREIGN KEY (rule_id) REFERENCES th_rule (id);
ALTER TABLE th_attribute_data ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
ALTER TABLE th_attribute_data ADD FOREIGN KEY (page_id) REFERENCES th_page (id);
ALTER TABLE th_page ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
ALTER TABLE th_page ADD FOREIGN KEY (task_run_id) REFERENCES task_run (id);

SET FOREIGN_KEY_CHECKS = 1;
