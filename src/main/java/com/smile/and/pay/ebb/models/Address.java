package com.smile.and.pay.ebb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@XmlRootElement
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int number;
    private String street;
    private String zip_code;
    @ManyToOne(cascade = {CascadeType.ALL,CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn( name="id_marchand" )
    private Marchand marchand;
}
