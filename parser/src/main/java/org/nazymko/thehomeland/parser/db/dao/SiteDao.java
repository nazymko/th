package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;

import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ThSite.TH_SITE;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SiteDao extends AbstractDao<Integer, ThSiteRecord> {

    @Override
    public Integer save(ThSiteRecord site) {
        store(site);
        return site.getId();
    }

    @Override
    public Optional<ThSiteRecord> getById(Integer key) {
        ThSiteRecord siteRecord = getDslContext().selectFrom(TH_SITE).where(TH_SITE.ID.eq(key)).fetchOne();
        return Optional.of(siteRecord);
    }

    public Integer getIdByUrl(String authority) {
        ThSiteRecord siteRecord = getDslContext().selectFrom(TH_SITE).where(TH_SITE.AUTHORITY.eq(authority)).fetchOne();
        if (siteRecord != null) {
            return siteRecord.getId();
        }
        return -1;
    }

    public Optional<ThSiteRecord> getByUrl(String authority) {
        ThSiteRecord siteRecord = getDslContext().selectFrom(TH_SITE).where(TH_SITE.AUTHORITY.eq(authority)).fetchOne();
        return Optional.ofNullable(siteRecord);
    }

    public List<ThSiteRecord> getList(int pageSize, int page) {
        return getDslContext().selectFrom(TH_SITE).limit(page * pageSize, pageSize).fetch();
    }


    public List<ThSiteRecord> getAll() {
        return getDslContext().selectFrom(TH_SITE).fetch();
    }

}
