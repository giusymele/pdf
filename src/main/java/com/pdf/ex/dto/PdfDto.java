/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author giuseppina.mele
 */
@Data


public class PdfDto implements Serializable {


    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titolo")
    private String titolo;
    
    @JsonProperty("base64")
    private String base64;

    
    
    
   
}
