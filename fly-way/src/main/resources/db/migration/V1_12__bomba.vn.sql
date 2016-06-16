INSERT INTO thehomeland.th_site (authority, name, default_url) VALUES ('bomba.vn.ua', 'TK Bomba', 'http://bomba.vn.ua');


INSERT INTO thehomeland.th_rule (rule, version, status, authority) VALUES ('{
  "meta": {
    "languages": [
      "ua"
    ],
    "type": [
      "excursion",
      "bus_travel"
    ]
  },
  "name": "TK Bomba",
  "selector": "css",
  "page": [
    {
      "unique": false,
      "type": "front_page",
      "attrs": [
        {
          "path": "#block-menu-menu-main-menu a[href^\\u003d/tour]",
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
          "path": ".field-content a",
          "persist": false,
          "attr": "href",
          "type": "article"
        }
      ]
    },
    {
      "unique": true,
      "type": "article",
      "attrs": [
        {
          "path": ".tour-title",
          "persist": true,
          "attr": "text",
          "type": "title"
        },
        {
          "path": ".body",
          "persist": true,
          "attr": "html",
          "type": "body"
        },
        {
          "path": ".field-slideshow-image",
          "persist": true,
          "attr": "src",
          "type": "img"
        },
        {
          "path": ".tour-sidebar-inner .price",
          "regexp": [
            {
              "expression": "(\\\\d+)\\\\s(.*)",
              "type": "price",
              "group": [
                {
                  "nature": "number",
                  "persist": true,
                  "type": "price",
                  "order": 1
                },
                {
                  "nature": "string",
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
          "type": "price-block"
        }
      ]
    }
  ],
  "url": "http://bomba.vn.ua"
}', 1, 0, 'bomba.vn.ua');

INSERT INTO thehomeland.connector_rules (rule, consumer_id, site_id) VALUES ('{
  "title": "#title",
  "description": "#body",
  "type": "Автобусні",
  "price": "#price",
  "targetCity": "#targetCity",
  "departureCity": "Київ",
  "dateStart": "2016-06-01 00:01:01",
  "dateEnd": "2016-09-01 00:01:01",
  "departureAddress": "Вінниця",
  "ticketsTotal": "50",
  "image": "$img",
  "firstName": "Турклуб",
  "lastName": "Бомба",
  "phones": "380979080279",
  "url": "%page_url"
}', (SELECT id
     FROM connector_consumer
     WHERE domain =
           'http://thehomeland.com.ua'), (SELECT id
                                          FROM th_site
                                          WHERE authority = 'bomba.vn.ua'));
