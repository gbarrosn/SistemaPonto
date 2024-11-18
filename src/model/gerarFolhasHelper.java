/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import db.dadosFuncionario;

import org.apache.poi.ss.usermodel.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author gbarrosn
 */
public class gerarFolhasHelper {

    public static void gerarFolhaPontoTerceirizado(registroMensal registro, File path) throws FileNotFoundException, IOException {
        
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream("Tabela ponto terceirizados.xlsx"))) {
            
            Sheet sheet = workbook.getSheetAt(0);

            // Criar imagem do código de barras e inserir na planilha
            createBarcode(String.valueOf(registro.getFuncionario().getIdFuncionario()), registro.getFuncionario().getCodigoDeBarras());
            FileInputStream barcode = new FileInputStream("." + File.separator + "barcodes" + File.separator + String.valueOf(registro.getFuncionario().getIdFuncionario()) + ".png");
            byte[] bytes = IOUtils.toByteArray(barcode);
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            barcode.close();

            CreationHelper helper = workbook.getCreationHelper();
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(6);
            anchor.setCol2(8);

            anchor.setRow1(0);
            anchor.setRow2(1);


            anchor.setDy1(Units.toEMU(2)); // Set the top offset to 2 millimeters
            anchor.setDy2(Units.toEMU(2)); // Set the bottom offset to 2 millimeters
            Picture pict = drawing.createPicture(anchor, pictureIdx);

            pict.resize(1, 0.7);
            

            // Preencher cabeçalho

            // Nome
            Row nome = sheet.getRow(1);
            nome.getCell(0).setCellValue("Nome: " + registro.getFuncionario().getNome()); // funciona

            // Matrícula
            nome.getCell(6).setCellValue("Nº Registro: " + String.valueOf(registro.getFuncionario().getMatricula())); //funciona

            // Lotação
            Row lotacao = sheet.getRow(2);
            lotacao.getCell(0).setCellValue(registro.getFuncionario().getContrato());

            // mes atual
            lotacao.getCell(6).setCellValue("Mês: " + numeroMesToNome(registro.getRegistros().get(0).getMes()) + " de " + registro.getRegistros().get(0).getData().split("/")[2]); // funciona

            // Função
            Row funcao = sheet.getRow(3);
            funcao.getCell(0).setCellValue(registro.getFuncionario().getFuncao());

            if (registro.getFuncionario().getFuncao().equals("Atendente") && registro.getFuncionario().getVinculo().equals("Terceirizado")) {
                funcao.getCell(0).setCellValue("APOIO ADMINISTRATIVO");
            }
            

            // Data de admissão
            funcao.getCell(6).setCellValue("Data de admissão: " + registro.getFuncionario().getDataAdmissao()); // funbciona

            // Escala
            Row escala = sheet.getRow(4);
            escala.getCell(0).setCellValue("Escala: " + registro.getFuncionario().getEscala());
            
            // Carga horária
            escala.getCell(6).setCellValue("Carga horária: " + registro.getFuncionario().getHorasSemanais() + "h semanais."); // funciona
            

            //criando uma lista das datas do mês
            List<String> datas = new ArrayList<String>();
            int dia = 1;
            while (dia <= 31) {
                String data = "";
                String diaStr = dia < 10 ? "0" + String.valueOf(dia) : String.valueOf(dia);
                String mesStr = registro.getRegistros().get(0).getMes() < 10 ? "0" + String.valueOf(registro.getRegistros().get(0).getMes()) : String.valueOf(registro.getRegistros().get(0).getMes());
                data = diaStr + "/" + mesStr + "/" + String.valueOf(registro.getRegistros().get(0).getData().split("/")[2]);
                dia += 1;
                datas.add(data);
            }

            // preenchendo o cabeçalho com periodo de apuração
            Row cabeçalho = sheet.getRow(0);
            Cell cell = cabeçalho.getCell(0);

            CellStyle styleTitulo = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 18);
            styleTitulo.setFont(font);
            styleTitulo.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(styleTitulo);
            styleTitulo.setWrapText(true);

            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            Font font2 = workbook.createFont();
            font2.setBold(true);
            style.setFont(font2);
            style.setWrapText(true);

            cell.setCellValue("Folha de Ponto\n Período de apuração\n" + datas.get(0) + " a " + datas.get(datas.size()-1));

            int indexQuebraLinha = cell.getStringCellValue().indexOf("\n");

            RichTextString richText = cell.getRichStringCellValue();
            richText.applyFont(0, indexQuebraLinha, font);
            richText.applyFont(indexQuebraLinha, richText.length(), font2);

            //preenchendo a tabela de ponto
            int linha = 7;
            for (String data : datas) {
                Row linhaDia = sheet.getRow(linha);

                for (registro r : registro.getRegistros()) {
                    if (r.getData().equals(data)) {
                        linhaDia.getCell(2).setCellValue(r.getHoraEntrada());
                        linhaDia.getCell(3).setCellValue(r.getSaidaAlmoco());
                        linhaDia.getCell(4).setCellValue(r.getRetornoAlmoco());
                        linhaDia.getCell(5).setCellValue(r.getHoraSaida());

                        if (r.isAtestado()) {
                            linhaDia.getCell(6).setCellValue("Atestado.");
                            linhaDia.getCell(7).setCellValue("Sim");
                            
                        }
                        if (r.getSaidaAntecipada() != null) {
                            linhaDia.getCell(6).setCellValue(r.getSaidaAntecipada());
                        }
                        if (r.getAlteracao() != null) {
                            linhaDia.getCell(6).setCellValue(r.getAlteracao());
                        }
                        
                        continue;
                    }
                }
                linhaDia.getCell(0).setCellValue(data);
                linhaDia.getCell(1).setCellValue(registro.getRegistros().get(0).dataToDia(data));
                linha += 1;
            }


            try {
                //preenchendo assinatura
                assinatura assinatura = registro.getAssinatura();

                String assinaturaStr = "Assinado por " + registro.getFuncionario().getNome() + " em " + assinatura.getDataAssinatura() + " às " + assinatura.getHoraAssinatura();

                Row assinaturaRow = sheet.getRow(38);
                Cell assinaturaCell = assinaturaRow.getCell(0);
                assinaturaCell.setCellValue(assinaturaStr);
                
                CellStyle boldStyle = workbook.createCellStyle();
                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                boldStyle.setFont(boldFont);
                boldStyle.setAlignment(HorizontalAlignment.CENTER);
                
                CellStyle styleCentralizado = assinaturaCell.getCellStyle();
                styleCentralizado.setAlignment(HorizontalAlignment.CENTER);

                assinaturaCell.setCellStyle(styleCentralizado);
                
                int indexNomeFuncionario = assinaturaStr.indexOf(registro.getFuncionario().getNome());
                RichTextString richTextAssinatura = assinaturaCell.getRichStringCellValue();

                richTextAssinatura.applyFont(indexNomeFuncionario, indexNomeFuncionario + registro.getFuncionario().getNome().length(), boldFont);

            } catch (NullPointerException e) {
                Row assinaturaRow = sheet.getRow(38);
                assinaturaRow.getCell(0).setCellValue("");
            }

            try {
                //preenchendo assinatura da coordenação
                assinaturaCoordenacao assinaturaCoordenacao = registro.getAssinaturaCoordenacao();

                String assinaturaCoordenacaoStr = "Assinado por " + assinaturaCoordenacao.getNomeCoordenacao() + " em " + assinaturaCoordenacao.getDataAssinatura() + " às " + assinaturaCoordenacao.getHoraAssinatura();

                Row assinaturaCoordenacaoRow = sheet.getRow(39);
                assinaturaCoordenacaoRow.getCell(0).setCellValue(assinaturaCoordenacaoStr);

                CellStyle boldStyle = workbook.createCellStyle();
                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                boldStyle.setFont(boldFont);
                boldStyle.setAlignment(HorizontalAlignment.CENTER);

                CellStyle styleCentralizado = assinaturaCoordenacaoRow.getCell(0).getCellStyle();
                styleCentralizado.setAlignment(HorizontalAlignment.CENTER);

                assinaturaCoordenacaoRow.getCell(0).setCellStyle(styleCentralizado);

                int indexNomeCoordenacao = assinaturaCoordenacaoStr.indexOf(assinaturaCoordenacao.getNomeCoordenacao());
                RichTextString richTextAssinaturaCoordenacao = assinaturaCoordenacaoRow.getCell(0).getRichStringCellValue();

                richTextAssinaturaCoordenacao.applyFont(indexNomeCoordenacao, indexNomeCoordenacao + assinaturaCoordenacao.getNomeCoordenacao().length(), boldFont);

            } catch (NullPointerException e) {
                Row assinaturaCoordenacaoRow = sheet.getRow(39);
                assinaturaCoordenacaoRow.getCell(0).setCellValue("");
            }

            

            // criar excel para o libreoffice converter para pdf
            try (FileOutputStream outputStream = new FileOutputStream(path.getAbsolutePath() + File.separator + "Folha de ponto " + registro.getFuncionario().getNome() + ".xlsx")) {
                workbook.write(outputStream);
            }

            String caminho = path.getAbsolutePath() + File.separator + "Folha de ponto " + registro.getFuncionario().getNome() + ".xlsx";
            caminho = caminho.replace("\\ ", "");

            converterExcelParaPdf(caminho, path.getAbsolutePath());
          
            workbook.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void gerarFolhasPontoServidor(registroMensal registro, File path) throws FileNotFoundException, IOException {
            
            try (Workbook workbook = new XSSFWorkbook(new FileInputStream("Tabela ponto servidores.xlsx"))) {
                
                Sheet sheet = workbook.getSheetAt(0);
    
                // Preencher cabeçalho

            // Nome
            Row nome = sheet.getRow(1);
            nome.getCell(0).setCellValue("Nome: " + registro.getFuncionario().getNome()); // funciona

            // Matrícula
            nome.getCell(6).setCellValue("Matrícula: " + String.valueOf(registro.getFuncionario().getMatricula())); //funciona

            // Lotação
            Row lotacao = sheet.getRow(2);
            lotacao.getCell(0).setCellValue("Lotação: " + registro.getFuncionario().getSetor());

            // mes atual
            lotacao.getCell(6).setCellValue("Mês: " + numeroMesToNome(registro.getRegistros().get(0).getMes()) + " de " + registro.getRegistros().get(0).getData().split("/")[2]); // funciona

            // Função
            Row funcao = sheet.getRow(3);
            funcao.getCell(0).setCellValue("Cargo: " + registro.getFuncionario().getFuncao());

            // Data de admissão
            funcao.getCell(6).setCellValue("Data de admissão: " + registro.getFuncionario().getDataAdmissao()); // funbciona

            // chefia imediata
            List<funcionario> func = new ArrayList<funcionario>();
            try {
                func = dadosFuncionario.buscarFuncionarios();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Row escala = sheet.getRow(4);
            for (funcionario f : func) {
                if (f.getFuncao().equals("Coordenação")) {
                    
                    escala.getCell(0).setCellValue("Chefia imediata: " + f.getNome());
                }
            }
            
            // Carga horária
            escala.getCell(6).setCellValue("Carga horária: " + registro.getFuncionario().getHorasSemanais() + "h semanais."); // funciona
            

            //criando uma lista das datas do mês
            List<String> datas = new ArrayList<String>();
            int dia = 1;
            while (dia <= 31) {
                String data = "";
                String diaStr = dia < 10 ? "0" + String.valueOf(dia) : String.valueOf(dia);
                String mesStr = registro.getRegistros().get(0).getMes() < 10 ? "0" + String.valueOf(registro.getRegistros().get(0).getMes()) : String.valueOf(registro.getRegistros().get(0).getMes());
                data = diaStr + "/" + mesStr + "/" + String.valueOf(registro.getRegistros().get(0).getData().split("/")[2]);
                dia += 1;
                datas.add(data);
            }

            // preenchendo o cabeçalho com periodo de apuração
            Row cabeçalho = sheet.getRow(0);
            Cell cell = cabeçalho.getCell(0);

            CellStyle styleTitulo = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 18);
            styleTitulo.setFont(font);
            styleTitulo.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(styleTitulo);
            styleTitulo.setWrapText(true);

            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            Font font2 = workbook.createFont();
            font2.setBold(true);
            style.setFont(font2);
            style.setWrapText(true);

            cell.setCellValue("Folha de Ponto\n Período de apuração\n" + datas.get(0) + " a " + datas.get(datas.size()-1));

            int indexQuebraLinha = cell.getStringCellValue().indexOf("\n");

            RichTextString richText = cell.getRichStringCellValue();
            richText.applyFont(0, indexQuebraLinha, font);
            richText.applyFont(indexQuebraLinha, richText.length(), font2);

            //preenchendo a tabela de ponto
            int linha = 7;
            for (String data : datas) {
                Row linhaDia = sheet.getRow(linha);

                for (registro r : registro.getRegistros()) {
                    if (r.getData().equals(data)) {
                        linhaDia.getCell(2).setCellValue(r.getHoraEntrada());
                        linhaDia.getCell(3).setCellValue(r.getSaidaAlmoco());
                        linhaDia.getCell(4).setCellValue(r.getRetornoAlmoco());
                        linhaDia.getCell(5).setCellValue(r.getHoraSaida());

                        if (r.isAtestado()) {
                            linhaDia.getCell(6).setCellValue("Atestado.");
                            linhaDia.getCell(7).setCellValue("Sim");
                            
                        }
                        if (r.getAlteracao() != null) {
                            linhaDia.getCell(6).setCellValue(r.getAlteracao());
                        }
                        break;
                    }
                }
                linhaDia.getCell(0).setCellValue(data);
                linhaDia.getCell(1).setCellValue(registro.getRegistros().get(0).dataToDia(data));
                linha += 1;
            }


            try {
                //preenchendo assinatura
                assinatura assinatura = registro.getAssinatura();

                String assinaturaStr = "Assinado por " + registro.getFuncionario().getNome() + " em " + assinatura.getDataAssinatura() + " às " + assinatura.getHoraAssinatura();

                Row assinaturaRow = sheet.getRow(38);
                Cell assinaturaCell = assinaturaRow.getCell(0);
                assinaturaCell.setCellValue(assinaturaStr);
                
                CellStyle boldStyle = workbook.createCellStyle();
                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                boldStyle.setFont(boldFont);
                boldStyle.setAlignment(HorizontalAlignment.CENTER);
                
                CellStyle styleCentralizado = workbook.createCellStyle();
                styleCentralizado.setAlignment(HorizontalAlignment.CENTER);

                assinaturaCell.setCellStyle(styleCentralizado);
                
                int indexNomeFuncionario = assinaturaStr.indexOf(registro.getFuncionario().getNome());
                RichTextString richTextAssinatura = assinaturaCell.getRichStringCellValue();

                richTextAssinatura.applyFont(indexNomeFuncionario, indexNomeFuncionario + registro.getFuncionario().getNome().length(), boldFont);

            } catch (NullPointerException e) {
                Row assinaturaRow = sheet.getRow(38);
                assinaturaRow.getCell(0).setCellValue("");
            }

            try {
                //preenchendo assinatura da coordenação
                assinaturaCoordenacao assinaturaCoordenacao = registro.getAssinaturaCoordenacao();

                String assinaturaCoordenacaoStr = "Assinado por " + assinaturaCoordenacao.getNomeCoordenacao() + " em " + assinaturaCoordenacao.getDataAssinatura() + " às " + assinaturaCoordenacao.getHoraAssinatura();

                Row assinaturaCoordenacaoRow = sheet.getRow(39);
                assinaturaCoordenacaoRow.getCell(0).setCellValue(assinaturaCoordenacaoStr);

                CellStyle boldStyle = workbook.createCellStyle();
                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                boldStyle.setFont(boldFont);
                boldStyle.setAlignment(HorizontalAlignment.CENTER);

                CellStyle styleCentralizado = workbook.createCellStyle();
                styleCentralizado.setAlignment(HorizontalAlignment.CENTER);

                assinaturaCoordenacaoRow.getCell(0).setCellStyle(styleCentralizado);

                int indexNomeCoordenacao = assinaturaCoordenacaoStr.indexOf(assinaturaCoordenacao.getNomeCoordenacao());
                RichTextString richTextAssinaturaCoordenacao = assinaturaCoordenacaoRow.getCell(0).getRichStringCellValue();

                richTextAssinaturaCoordenacao.applyFont(indexNomeCoordenacao, indexNomeCoordenacao + assinaturaCoordenacao.getNomeCoordenacao().length(), boldFont);

            } catch (NullPointerException e) {
                Row assinaturaCoordenacaoRow = sheet.getRow(39);
                assinaturaCoordenacaoRow.getCell(0).setCellValue("");
            }

            // criar excel para o libreoffice converter para pdf
            try (FileOutputStream outputStream = new FileOutputStream(path.getAbsolutePath() + File.separator + "Folha de ponto " + registro.getFuncionario().getNome() + ".xlsx")) {
                workbook.write(outputStream);
            }

            String caminho = path.getAbsolutePath() + File.separator + "Folha de ponto " + registro.getFuncionario().getNome() + ".xlsx";
            caminho = caminho.replace("\\ ", "");

            converterExcelParaPdf(caminho, path.getAbsolutePath());
          
            workbook.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }

    public static void createBarcode(String imageName, String myString) {

        try {

            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(0);
            code128.doQuietZone(false);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();

            FileOutputStream fos = new FileOutputStream("." + File.separator + "barcodes" + File.separator + imageName + ".png");
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void converterExcelParaPdf(String caminho, String saida) {

        // Construindo o comando com ProcessBuilder
        ProcessBuilder builder;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            builder = new ProcessBuilder(
            "\"C:\\Program Files\\LibreOffice\\program\\soffice.bin\"", "--headless", "--convert-to", "pdf", "--outdir", saida, caminho
            );
        } else {
            builder = new ProcessBuilder(
            "libreoffice", "--headless", "--convert-to", "pdf", "--outdir", saida, caminho
            );
        }

        try {
            // Iniciando o processo
            Process process = builder.start();

            // Lendo a saída do processo (opcional)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  

            }

            // Aguardando o término do processo
            int exitCode = process.waitFor();

            // Verificando o código de saída
            if (exitCode == 0) {
                System.out.println("Conversão realizada com sucesso!");
            } else {
                System.err.println("Erro ao converter o arquivo. Código de saída: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static String numeroMesToNome(int mes) {
        List<String> meses = new ArrayList<String>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        return meses.get(mes-1);
    }
}
