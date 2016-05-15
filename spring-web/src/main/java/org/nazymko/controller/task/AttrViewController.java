package org.nazymko.controller.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.nazymko.th.parser.autodao.tables.pojos.ThAttributeData;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Controller
@RequestMapping("task")
public class AttrViewController {
    @Resource
    AttributeDao attributeDao;

    @Resource
    PageDao pageDao;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @RequestMapping("attrs/{pageId}")
    public String pageAttrs(Model model, @PathVariable("pageId") Integer pageId) {
        Optional<ThPageRecord> byId = pageDao.getById(pageId);
        if (byId.isPresent()) {

            List<ThAttributeData> attributes = attributeDao.getByPage(pageId);

//            HashMap<String,String> attributeMap=convertToMap(attributes)
            model.addAttribute("page", byId.get());
            model.addAttribute("attrs", attributes);
            model.addAttribute("gson", gson);
        }

        return "task/attrs";
    }
}
