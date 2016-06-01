package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class PersistenceAttrListener implements AttrListener {
    SimpleDateFormat defaultFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Setter
    @Resource
    private AttributeDao attributeDao;
    public HashMap<Integer, AttrListener> preprocessors = new HashMap<>();

    @Override
    public boolean support(ThAttributeDataRecord attribute, Integer runId, boolean persist) {
        return persist;
    }

    @Override
    public void process(ThAttributeDataRecord attribute, Integer runId) {
        log.debug("saving into db pageId = " + attribute.getPageId());

        preprocessors.keySet()
                .stream()
                .sorted()
                .forEachOrdered(x -> preprocessors.get(x).process(attribute, runId));


        if (attribute.getAttributeFormat() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(attribute.getAttributeFormat());
            try {
                Date date = simpleDateFormat.parse(attribute.getAttributeValue());
                Calendar calendar = Calendar.getInstance();
                if (date.before(calendar.getTime())) {
                    int year = calendar.get(Calendar.YEAR);
                    date.setYear(year - 1900);
                }
                attribute.setAttributeValue(defaultFormatter.format(date));

            } catch (ParseException e) {
                e.printStackTrace();//OK , ignore this shit
            }
        }
        attributeDao.save(attribute);

    }

    @Override
    public void addPreprocessor(Integer order, AttrListener listener) {
        preprocessors.put(order, listener);
    }
}
