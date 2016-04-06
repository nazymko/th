package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;

import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ThAttributeData.TH_ATTRIBUTE_DATA;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class AttributeDao extends AbstractDao<Integer, ThAttributeDataRecord> {

    public List<ThAttributeDataRecord> getByPage(Integer key) {
        Result<ThAttributeDataRecord> fetch = getDslContext().selectFrom(TH_ATTRIBUTE_DATA).where(TH_ATTRIBUTE_DATA.PAGE_ID.eq(key)).fetch();
        return fetch;
    }

  /*  public List<Attribute> getByPage(Integer key) {
        List<Attribute> attributes = getJdbcTemplate().query("SELECT * FROM th_attribute_data WHERE page_id=:pageId", new MapSqlParameterSource("pageId", key), new RowMapper<Attribute>() {
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
                        .attrTag(attribute_type)
                        .attrFormat(attribute_format)
                        .build();
            }
        });

        return attributes;

    }*/


//    public Optional<Attribute> getAttrsOnPage(Integer key) {
//        throw new NotImplementedException();
//    }

//    //    @Override
//    public Integer save(Attribute attr) {
//        MapSqlParameterSource paramSource = new MapSqlParameterSource();
//
//        paramSource.addValue("site_id", attr.getSiteId());
//        paramSource.addValue("page_id", attr.getPageId());
//        paramSource.addValue("attribute_name", attr.getAttrMeaning());
//        paramSource.addValue("attribute_value", attr.getAttrValue());
//        paramSource.addValue("attribute_index", attr.getAttrIndex());
//        paramSource.addValue("attribute_type", attr.getAttrTag());
//        paramSource.addValue("attribute_format", attr.getAttrFormat());
//        paramSource.addValue("rule_id", attr.getRuleId());
//
//        getJdbcTemplate().update("INSERT INTO th_attribute_data(site_id, page_id, attribute_name, attribute_value, attribute_index, attribute_type, attribute_format, rule_id) VALUES( :site_id, :page_id, :attribute_name, :attribute_value, :attribute_index, :attribute_type, :attribute_format, :rule_id)", paramSource);
//        return null;
//    }

    @Override
    public Integer save(ThAttributeDataRecord obj) {
        store(obj);
        return obj.getId();
    }

    @Override
    public Optional<ThAttributeDataRecord> getById(Integer key) {
        ThAttributeDataRecord thAttributeDataRecord = getDslContext()
                .selectFrom(TH_ATTRIBUTE_DATA)
                .where(TH_ATTRIBUTE_DATA.ID.eq(key))
                .fetchOne();
        return Optional.ofNullable(thAttributeDataRecord);
    }
}