/*
package org.nazymko.thehomeland.parser;


import com.google.gson.Gson;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.rule.JsonRule;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

*/
/**
 * Created by nazymko
 *//*

public class Gen {

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36";


    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {


        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword("0000");
        dataSource.setUser("root");
        dataSource.setDatabaseName("thehomeland");


        SiteDao siteDao = new SiteDao(dataSource);
        RuleDao ruleDao = new RuleDao(dataSource);
        PageDao pageDao = new PageDao(dataSource);
        AttributeDao attributeDao = new AttributeDao(dataSource);


        Gson gson = new Gson();
        JsonRule jsonRule = gson.fromJson(new FileReader("rule_haker.json"), JsonRule.class);

        ruleDao.setSiteDao(siteDao);
        ruleDao.save(jsonRule);

        THLParserRunner parser = THLParserRunner.getInstance();

        parser.setSiteDao(siteDao);
        parser.setRuleDao(ruleDao);
        parser.setPageDao(pageDao);
        parser.setAttributeDao(attributeDao);

        parser.init();

        parser.setPageDao(pageDao);
        parser.create("][akep", "https://xakep.ru/");

    }
}
*/
