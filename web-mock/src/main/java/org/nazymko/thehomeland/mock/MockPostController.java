package org.nazymko.thehomeland.mock;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Controller
@RequestMapping("mock")
public class MockPostController {
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String mockPost(@RequestBody HashMap<String, String> object, @RequestHeader HttpHeaders allHeaders) {
        System.out.println("object = [" + object + "], \nallHeaders = [" + allHeaders + "]");
        return "success";
    }

}
