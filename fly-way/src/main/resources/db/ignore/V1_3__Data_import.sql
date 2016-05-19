INSERT INTO thehomeland.connector_consumer (domain, time) VALUES ('http://thehomeland.com.ua', '2016-04-10 22:16:43');

INSERT INTO thehomeland.connector_rules
(rule, consumer_id, site_id)
VALUES ('{
  "title": "#article-title",
  "description": "#big-body",
  "type": "#category",
  "price": "#price",
  "targetCity": "Вінниця",
  "departureCity": "Вінниця",
  "dateStart": "#date",
  "dateEnd": "#date",
  "departureAddress": "Вінниця",
  "ticketsTotal": "50",
  "image": "$article-img",
  "firstName": "#firstName",
  "lastName": "#lastName",
  "phones": "#phone",
  "url": "%page_url"
}', (SELECT id
     FROM connector_consumer c
     WHERE c.domain = 'http://thehomeland.com.ua'),
        (SELECT id
         FROM th_site
         WHERE authority = 'tcb.vn.ua'));

INSERT INTO thehomeland.connectors_send_headers (consumer_id, header, value) VALUES ((SELECT id
                                                                                      FROM connector_consumer c
                                                                                      WHERE c.domain =
                                                                                            'http://thehomeland.com.ua'),
                                                                                     'content-type',
                                                                                     'application/json;charset=UTF-8');
INSERT INTO thehomeland.connectors_send_headers (consumer_id, header, value)
VALUES ((SELECT id
         FROM connector_consumer c
         WHERE c.domain =
               'http://thehomeland.com.ua'), 'Authorization', 'Basic T1BHTkotOUd6NC04TVVjVEFRZ1g5UXhBNXdkb0JyMEk6');
INSERT INTO thehomeland.connectors_send_headers (consumer_id, header, value)
VALUES ((SELECT id
         FROM connector_consumer c
         WHERE c.domain =
               'http://thehomeland.com.ua'), 'consumer_url', 'https://thehomeland.com.ua/api/trip/create');