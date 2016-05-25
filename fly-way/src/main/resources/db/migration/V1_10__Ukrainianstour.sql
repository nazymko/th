INSERT INTO thehomeland.th_site (authority, name, default_url)
VALUES ('ukrainiantour.com', 'Ukrainian Tour', 'http://ukrainiantour.com');


INSERT INTO thehomeland.th_rule (rule, version, status, authority)
VALUES ('{
  "meta": {
    "languages": [
      "rus"
    ],
    "type": [
      "excursion",
      "bus_travel"
    ]
  },
  "name": "Ukrainian Tour",
  "selector": "css",
  "page": [
    {
      "unique": false,
      "type": "front_page",
      "attrs": [
        {
          "path": "#navBar .nav .tours a",
          "persist": false,
          "attr": "href",
          "type": "category_list"
        }
      ]
    },
    {
      "unique": false,
      "type": "category_list",
      "attrs": [
        {
          "path": ".item h2 a",
          "persist": false,
          "attr": "href",
          "type": "article"
        }
      ]
    },
    {
      "unique": false,
      "type": "article",
      "attrs": [
        {
          "path": "div.tourName \\u003e h1",
          "persist": true,
          "attr": "text",
          "type": "title"
        },
        {
          "path": ".body.details",
          "persist": true,
          "attr": "html",
          "type": "body"
        },
        {
          "path": ".galery a img",
          "persist": true,
          "attr": "src",
          "type": "image"
        },
        {
          "path": ".breadcrumbs.crumbs div:last-child a span",
          "persist": true,
          "attr": "text",
          "type": "targetCity"
        },
        {
          "path": "div.tourName \\u003e div",
          "regexp": [
            {
              "expression": "(\\\\d+)\\\\s?(.+)",
              "type": "trip-price",
              "group": [
                {
                  "nature": "number",
                  "persist": true,
                  "type": "price",
                  "order": 1
                },
                {
                  "nature": "enum",
                  "persist": true,
                  "type": "currency",
                  "order": 2
                }
              ],
              "persist": false
            }
          ],
          "persist": false,
          "attr": "text",
          "type": "price-container"
        }
      ]
    }
  ],
  "url": "http://ukrainiantour.com"
}', 1, 0, 'ukrainiantour.com');


INSERT INTO thehomeland.connector_rules (rule, consumer_id, site_id) VALUES ('{
  "title": "#title",
  "description": "#body",
  "type": "Екскурсії",
  "price": "#price",
  "targetCity": "#targetCity",
  "departureCity": "Київ",
  "dateStart": "2016-01-01 00:01:01",
  "dateEnd": "2017-01-01 00:01:01",
  "departureAddress": "Any",
  "ticketsTotal": "50",
  "image": "$image",
  "firstName": "Ukrainian",
  "lastName": "Tour",
  "phones": "380443605737",
  "url": "%page_url"
}', (SELECT id
     FROM connector_consumer
     WHERE domain =
           'http://thehomeland.com.ua'), ((SELECT id
                                           FROM th_site
                                           WHERE authority = 'ukrainiantour.com')));