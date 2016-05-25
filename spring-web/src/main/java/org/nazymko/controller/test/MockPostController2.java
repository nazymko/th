package org.nazymko.controller.test;

import org.nazymko.controller.test.dto.TripDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Controller("someNewMock")
@RequestMapping("mock2")
public class MockPostController2 {
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String mockPost(@RequestBody TripDto object, @RequestHeader HttpHeaders allHeaders) {
        System.out.println("Mock consumer = [" + object + "], \nallHeaders = [" + allHeaders + "]");
        return "success";
    }

}
