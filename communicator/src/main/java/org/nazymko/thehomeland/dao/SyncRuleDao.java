package org.nazymko.thehomeland.dao;

import org.nazymko.th.parser.autodao.tables.records.ConnectorRulesRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;

import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.Tables.CONNECTOR_RULES;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncRuleDao extends AbstractDao<Integer, ConnectorRulesRecord> {
    public Integer save(ConnectorRulesRecord obj) {
        store(obj);
        return obj.getId();
    }

    @Override public Optional<ConnectorRulesRecord> getById(Integer key) {
        return Optional.ofNullable(getDslContext().selectFrom(CONNECTOR_RULES).where(CONNECTOR_RULES.ID.eq(key)).fetchOne());
    }

    public List<ConnectorRulesRecord> all() {
        return getDslContext().selectFrom(CONNECTOR_RULES).fetch();
    }

}
