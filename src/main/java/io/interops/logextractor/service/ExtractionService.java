package io.interops.logextractor.service;


import io.interops.logextractor.dto.ExtractionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ExtractionService {

    ExtractionResponse extractData(MultipartFile input, Optional<String> proxyId);
}
