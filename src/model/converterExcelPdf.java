/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.List;

/**
 *
 * @author gbarrosn
 */
public class converterExcelPdf {
     public static void main(String[] args) throws IOException {
        String excelFilePath = "folhas" + File.separator + "Folha de ponto Ligia Elainia.xlsx";
        String pdfFilePath = "folhas" + File.separator + "output.pdf";

        // Cria um novo documento PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        document.open();

        // Cria uma tabela PDF
        PdfPTable table = new PdfPTable(5); // Ajustar o número de colunas conforme necessário
        table.setWidthPercentage(100);

        // Lê o arquivo Excel
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));
        Sheet sheet = workbook.getSheetAt(0);

        // Obtém as regiões mescladas
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();

        // Itera sobre as linhas e colunas da planilha
        for (Row row : sheet) {
            PdfPCell cell;
            for (Cell cellData : row) {
                cell = new PdfPCell(new Phrase(cellData.toString()));

                // Verifica se a célula está em uma região mesclada
                for (CellRangeAddress mergedRegion : mergedRegions) {
                    if (mergedRegion.isInRange(row.getRowNum(), cellData.getColumnIndex())) {
                        cell.setRowspan(mergedRegion.getLastRow() - mergedRegion.getFirstRow() + 1);
                        cell.setColspan(mergedRegion.getLastColumn() - mergedRegion.getFirstColumn() + 1);

                        // Concatena o conteúdo das células mescladas
                        StringBuilder content = new StringBuilder();
                        for (int r = mergedRegion.getFirstRow(); r <= mergedRegion.getLastRow(); r++) {
                            Row currentRow = sheet.getRow(r);
                            if (currentRow != null) {
                                for (int c = mergedRegion.getFirstColumn(); c <= mergedRegion.getLastColumn(); c++) {
                                    Cell currentCell = currentRow.getCell(c);
                                    if (currentCell != null) {
                                        content.append(currentCell.toString()).append(" ");
                                    }
                                }
                            }
                        }
                        cell.setPhrase(new Phrase(content.toString()));
                    }
                }

                table.addCell(cell);
            }
        }

        // Adiciona a tabela ao documento PDF
        try {
            document.add(table);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        document.close();
        workbook.close();
    }

}
