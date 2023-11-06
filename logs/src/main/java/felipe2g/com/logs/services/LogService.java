package felipe2g.com.logs.services;

import felipe2g.com.logs.models.Log;
import felipe2g.com.logs.models.dtos.LogDTO;
import felipe2g.com.logs.models.dtos.PostDTO;
import felipe2g.com.logs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {
    @Autowired
    private LogRepository repository;

    public ResponseEntity<List<LogDTO<PostDTO>>> findAll(){
        var dbLogs = repository.findAll();
        if(dbLogs == null || dbLogs.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var dbLogsDto = dbLogs.stream().map(log -> {
            return new LogDTO<PostDTO>(log);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(dbLogsDto, HttpStatus.OK);
    }

    public ResponseEntity<LogDTO<PostDTO>> save(LogDTO logDTO){
        if(logDTO.getAction().isBlank() || logDTO.getObject() == null)
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var dbLog = repository.save(logDTO.toLog());
        return new ResponseEntity<>(new LogDTO<>(dbLog), HttpStatus.CREATED);
    }

}
