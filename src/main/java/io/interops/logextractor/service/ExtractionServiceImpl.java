package io.interops.logextractor.service;

import io.interops.logextractor.dto.Extraction;
import io.interops.logextractor.dto.ExtractionResponse;
import io.interops.logextractor.dto.Type;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExtractionServiceImpl implements ExtractionService {
    @Override
    public ExtractionResponse extractData(@NonNull MultipartFile input, @Nullable Optional<String> proxyId) {
        ExtractionResponse extractionResponse = new ExtractionResponse();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(input.getInputStream()))) {
            String line;
            final String regex = "^(.{15}) ([^\\s]+) ([^\\s]+) (\\[[^]]+]){5} (\\[[^]]+])(\\[[^]]+])? (.*)";
            Pattern pattern = Pattern.compile(regex);
            while ((line = br.readLine()) != null) {
                {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches() && line.contains(proxyId.orElseGet(()->""))) {
                        Extraction extraction = Extraction.builder()
                                .date(matcher.group(1))
                                .proxyId(matcher.group(3))
                                .type(matcher.group(5).contains("in") ? Type.IN : Type.OUT)
                                .soapMessage(matcher.group(7))
                                .build();
                        extractionResponse.getExtractions().add(extraction);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractionResponse;
    }
}
