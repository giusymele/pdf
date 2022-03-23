/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.ex.dto.NewPdfDto;
import com.pdf.ex.dto.PdfDto;
import com.pdf.ex.entity.Pdf;
import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Component;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class GeneratePdfReport {

    @Autowired
    Base64Util base64;

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public String createReport(NewPdfDto newPdfDto) throws FileNotFoundException {

        String filePath = "C:\\Users\\giuseppina.mele\\Documents\\" + newPdfDto.getTitolo() + ".pdf";
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(newPdfDto.getNumeroColonne());
            //table.setWidthPercentage(600);
            //table.setWidths(new int[]{1, 3, 3, 3,3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            Paragraph p = new Paragraph(newPdfDto.getTitolo(), headFont);
            p.setSpacingAfter(20);
            p.setAlignment(1); // Center


            for (Object item : newPdfDto.getLista()) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase((String) item));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

            }

            PdfWriter write = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(p);
            document.add(table);

            document.close();

            if (!new File(filePath).isFile()) {
                filePath = "";
            }

//       JOptionPane.showMessageDialog(null, "PDF creado correctamente");
        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
            filePath = "";
            return filePath;
        } catch (IOException io) {/*Failed to close*/
            filePath = "";
            return filePath;
        }

        return filePath;
    }

    public Pdf creoPdfEntity(NewPdfDto newPdfDto) throws FileNotFoundException, IOException {

        String pathPdf = createReport(newPdfDto);

        Pdf pdfEntity = new Pdf();
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

            pdfEntity.setTitolo(newPdfDto.getTitolo());
            pdfEntity.setBase64(codifica);

        }

        return pdfEntity;
    }
}