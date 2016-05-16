INSERT INTO thehomeland.th_site (authority, name, default_url) VALUES ('kraina-ua.com', 'kraina-ua.com', 'http://kraina-ua.com/');
INSERT INTO thehomeland.th_rule (rule, version, status, authority) VALUES ('{
  "meta": {
    "languages": [
      "eng"
    ],
    "topics": [
      "programming"
    ],
    "type": [
      "q\\u0026a",
      "excursion",
      "bus_travel"
    ]
  },
  "name": "TK Bidnyajka",
  "selector": "css",
  "page": [
    {
      "unique": false,
      "type": "front_page",
      "attrs": [
        {
          "path": ".travels_meny .myul li a",
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
          "path": ".tripak \\u003ea",
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
          "path": ".entry td h3",
          "persist": true,
          "attr": "text",
          "type": "article-title"
        },
        {
          "path": ".newcontent img",
          "persist": true,
          "attr": "src",
          "type": "article-img"
        },
        {
          "path": ".newcontent a[href^\\u003dhttps://tcb.vn.ua]",
          "persist": false,
          "attr": "href",
          "type": "slave_page"
        },
        {
          "path": ".newcontent",
          "regexp": [
            {
              "expression": "(\\\\s*\\\\d{10})+",
              "type": "contact-phone",
              "group": [
                {
                  "nature": "string",
                  "persist": true,
                  "type": "phone",
                  "order": 1
                },
                {
                  "nature": "string",
                  "persist": true,
                  "type": "phone",
                  "order": 2
                }
              ],
              "persist": false
            },
            {
              "expression": "Тип поїздки:\\\\s(\\\\S*)\\\\s",
              "type": "category",
              "persist": true
            },
            {
              "expression": "Відповідальний:\\\\s*(.*)\\\\s(.*)\\\\s[Контакты|Контакти]",
              "type": "contact-name",
              "group": [
                {
                  "nature": "string",
                  "persist": true,
                  "type": "firstName",
                  "order": 1
                },
                {
                  "nature": "string",
                  "persist": true,
                  "type": "lastName",
                  "order": 2
                }
              ],
              "persist": false
            }
          ],
          "persist": false,
          "attr": "text",
          "type": "contacts"
        },
        {
          "path": ".inforr",
          "regexp": [
            {
              "expression": ".*Орг\\\\.збір:\\\\s}*(\\\\d*)\\\\s(\\\\S*).*",
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
            },
            {
              "expression": "Дата:\\\\s*((((\\\\d\\\\d)(([02468][048])|([13579][26]))-02-29)|(((\\\\d\\\\d)(\\\\d\\\\d)))-((((0\\\\d)|(1[0-2]))-((0\\\\d)|(1\\\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))\\\\s([01]\\\\d|2[0-3]):([0-5]\\\\d):([0-5]\\\\d))",
              "type": "date",
              "persist": true,
              "format": "YYYY-MM-DD hh:mm:ss"
            }
          ],
          "persist": false,
          "attr": "text",
          "type": "parser#info-block"
        }
      ]
    },
    {
      "parent": "article",
      "unique": false,
      "type": "slave_page",
      "attrs": [
        {
          "path": ".entry",
          "persist": true,
          "attr": "html",
          "type": "big-body"
        }
      ]
    }
  ],
  "url": "http://tcb.vn.ua"
}', 1, 0, 'tcb.vn.ua');
INSERT INTO thehomeland.th_rule (rule, version, status, authority) VALUES ('{
  "meta": {
    "languages": [
      "ua"
    ],
    "topics": [
      "travelling"
    ],
    "type": [
      "excursion",
      "bus_travel"
    ]
  },
  "name": "kraina-ua.com",
  "page": [
    {
      "unique": false,
      "type": "front_page",
      "attrs": [
        {
          "path": "body \\u003e header \\u003e div \\u003e nav \\u003e ul \\u003e li.menu__li.menu__li_aux.menu__li_ic1 \\u003e span \\u003e a",
          "persist": false,
          "attr": "href",
          "type": "calendar"
        }
      ]
    },
    {
      "unique": false,
      "type": "calendar",
      "attrs": [
        {
          "path": "body \\u003e div.container \\u003e div \\u003e div.layout__col-l \\u003e div \\u003e form \\u003e div.tours-cnt \\u003e div.listPagination \\u003e table \\u003e tbody \\u003e tr \\u003e td.next \\u003e a",
          "persist": false,
          "attr": "href",
          "type": "calendar"
        },
        {
          "path": ".tours-lst .tours-lst__col-2 .tours-lst__ac a",
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
          "path": ".item-img \\u003e img",
          "persist": true,
          "attr": "src",
          "type": "big-image"
        },
        {
          "path": ".item-view .item-view__l",
          "persist": true,
          "attr": "html",
          "type": "short-details"
        },
        {
          "path": "#w-tab-1",
          "persist": true,
          "attr": "html",
          "type": "big-details"
        },
        {
          "path": ".date-lst-lbl.date-lst-lbl-1",
          "persist": true,
          "attr": "text",
          "type": "trip-date",
          "format": "MM.dd"
        },
        {
          "path": ".item-view__price",
          "regexp": [
            {
              "expression": "\\\\s?(\\\\d*)\\\\s(.*)",
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
          "type": "trip-price"
        }
      ]
    }
  ],
  "url": "http://kraina-ua.com/"
}', 1, 0, 'kraina-ua.com');