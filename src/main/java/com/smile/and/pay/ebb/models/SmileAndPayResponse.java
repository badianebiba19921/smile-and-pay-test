package com.smile.and.pay.ebb.models;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement
public class SmileAndPayResponse implements Serializable {

    int codeRetour;
    String messageRetour;

    public SmileAndPayResponse setCodeRetour(int codeRetour) {
        this.codeRetour = codeRetour;
        return this;
    }

    public SmileAndPayResponse setMessageRetour(String messageRetour) {
        this.messageRetour = messageRetour;
        return this;
    }
}
