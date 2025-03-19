package com.kvinltf.productionfindingbackend.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Currency}
 * Contains all the fields from the Currency entity for data transfer purposes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto implements Serializable {
    private Long id;
    private Integer version;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private String name;
    private String code;
    private String symbol;
    private Integer decimalPlaces;
}
