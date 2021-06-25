package reis.pedro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reis.pedro.service.LoadTestService;

@RestController
public class LoadTestController {

    @GetMapping(value = "/cpuUsage/{value}", produces = "application/json")
    public void returnVersion(@PathVariable(value = "value") int qtd) {
        LoadTestService.cpuEater(qtd);
    }

    @GetMapping(value = "/memoryUsage")
    public void memoryEater() {
        LoadTestService.memoryEater();
    }
}
