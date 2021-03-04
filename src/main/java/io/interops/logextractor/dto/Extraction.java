package io.interops.logextractor.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Extraction {

    private String date;
    private String proxyId;
    private Type type;
    private String soapMessage;
}
