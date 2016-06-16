INSERT INTO thehomeland.th_site (authority, name, default_url)
VALUES ('interesniy.kiev.ua', 'Interesniy Kiev', 'http://interesniy.kiev.ua');

INSERT INTO thehomeland.th_rule (rule, version, status, authority) VALUES ('{
  "meta": {
    "languages": [
      "eng"
    ],
    "type": [
      "excursion",
      "bus_travel"
    ]
  },
  "name": "Interesniy Kiev",
  "selector": "css",
  "page": [
    {
      "unique": false,
      "type": "front_page",
      "attrs": [
        {
          "path": "a.excursions_calendar",
          "persist": false,
          "attr": "href",
          "type": "excursion_list"
        }
      ]
    },
    {
      "unique": false,
      "type": "excursion_list",
      "attrs": [
        {
          "path": "a.cal_item_link",
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
          "path": ".singleTourPost h1",
          "persist": true,
          "attr": "text",
          "type": "article-title"
        },
        {
          "path": ".placeGallery img",
          "persist": true,
          "attr": "src",
          "type": "article-img"
        },
        {
          "path": ".singleTourBox",
          "persist": true,
          "attr": "html",
          "type": "body"
        },
        {
          "path": "span.active",
          "persist": true,
          "attr": "date",
          "type": "date",
          "format": "yyyy-MM-dd"
        },
        {
          "path": ".regularTourItem a[href~\\u003d/guide/]",
          "regexp": [
            {
              "expression": "(.*)\\\\s(.*)",
              "type": "name",
              "group": [
                {
                  "nature": "string",
                  "persist": true,
                  "type": "first-name",
                  "order": 1
                },
                {
                  "nature": "string",
                  "persist": true,
                  "type": "last-name",
                  "order": 2
                }
              ],
              "persist": false
            }
          ],
          "persist": false,
          "attr": "text",
          "type": "guide-name-full"
        },
        {
          "path": ".regularTourPrice",
          "regexp": [
            {
              "expression": "(\\\\d+)\\\\s(.*)/.*",
              "type": "contact-phone",
              "group": [
                {
                  "nature": "string",
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
  "url": "http://interesniy.kiev.ua"
}', 1, 0, 'interesniy.kiev.ua');

INSERT INTO thehomeland.connector_rules (rule, consumer_id, site_id) VALUES ('{
  "title": "#article-title",
  "description": "#body",
  "type": "Екскурсії",
  "price": "#price",
  "targetCity": "Київ",
  "departureCity": "Київ",
  "dateStart": "#date",
  "dateEnd": "#date",
  "departureAddress": "Київ",
  "ticketsTotal": "50",
  "image": "$article-img",
  "firstName": "Интересный",
  "lastName": "Киев",
  "phones": "380443645111",
  "url": "%page_url"
}', (SELECT id
       FROM connector_consumer
       WHERE domain =
             'http://thehomeland.com.ua'), (SELECT id
                                            FROM th_site
                                            WHERE authority = 'interesniy.kiev.ua'));