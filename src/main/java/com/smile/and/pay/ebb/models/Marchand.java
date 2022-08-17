package com.smile.and.pay.ebb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marchands")
public class Marchand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date create_date;
    private String name;
    private String lastname;
    private Date birthdate;

    @OneToMany( targetEntity = Address.class, mappedBy="marchand" )
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JoinTable( name = "marchands_products",
            joinColumns = @JoinColumn( name = "id_marchand" ),
            inverseJoinColumns = @JoinColumn( name = "id_product" ) )
    private List<Product> products = new ArrayList<>();
}
