/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pdf.ex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pdf.ex.entity.Pdf;
//@Repository
public interface PdfRepository extends JpaRepository<Pdf,Integer>{
    

   // Pdf findMyId(Integer id);
    
}
