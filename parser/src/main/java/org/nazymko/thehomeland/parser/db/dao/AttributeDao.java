package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.th.parser.autodao.tables.pojos.ThAttributeData;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;

import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ThAttributeData.TH_ATTRIBUTE_DATA;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class AttributeDao extends AbstractDao<Integer, ThAttributeDataRecord> {

    public List<ThAttributeData> getByPage(Integer key) {
        return getDslContext().selectFrom(TH_ATTRIBUTE_DATA).where(TH_ATTRIBUTE_DATA.PAGE_ID.eq(key)).fetchInto(ThAttributeData.class);
    }

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