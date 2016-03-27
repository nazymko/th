package org.nazymko.thehomeland.parser.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.model.Page;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class PageDaoTest {
    PageDao pageDao;

    @Before
    public void setUp() throws Exception {
        MysqlDataSource mysqlDataSource = DaoFactory.getMysqlDataSource();

        pageDao = new PageDao();

    }

    @Test
    public void testGet() throws Exception {

        Page page = new Page("http://xakep.ru", "http://xakep.ru/some/page/", "article");
        pageDao.save(page);


        Page loadedPage = pageDao.get("http://xakep.ru/some/page/").get();

        Assert.assertThat(loadedPage, IsEqual.equalTo(page));
    }


}