INSERT INTO thehomeland.connector_rules (rule, consumer_id, site_id) VALUES ('{
  "title": "#title",
  "description": "#big-details",
  "type": "ekskursii",
  "price": "#price",
  "targetCity": "Львів",
  "departureCity": "Львів",
  "dateStart": "#trip-date",
  "dateEnd": "#trip-date",
  "departureAddress": "Any",
  "ticketsTotal": "50",
  "image": "$big-image",
  "firstName": "Kraina",
  "lastName": "Ua",
  "phones": "0443606511",
  "url": "%page_url"
}', (SELECT id
     FROM connector_consumer
     WHERE domain = 'http://thehomeland.com.ua'),
                                                                             (SELECT id
                                                                              FROM th_site
                                                                              WHERE authority = 'kraina-ua.com'));