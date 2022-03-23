/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author giuseppina.mele
 */
@Data
public class NewPdfDto implements Serializable {
    

    @JsonProperty("titolo")
    private String titolo= null;
    //private int numeroRighe;
    @JsonProperty("numeroColonne")
    private Integer numeroColonne=null;
    @JsonProperty("lista")
    private List<String> lista=null;

}
