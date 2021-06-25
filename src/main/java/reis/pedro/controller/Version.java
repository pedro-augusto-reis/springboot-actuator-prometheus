package reis.pedro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class Version {

    @GetMapping(produces = "application/json")
    public String returnVersion(){
        return "1.0.0";
    }

}
