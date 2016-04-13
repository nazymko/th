package org.nazymko.thehomeland.mock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Controller
@RequestMapping("mock")
public class MockPostController {
    @ResponseBody
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String mockPost(@RequestBody HashMap<String, String> object) {
        System.out.println("object = [" + object + "]");
        return "success";
    }

}
