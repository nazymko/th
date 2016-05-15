SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE connector_consumer
(
  id     INT(11) PRIMARY KEY                   NOT NULL AUTO_INCREMENT,
  domain VARCHAR(1024)                         NOT NULL,
  time   TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL
);
CREATE TABLE connector_rules
(
  id          INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  rule        TEXT,
  consumer_id INT(11),
  site_id     INT(11)
);
CREATE TABLE connector_sync_page_log
(
  id                INT(11) PRIMARY KEY                   NOT NULL AUTO_INCREMENT,
  page_id           INT(11)                               NOT NULL,
  consumer          VARCHAR(128)                          NOT NULL,
  time              TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
  response_text     LONGTEXT,
  response_code     INT(11),
  consumer_endpoint VARCHAR(128)
);
CREATE TABLE connectors_send_headers
(
  id          INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  consumer_id INT(11),
  header      VARCHAR(512)        NOT NULL,
  value       VARCHAR(1024)       NOT NULL
);
CREATE TABLE task_run
(
  id                 INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  start_at           DATETIME            NOT NULL,
  schedule_source_id INT(11),
  status             INT(11),
  message            LONGTEXT,
  is_enabled         TINYINT(4),
  finish_at          DATETIME,
  run_type           INT(11)             NOT NULL,
  site_id            INT(11)             NOT NULL
);
CREATE TABLE task_schedule
(
  id         INT(11) PRIMARY KEY                   NOT NULL AUTO_INCREMENT,
  site_id    INT(11),
  start_page VARCHAR(256)                          NOT NULL,
  page_type  VARCHAR(64)                           NOT NULL,
  start_at   TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
  cron       VARCHAR(64),
  is_enabled TINYINT(4)
);
CREATE TABLE th_attribute_data
(
  id               INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  site_id          INT(11),
  page_id          INT(11),
  attribute_name   VARCHAR(64)         NOT NULL,
  attribute_value  LONGTEXT,
  attribute_index  INT(11),
  attribute_type   VARCHAR(32),
  attribute_format VARCHAR(256),
  rule_id          INT(11)
);
CREATE TABLE th_config
(
  id    INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name  VARCHAR(128)        NOT NULL,
  value LONGTEXT            NOT NULL
);
CREATE TABLE th_page
(
  id            INT(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  page_url      VARCHAR(1024)        NOT NULL,
  version       INT(11) DEFAULT '0'  NOT NULL,
  authority     VARCHAR(512),
  site_id       INT(11),
  visited_at    DATETIME,
  type          VARCHAR(32),
  registered_at DATETIME,
  sourcePage    INT(11) DEFAULT '-1' NOT NULL,
  task_run_id   INT(11) DEFAULT '-1' NOT NULL
);
CREATE TABLE th_rule
(
  id        INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  rule      LONGTEXT            NOT NULL,
  version   INT(11) DEFAULT '0' NOT NULL,
  status    INT(11),
  authority LONGTEXT            NOT NULL
);
CREATE TABLE th_site
(
  id          INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  authority   VARCHAR(256),
  name        VARCHAR(128),
  default_url VARCHAR(1024)       NOT NULL
);
CREATE UNIQUE INDEX unique_id ON connector_consumer (id);
ALTER TABLE connector_rules
  ADD FOREIGN KEY (consumer_id) REFERENCES connector_consumer (id);
ALTER TABLE connector_rules
  ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
CREATE INDEX connector_rules_connector_consumer__fk ON connector_rules (consumer_id);
CREATE UNIQUE INDEX connector_rules_id_uindex ON connector_rules (id);
CREATE INDEX connector_rules_th_site__fk ON connector_rules (site_id);
ALTER TABLE connector_sync_page_log
  ADD FOREIGN KEY (page_id) REFERENCES th_page (id);
CREATE INDEX page_id ON connector_sync_page_log (page_id);
ALTER TABLE connectors_send_headers
  ADD FOREIGN KEY (consumer_id) REFERENCES connector_consumer (id);
CREATE INDEX connectors_send_headers_connector_consumer__fk ON connectors_send_headers (consumer_id);
CREATE UNIQUE INDEX connectors_send_headers_id_uindex ON connectors_send_headers (id);
ALTER TABLE task_run
  ADD FOREIGN KEY (schedule_source_id) REFERENCES task_schedule (id);
ALTER TABLE task_run
  ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
CREATE INDEX schedule_source_id ON task_run (schedule_source_id);
CREATE INDEX site_id ON task_run (site_id);
ALTER TABLE task_schedule
  ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
CREATE INDEX site_id ON task_schedule (site_id);
ALTER TABLE th_attribute_data
  ADD FOREIGN KEY (rule_id) REFERENCES th_rule (id);
ALTER TABLE th_attribute_data
  ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
ALTER TABLE th_attribute_data
  ADD FOREIGN KEY (page_id) REFERENCES th_page (id);
CREATE INDEX page_id ON th_attribute_data (page_id);
CREATE INDEX rule_id ON th_attribute_data (rule_id);
CREATE INDEX site_id ON th_attribute_data (site_id);
CREATE UNIQUE INDEX unique_id ON th_attribute_data (id);
CREATE UNIQUE INDEX th_config_id_uindex ON th_config (id);
CREATE UNIQUE INDEX th_config_name_uindex ON th_config (name);
ALTER TABLE th_page
  ADD FOREIGN KEY (site_id) REFERENCES th_site (id);
ALTER TABLE th_page
  ADD FOREIGN KEY (task_run_id) REFERENCES task_run (id);
CREATE INDEX site_id ON th_page (site_id);
CREATE INDEX task_run_id ON th_page (task_run_id);

SET FOREIGN_KEY_CHECKS = 1;