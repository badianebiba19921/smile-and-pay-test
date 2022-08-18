package com.smile.and.pay.ebb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
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

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Address.class, mappedBy="marchand" )
    @JsonIgnoreProperties("marchand")
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "marchands_products",
            joinColumns = @JoinColumn( name = "id_marchand" ),
            inverseJoinColumns = @JoinColumn( name = "id_product" ) )
    @JsonIgnoreProperties("marchands")
    private List<Product> products = new ArrayList<>();
}
