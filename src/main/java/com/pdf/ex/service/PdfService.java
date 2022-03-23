/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.service;

import com.pdf.ex.dto.NewPdfDto;
import com.pdf.ex.dto.PdfDto;
import com.pdf.ex.repository.PdfRepository;
import com.pdf.ex.util.Base64Util;
import com.pdf.ex.util.GeneratePdfReport;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pdf.ex.entity.Pdf;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giuseppina.mele
 */
@Service
public class PdfService {

    @Autowired
    GeneratePdfReport generatePdf;

    @Autowired
    Base64Util base64;

    @Autowired
    PdfRepository pdfRepository;

    public ResponseEntity<?> creoPdf(NewPdfDto newPdfDto) throws FileNotFoundException, IOException {

        String pathPdf = generatePdf.createReport(newPdfDto);

        PdfDto pdfDto = new PdfDto();

        if (!pathPdf.isEmpty()) {

            String cod = "";

            File filePdf = new File(pathPdf);

            InputStream stream = new FileInputStream(filePdf);

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;

            while ((line = br.readLine()) != null) {
                cod += br.readLine();
            }

            String codifica = base64.encode(cod);

            Pdf pdfEntity = new Pdf();

            pdfEntity.setTitolo(newPdfDto.getTitolo());
            pdfEntity.setBase64(codifica);
            pdfEntity = pdfRepository.save(pdfEntity);

            pdfDto.setTitolo(newPdfDto.getTitolo());
            pdfDto.setId(pdfEntity.getId());
            pdfDto.setBase64(codifica);
            return ResponseEntity//

                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(pdfDto);
        } else {
            return ResponseEntity//

                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("errore");
        }

    }

    public ResponseEntity<?> getpdfBase64ById(Integer id) {

        if (pdfRepository.findById(id).isPresent()) {
            Optional<Pdf> pdfEntity = pdfRepository.findById(id);

            Pdf pdf = pdfEntity.get();
            PdfDto pdfDto = new PdfDto();

            pdfDto.setId(pdf.getId());
            pdfDto.setTitolo(pdf.getTitolo());
            pdfDto.setBase64(pdf.getBase64());

            return ResponseEntity//
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(pdfDto);
        } else {
            return ResponseEntity//
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("pdf non trovato");
        }

    }

    public ResponseEntity getAllPdf() {

        List<PdfDto> listaPdfDto = new ArrayList();

        List<Pdf> listaPdf = pdfRepository.findAll();

        if (!listaPdf.isEmpty()) {

            for (Pdf item : listaPdf) {

                PdfDto _pdfDto = new PdfDto();
                _pdfDto.setId(item.getId());
                _pdfDto.setTitolo(item.getTitolo());
                _pdfDto.setBase64(item.getBase64());

                listaPdfDto.add(_pdfDto);
            }

            return ResponseEntity//
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(listaPdfDto);
        } else {
            return ResponseEntity//
                    .status(HttpStatus.NOT_FOUND)
                    .body("pdf non trovati");
        }

    }

    public ResponseEntity deletePdfById(int id) {

        if (pdfRepository.findById(id).isPresent()) {

            pdfRepository.deleteById(id);

            return ResponseEntity//
                    .status(HttpStatus.OK)
                    .build();

        } else {
            return ResponseEntity//
                    .status(HttpStatus.NOT_FOUND)
                    .body("pdf non trovato");
        }

    }

    public ResponseEntity updatePdf(NewPdfDto modifiedPdfDto, int id) throws IOException {

        Pdf pdfEntity = generatePdf.creoPdfEntity(modifiedPdfDto);

        //controllo se l'id esiste
        if (pdfRepository.findById(id).isPresent()) {
            //se è presente lo modifico 
            pdfEntity.setId(id);
            //salvo l'entità 
            pdfRepository.save(pdfEntity);

            return ResponseEntity//
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("il pdf è stato modificato");

        } else {
            //salvo l'entità 
            pdfEntity = pdfRepository.save(pdfEntity);

            PdfDto pdfDto = new PdfDto();

            pdfDto.setId(pdfEntity.getId());
            pdfDto.setTitolo(pdfEntity.getTitolo());
            pdfDto.setBase64(pdfEntity.getBase64());

            return ResponseEntity//
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(pdfDto);

        }

    }

    public ResponseEntity<?> partialUpdatePdf(NewPdfDto modifiedPdfDto, int id) throws IOException {
        Optional<Pdf> pdfEntity = pdfRepository.findById(id);
        Pdf pdf =new Pdf();

        if (!pdfEntity.isPresent()) {

            return ResponseEntity//
                    .status(HttpStatus.NOT_FOUND)
                    .body("pdf non trovato");

        } else {

             pdf=pdfEntity.get();

            //modifico il valore 
            pdf.setTitolo(modifiedPdfDto.getTitolo());

            if (modifiedPdfDto.getLista() == null || modifiedPdfDto.getNumeroColonne() == null) {

                pdf.setBase64(null);
               

            }
            
            else if(modifiedPdfDto.getLista() != null & modifiedPdfDto.getNumeroColonne() != null){
            
                pdf=generatePdf.creoPdfEntity(modifiedPdfDto);
                
                pdf.setTitolo(modifiedPdfDto.getTitolo());
                        
            } 
                
        }
       return ResponseEntity//
                        .status(HttpStatus.OK)
                        .body(pdf);

    }

}
