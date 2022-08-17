package com.smile.and.pay.ebb.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "marchands_products")
@XmlRootElement
public class MarchandProduct implements Serializable {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;
}
