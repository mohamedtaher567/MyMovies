package mymovies.rest.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    @RequestMapping("/index")
    public String index(Map<String, Object> model) {
		model.put("message", getMessage());
        return "index";
    }

    private String getMessage() {
        return "Hello World";
    }

}
