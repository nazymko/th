CREATE TABLE site (
  id   INT(11) NOT NULL AUTO_INCREMENT,
  url  VARCHAR(256),
  name VARCHAR(128),
  PRIMARY KEY (id)
);
CREATE TABLE rule (
  site    VARCHAR(256) NOT NULL,
  rule    LONGTEXT     NOT NULL,
  version INT(11)      NOT NULL DEFAULT '0',
  id      INT(11)      NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
);

CREATE TABLE page (
  id         INT(11) NOT NULL AUTO_INCREMENT,
  url        VARCHAR(512),
  site_id    INT(11),
  visited_at DATETIME,
  type       VARCHAR(32),
  PRIMARY KEY (id),
  FOREIGN KEY (site_id) REFERENCES th_site (id)
);

CREATE TABLE attribute_data (
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


CREATE UNIQUE INDEX rule_id_uindex ON rule (id);


CREATE UNIQUE INDEX site_id_uindex ON site (id);