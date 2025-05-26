package com.renta.herramienta.aplication.service;

import java.io.ByteArrayOutputStream;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.renta.herramienta.domain.dto.DetalleFacturaDTO;
import com.renta.herramienta.domain.dto.FacturacionDTO;

public class FacturaPdfGenerator {

    public static ByteArrayOutputStream generarFacturaPdf(FacturacionDTO dto) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
        try {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
    
            // Encabezado
            document.add(new Paragraph("FACTURA #" + dto.getNumeroFactura())
                    .setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Fecha de emisión: " + dto.getFechaEmision()));
            document.add(new Paragraph("Cliente: " + dto.getNombreCliente()));
            document.add(new Paragraph("Proveedor: " + dto.getProveedor().getNombre()));
            document.add(new Paragraph(" "));
    
            
            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 1, 2}))
                    .useAllAvailableWidth();
    
            table.addHeaderCell(new Cell().add(new Paragraph("Herramienta").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Costo por Día").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Días").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Total").setBold()));
    
            for (DetalleFacturaDTO detalle : dto.getDetalles()) {
                table.addCell(detalle.getNombreHerramienta());
                table.addCell("$" + detalle.getCostoPorDia());
                table.addCell(String.valueOf(detalle.getDiasAlquiler()));
                table.addCell(String.valueOf(detalle.getCantidad())); 
                table.addCell("$" + detalle.getTotalPorHerramienta());
            }
    
            document.add(table);
    
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total a pagar: $" + dto.getTotal())
                    .setBold().setTextAlignment(TextAlignment.RIGHT));
    
            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }
    
        return outputStream;
    }
    
}



