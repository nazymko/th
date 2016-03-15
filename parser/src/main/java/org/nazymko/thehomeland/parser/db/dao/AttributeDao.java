package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.thehomeland.parser.db.model.Attribute;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class AttributeDao extends AbstractDao<Integer, Attribute> {
    public Optional<Attribute> get(Integer key) {
        throw new NotImplementedException();
    }


    public List<Attribute> getByPage(Integer key) {
        List<Attribute> attributes = getJdbcTemplate().query("SELECT * FROM attribute_data WHERE page_id=:pageId", new MapSqlParameterSource("pageId", key), new RowMapper<Attribute>() {
            @Override
            public Attribute mapRow(ResultSet resultSet, int i) throws SQLException {

                int site_id = resultSet.getInt("site_id");
                int page_id = resultSet.getInt("page_id");
                int rule_id = resultSet.getInt("rule_id");
                int attribute_index = resultSet.getInt("attribute_index");
                String attribute_meaning = resultSet.getString("attribute_name");
                String attribute_value = resultSet.getString("attribute_value");
                String attribute_type = resultSet.getString("attribute_type");
                String attribute_format = resultSet.getString("attribute_format");

                return Attribute.builder()
                        .siteId(site_id)
                        .pageId(page_id)
                        .ruleId(rule_id)
                        .attrIndex(attribute_index)
                        .attrMeaning(attribute_meaning)
                        .attrValue(attribute_value)
                        .attrType(attribute_type)
                        .attrFormat(attribute_format)
                        .build();
            }
        });

        return attributes;

    }


    public Optional<Attribute> getAttrsOnPage(Integer key) {
        throw new NotImplementedException();
    }

    @Override
    public Integer save(Attribute attr) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();

        paramSource.addValue("site_id", attr.getSiteId());
        paramSource.addValue("page_id", attr.getPageId());
        paramSource.addValue("attribute_name", attr.getAttrMeaning());
        paramSource.addValue("attribute_value", attr.getAttrValue());
        paramSource.addValue("attribute_index", attr.getAttrIndex());
        paramSource.addValue("attribute_type", attr.getAttrType());
        paramSource.addValue("attribute_format", attr.getAttrFormat());
        paramSource.addValue("rule_id", attr.getRuleId());

        getJdbcTemplate().update("INSERT INTO attribute_data(site_id, page_id, attribute_name, attribute_value, attribute_index, attribute_type, attribute_format, rule_id) VALUES( :site_id, :page_id, :attribute_name, :attribute_value, :attribute_index, :attribute_type, :attribute_format, :rule_id)", paramSource);
        return null;
    }
}