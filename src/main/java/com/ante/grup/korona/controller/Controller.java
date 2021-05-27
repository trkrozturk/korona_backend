package com.ante.grup.korona.controller;

import com.ante.grup.korona.model.KoronaData;
import com.ante.grup.korona.process.ParagraphProcess;
import com.ante.grup.korona.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class Controller {
    private SimpMessagingTemplate template;

    private DataService dataService;

    @Autowired
    public void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    public ResponseEntity<List<KoronaData>> getAll() {
        List<KoronaData> allResult = dataService.findAll();
        return new ResponseEntity<>(allResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/newRecord", produces = {MediaType.TEXT_PLAIN_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<String> newRecord(@RequestBody String inputString) {

        if (inputString.isEmpty()) {
            return new ResponseEntity<>("Request Body is Empty", HttpStatus.BAD_REQUEST);
        }

        ParagraphProcess paragraphProcess = new ParagraphProcess();
        KoronaData koronaDataObj = paragraphProcess.getKoronaDataObj(inputString);
        if (koronaDataObj == null){
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        if (dataService.findByTarihAndAndSehir(koronaDataObj.getTarih(),koronaDataObj.getSehir()).isPresent())
            return new ResponseEntity<>("Aynı Tarihte, Aynı Şehirde Kayıt Bulunmaktadır", HttpStatus.BAD_REQUEST);
        dataService.save(koronaDataObj);
        List<KoronaData> allResult = dataService.findAll();
        template.convertAndSend("/topic/message", allResult);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
