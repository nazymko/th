CREATE TABLE rule (
  site    VARCHAR(256) NOT NULL,
  rule    LONGTEXT     NOT NULL,
  version INT(11)      NOT NULL DEFAULT '0',
  id      INT(11) PRIMARY KEY   AUTO_INCREMENT
);
CREATE UNIQUE INDEX rule_id_uindex ON RULE (id ASC);

CREATE TABLE SITE (
  id   INT(11) PRIMARY KEY AUTO_INCREMENT,
  url  VARCHAR(256),
  name VARCHAR(128)
);


CREATE TABLE ATTRIBUTE_DATA (
  site_id          INT(11),
  page_id          INT(11),
  attribute_name   VARCHAR(64) NOT NULL,
  attribute_value  LONGTEXT,
  attribute_index  INT(11),
  attribute_type   VARCHAR(32),
  attribute_format VARCHAR(256),
  rule_id          INT(11),
  FOREIGN KEY (rule_id) REFERENCES th_rule (id)
);
CREATE TABLE PAGE (
  id            INT(11) PRIMARY KEY AUTO_INCREMENT,
  url           VARCHAR(512),
  site_id       INT(11),
  visited_at    DATETIME,
  type          VARCHAR(32),
  version       INT(11) NOT NULL    DEFAULT '0',
  registered_at DATETIME,
  sourcePage    INT(11) NOT NULL    DEFAULT '-1',
  FOREIGN KEY (site_id) REFERENCES th_site (id)
);


CREATE UNIQUE INDEX site_id_uindex ON SITE (id ASC);

CREATE TABLE T_SCHEDULE (
  id         INT(11) PRIMARY KEY AUTO_INCREMENT,
  siteId     INT(11),
  start_page VARCHAR(256) NOT NULL,
  page_type  VARCHAR(64)  NOT NULL,
  start_date DATETIME     NOT NULL,
  period     VARCHAR(64)
);
CREATE TABLE T_TASK (
  id                 INT(11) PRIMARY KEY AUTO_INCREMENT,
  schedule_source_id INT(11)   NOT NULL,
  start_at           TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00',
  finish_at          TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00',
  status             INT(11),
  message            LONGTEXT
);