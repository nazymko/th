package org.nazymko.thehomeland.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.validator.routines.UrlValidator;
import org.nazymko.th.parser.autodao.tables.pojos.ThAttributeData;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;

import javax.annotation.Resource;
import java.net.URL;
import java.util.*;

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

        HashMap<String, List<ThAttributeData>> mapping = new HashMap<>();
        List<ThAttributeData> page = attributeDao.getByPage(record.getId());
        HashMap<String, Object> result = new HashMap<>();


        for (ThAttributeData thAttributeDataRecord : page) {
            String name = thAttributeDataRecord.getAttributeName();
            List<ThAttributeData> data = mapping.get(name);
            if (data == null) {
                data = new ArrayList<>();
                mapping.put(name, data);
            }
            data.add(thAttributeDataRecord);
        }
        log.debug("Start converting");
        log.debug(record);

        HashMap<String, String> rule = getRule(record.getSiteId());
        if (rule == null) {
            return Optional.empty();
        }
        for (Map.Entry<String, String> keyPair : rule.entrySet()) {
            String value = keyPair.getValue();
            if (value.startsWith("#")
                    || value.startsWith("$")
                    || value.startsWith("%")) {
                continue;
            } else {
                List<ThAttributeData> thAttributeDatas = mapping.get(keyPair.getKey());
                if (thAttributeDatas == null) {
                    thAttributeDatas = new ArrayList<>();
                    mapping.put(keyPair.getKey(), thAttributeDatas);
                }
                //TODO:fucking dirty code;
                ThAttributeData attr = new ThAttributeData();
                attr.setAttributeName(keyPair.getKey());
                attr.setAttributeValue(keyPair.getValue());
                thAttributeDatas.add(attr);
            }

        }
        for (Map.Entry<String, String> entry : rule.entrySet()) {
            String entryValue = entry.getValue();

            if (entryValue.startsWith("#")) {
                if ("phones".equals(entry.getKey())) {
                    result.put("phones", valuesAsList(mapping.get(entry.getValue().substring(1))));
                } else {
                    putValue(mapping, result, entry);
                }
            } else if (entryValue.startsWith("$")) {
                putHostRelatedUrl(mapping, result, entryValue, entry.getKey());

            } else if (entryValue.startsWith("%page_url")) {
                result.put(entry.getKey(), record.getPageUrl());

            } else {
                List<ThAttributeData> dataRecord = mapping.get(entry.getKey());

                if (dataRecord != null && dataRecord.size() > 0) {
                    if ("phones".equals(entry.getKey())) {
                        result.put("phones", valuesAsList(dataRecord));
                    } else {
                        result.put(entry.getKey(), dataRecord.get(0).getAttributeValue());
                    }
                } else {
                    result.put(entry.getKey(), entryValue);
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(result);
            log.debug("Message:{}", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.debug("Finish converting");
        return Optional.of(result);
    }

    private Object valuesAsList(List<ThAttributeData> dataRecords) {
        ArrayList<Object> list = new ArrayList<>();
        for (ThAttributeData attribute : dataRecords) {
            list.add(attribute.getAttributeValue().trim());
        }
        return list;
    }


    private void putHostRelatedUrl(HashMap<String, List<ThAttributeData>> revertedMap,
                                   HashMap<String, Object> result,
                                   String entryValue,
                                   String key) {
        String _key = entryValue.substring(1);
        List<ThAttributeData> attributes = revertedMap.get(_key);

        if (attributes != null && attributes.size() > 0) {
            result.put(key, merge(domain(attributes.get(0).getSiteId()), attributes.get(0).getAttributeValue()));
        }
    }

    private String merge(String domain, String attributeValue) {
        if (!(attributeValue.startsWith("http") || attributeValue.startsWith("www.")) && domain != null) {
            return domain + "/" + attributeValue;
        } else if (domain == null) {
            throw new IllegalArgumentException("domain = null!");
        }
        return attributeValue;
    }

    private void putValue(HashMap<String, List<ThAttributeData>> mapping, HashMap<String, Object> result, Map.Entry<String, String> entry) {
        String _key = entry.getValue().substring(1);

        List<ThAttributeData> attributes = mapping.get(_key);

        if (attributes != null && attributes.size() > 0) {
            result.put(entry.getKey(), attributes.get(0).getAttributeValue());
        } else {
            result.put(entry.getKey(), String.format("Attribute '%s' Not Found", _key));
            return;
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
