package com.smile.and.pay.ebb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@XmlRootElement
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date create_date;
    private String label;
    private double unit_price;
    private String currency;
    private double weight;
    private double height;
    @ManyToMany
    @JoinTable( name = "marchands_products",
            joinColumns = @JoinColumn( name = "id_product" ),
            inverseJoinColumns = @JoinColumn( name = "id_marchand" ) )
    private List<Marchand> marchands = new ArrayList<>();
}
