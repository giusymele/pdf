/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.Data;


//@EnableAutoConfiguration
@Entity
@Data
@Table(name = "pdf")
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "titolo",nullable = true)
    private String titolo;
    @Column(name = "base64",nullable = true) @Lob
    private String base64;


//    public Pdf(String titolo, String encodeBase64) {
//
//        this.titolo = titolo;
//        this.base64 = encodeBase64;
//    }
//
//    public Pdf(Integer id, String titolo, String encodeBase64, String decodeBase64) {
//        this.id = id;
//        this.titolo = titolo;
//        this.encodeBase64 = encodeBase64;
//        this.dencodeBase64 = decodeBase64;
//    }
//
//    public Pdf() {
//    }

}
