CREATE TABLE thehomeland.connectors_send_headers
(
  id     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  header VARCHAR(128),
  value  VARCHAR(1024)
);

CREATE UNIQUE INDEX connectors_send_headers_id_uindex ON thehomeland.connectors_send_headers (id);
ALTER TABLE thehomeland.connectors_send_headers
  ADD consumer_id INT NULL;
ALTER TABLE thehomeland.connectors_send_headers
  ADD CONSTRAINT connectors_send_headers_connector_consumer_id_fk
FOREIGN KEY (consumer_id) REFERENCES connector_consumer (id);