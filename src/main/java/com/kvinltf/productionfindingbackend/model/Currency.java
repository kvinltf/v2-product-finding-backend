package com.kvinltf.productionfindingbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "currencies")
@Getter
@Setter
@ToString
public class Currency extends BaseEntity {
    private String name;
    private int decimal;
}
