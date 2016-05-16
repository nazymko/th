package org.nazymko.thehomeland.parser;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.pojos.ThAttributeData;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Log4j2
public abstract class AbstractConverter implements Converter<ThPageRecord> {

    @Resource
    private AttributeDao attributeDao;
    @Resource
    private SiteDao siteDao;
    @Resource
    private PageDao pageDao;
    private HashMap<Integer, String> cache = new HashMap<>();

    /**
     * Convert database attribute records into HashMap for sending as JSON
     * <p>
     * Used given property configuration <code>HashMap<String, String> rule</code>
     */
    @Override
    public Optional<Map> convert(ThPageRecord record) {

        HashMap<String, ThAttributeData> revertedMap = new HashMap<>();
        List<ThAttributeData> page = attributeDao.getByPage(record.getId());
        HashMap<String, String> result = new HashMap<>();


        for (ThAttributeData thAttributeDataRecord : page) {
            revertedMap.put(thAttributeDataRecord.getAttributeName(), thAttributeDataRecord);
        }
        log.debug("Converting");
        log.debug(record);

        HashMap<String, String> rule = getRule(record.getSiteId());
        if (rule == null) {
            return Optional.empty();
        }
        for (Map.Entry<String, String> entry : rule.entrySet()) {
            String entryValue = entry.getValue();
            if (entryValue.startsWith("#")) {
                putValue(revertedMap, result, entry);
            } else if (entryValue.startsWith("$")) {
                putHostRelatedUrl(revertedMap, result, entryValue,entry.getKey());
            } else if (entryValue.startsWith("%page_url")) {
                result.put(entry.getKey(), record.getPageUrl());
            } else {
                ThAttributeData dataRecord = revertedMap.get(entryValue);
                if (dataRecord != null) {
                    result.put(entryValue, dataRecord.getAttributeValue());
                } else {
                    result.put(entry.getKey(), entryValue);
                }
            }
        }

        return Optional.of(result);
    }

    private void putHostRelatedUrl(HashMap<String, ThAttributeData> revertedMap, HashMap<String, String> result, String entryValue, String key) {
        String _key = entryValue.substring(1);
        ThAttributeData attribute = revertedMap.get(_key);
        if (attribute != null) {
            result.put(key, merge(domain(attribute.getSiteId()), attribute.getAttributeValue()));
        }
    }

    private String merge(String domain, String attributeValue) {
        if (domain != null) {
            return domain + "/" + attributeValue;
        }
        throw new IllegalArgumentException("domain = null!");
    }

    private void putValue(HashMap<String, ThAttributeData> revertedMap, HashMap<String, String> result, Map.Entry<String, String> entry) {
        String _key = entry.getValue().substring(1);
        ThAttributeData thAttributeDataRecord = revertedMap.get(_key);
        if (thAttributeDataRecord != null) {
            result.put(entry.getKey(), thAttributeDataRecord.getAttributeValue());
        }
    }

    //Simple caching , there are no time-to live option
    private String domain(Integer siteId) {
        if (cache.containsKey(siteId)) {
            return cache.get(siteId);
        }
        Optional<ThSiteRecord> byId = siteDao.getById(siteId);
        if (byId.isPresent()) {
            cache.put(siteId, byId.get().getDefaultUrl());
        } else {
            cache.put(siteId, null);
        }

        return cache.get(siteId);
    }

}
