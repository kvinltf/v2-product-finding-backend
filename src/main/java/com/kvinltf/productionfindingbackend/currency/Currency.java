package com.kvinltf.productionfindingbackend.currency;

import com.kvinltf.productionfindingbackend.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "currencies")
@Getter
@Setter
@ToString
public class Currency extends BaseEntity {
    private String name;
    private int decimal;
}
