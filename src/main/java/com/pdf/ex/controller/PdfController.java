/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.controller;

import com.pdf.ex.dto.NewPdfDto;
import com.pdf.ex.dto.PdfDto;
import com.pdf.ex.service.PdfService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author giuseppina.mele
 */
@RequestMapping("/")
@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;

    @PostMapping("/pdf")
    public ResponseEntity<?> createpdf(@RequestBody NewPdfDto newPdfDto) {

        try {
            return pdfService.creoPdf(newPdfDto);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity//

                .status(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
                .header(HttpHeaders.CONTENT_TYPE)
                .body("errore");
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> getPdfBase64ById(@PathVariable int id) {
        return pdfService.getpdfBase64ById(id);

    }

    @GetMapping("/pdf")
    public ResponseEntity getAllPdf() {
        return pdfService.getAllPdf();
    }

    @DeleteMapping("/pdf/{id}")

    public ResponseEntity<?> deletePdfById(@PathVariable int id) {

        return pdfService.deletePdfById(id);
    }

    @PutMapping("/pdf/{id}")
    public ResponseEntity<?> updatePdf(@RequestBody PdfDto pdfDto){
    
        return pdfService.updatePdf(pdfDto);
    }
    
}
