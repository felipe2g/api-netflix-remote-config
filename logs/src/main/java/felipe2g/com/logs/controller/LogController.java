package felipe2g.com.logs.controller;

import felipe2g.com.logs.models.dtos.LogDTO;
import felipe2g.com.logs.models.dtos.PostDTO;
import felipe2g.com.logs.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {

    @Autowired
    private LogService service;

    @GetMapping
    public ResponseEntity<List<LogDTO<PostDTO>>> findAll(){
        return  service.findAll();
    }
}