package org.nazymko.thehomeland.parser.db;

import com.google.gson.Gson;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.Test;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.FileReader;

/**
 * Created by nazymko.patronus@gmail.com.
 */

public class RuleDaoSaveTest {

    @Test
    public void testSave() throws Exception {
        MysqlDataSource dataSource = DaoFactory.getMysqlDataSource();

        RuleDao ruleDao = new RuleDao();

        SiteDao siteDao = new SiteDao();
        siteDao.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));


        Gson gson = new Gson();

        JsonRule jsonRule = gson.fromJson(new FileReader("rule_haker_test.json"), JsonRule.class);
        ruleDao.save(jsonRule);


    }


}