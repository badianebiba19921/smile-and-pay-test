package com.smile.and.pay.ebb.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
//@Table(name = "marchands_products")
@XmlRootElement
public class MarchandProduct implements Serializable {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;
}
