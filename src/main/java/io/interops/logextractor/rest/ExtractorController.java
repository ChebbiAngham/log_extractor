package io.interops.logextractor.rest;


import io.interops.logextractor.dto.ExtractionResponse;
import io.interops.logextractor.service.ExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class ExtractorController {

    private ExtractionService extractionService;

    @Autowired
    public ExtractorController(ExtractionService extractionService) {
        this.extractionService = extractionService;
    }

    @PostMapping(path="/scan",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ExtractionResponse extraction (@RequestPart("logFile") MultipartFile logFile,@RequestPart(value = "proxyId",required = false) Optional<String> proxyId){
        if(logFile.isEmpty()){
            throw new IllegalArgumentException("file is Empty");
        }
        return extractionService.extractData(logFile,proxyId);

    }


}
