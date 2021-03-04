package io.interops.logextractor.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExtractionResponse {

    private List<Extraction> extractions=new ArrayList<>();
}
