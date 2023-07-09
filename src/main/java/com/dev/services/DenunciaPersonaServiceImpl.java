package com.dev.services;


import com.dev.dao.IDenunciaDAO;
import com.dev.dao.IDenunciaPersonaDAO;
import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.Persona;
import com.dev.utils.Constantes;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DenunciaPersonaServiceImpl implements  DenunciaPersonaService{

    @Autowired
    private IDenunciaPersonaDAO denunciaPersonaDAO;

    @Autowired
    private  IDenunciaDAO denunciaDAO;


    @Override
    public List<DenunciaPersona> obtenerDenunciantes(Denuncia denuncia) {
        String cdCodigo =  "DNCTE";
        return denunciaPersonaDAO.findByIdDenunciaAndTipoPersonaCdCodigo(denuncia.getIdDenuncia(), cdCodigo);
    }

    @Override
    public List<DenunciaPersona> obtenerDenunciados(Denuncia denuncia) {
        String cdCodigo =  "DNCDO";
        return denunciaPersonaDAO.findByIdDenunciaAndTipoPersonaCdCodigo(denuncia.getIdDenuncia(), cdCodigo);
    }


//    @Override
//    List<DenunciaPersona> exportarDenunciasExcel(){
//
//        List<DenunciaPersona> lstDenunciaPersona =  denunciaPersonaDAO.findAll();
//
//         List<DenunciaPersona> lstExportarExcel =  new ArrayList<>();
//
//        for (DenunciaPersona denunciaPersona : lstDenunciaPersona ) {
//
//            List<DenunciaPersona>   lstDenunciaPersonas = denunciaPersonaDAO.findByDenunciaAndTipoPersonaIdValor(denunciaPersona.getIdDenuncia(), Constantes.tipoPersonaId.DENUNCIADO);
//
//           // List<Denuncia> lstDenuncia = denunciaDAO.findAll();
//            for (DenunciaPersona dp: lstDenunciaPersonas ) {
//
//                    Denuncia denuncia = denunciaDAO.findById(dp.getIdDenuncia()).get();
//
//                    if(denuncia!=null){
//
//                        Denuncia denuncia1 = new Denuncia();
//                        DenunciaPersona dp2 = new DenunciaPersona();
//                        // settear datos de la denuncia a exportar al excel
//                        denuncia1.setNmDenuncia(denuncia1.getNmDenuncia());
//                        denuncia1.setEstadoDenuncia(denuncia.getEstadoDenuncia());
//                        denuncia1.setTipoDocumento(denuncia.getTipoDocumento());
//                        denuncia1.setInvestigador(denuncia.getInvestigador());
//                        denuncia1.setFcIngresoDocumento(denuncia.getFcIngresoDocumento());
//                        denuncia1.setFcAltaDenuncia(denuncia.getFcAltaDenuncia());
//                        denuncia1.setNmDocumento(denuncia.getNmDocumento());
//                        dp2.setPersona(dp.getPersona());
//
//
//
//                    }
//
//            }
//
//        }
//
//        return  lstExportarExcel;
//    }

    @Override
    public void exportarDenunciasExcel() {
        List<Denuncia> lstDenuncias = denunciaDAO.findAll();

        // Crear el libro de trabajo (workbook)
        Workbook workbook = new XSSFWorkbook();

        // Crear la hoja en el libro de trabajo
        Sheet sheet = workbook.createSheet("Denuncias");

        // Crear estilo de fecha
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        // Crear un estilo de celda para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();

        // Establecer el color de fondo del encabezado a azul
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Crear las fuentes y establecerla en blanco
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        // Crear encabezados


        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("N°. Denuncia");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Etapa");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("N° Exp Preliminar");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("N° Exp Preparatoria");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Tipo Documento");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Investigador");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Fecha Ingreso Documento");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("Fecha Alta Denuncia");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("N° Documento");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("Tipo delito");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(11);
        headerCell.setCellValue("Estado");
        headerCell.setCellStyle(headerStyle);

        headerCell = headerRow.createCell(12);
        headerCell.setCellValue("Denunciados");
        headerCell.setCellStyle(headerStyle);

        // Llenar los datos de las denuncias
        int rowNum = 1;
        for (Denuncia denuncia : lstDenuncias) {
            List<DenunciaPersona> lstDenunciaPersonas = denunciaPersonaDAO.findByDenunciaAndTipoPersonaIdValor(denuncia.getIdDenuncia(), Constantes.tipoPersonaId.DENUNCIADO);

            if (!lstDenunciaPersonas.isEmpty()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rowNum -1);
                row.createCell(1).setCellValue(denuncia.getNmDenuncia());
                row.createCell(2).setCellValue(denuncia.getEstadoDenuncia().getDsValor());

                if(denuncia.getNmExpedienteInvPreliminar()!=null){
                    row.createCell(3).setCellValue(denuncia.getNmExpedienteInvPreliminar());
                }
                else {
                    row.createCell(3).setCellValue("N/A");
                }

                if(denuncia.getNmExpedientePreparatoria()!=null){
                    row.createCell(4).setCellValue(denuncia.getNmExpedientePreparatoria());
                }
                else {
                    row.createCell(4).setCellValue("N/A");
                }

                row.createCell(5).setCellValue(denuncia.getTipoDocumento().getDsValor());
                row.createCell(6).setCellValue(denuncia.getInvestigador().getNombre() + " " + denuncia.getInvestigador().getApellido());

                Cell dateFcIngresoDoc = row.createCell(7);
                LocalDate localDateFcIngresoDoc = denuncia.getFcIngresoDocumento();

                if(Objects.nonNull(localDateFcIngresoDoc)) {
                    Date dateIngreso = Date.from(localDateFcIngresoDoc.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    dateFcIngresoDoc.setCellValue(dateIngreso);
                    dateFcIngresoDoc.setCellStyle(dateStyle);
                }
                Cell dateFcAltaDenuncia = row.createCell(8);
                LocalDate localDateAltaDen = denuncia.getFcAltaDenuncia();

                if(Objects.nonNull(localDateAltaDen)){
                    Date dateAltaDen = Date.from(localDateAltaDen.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    dateFcAltaDenuncia.setCellValue(dateAltaDen);
                    dateFcAltaDenuncia.setCellStyle(dateStyle);

                }
                row.createCell(9).setCellValue(denuncia.getNmDocumento());
                row.createCell(10).setCellValue(denuncia.getTipoDelito().getDsValor());
                // Comprobar si getEstadoExpedienteEtapa() es null
                if (denuncia.getEstadoExpedienteEtapa() != null) {
                    row.createCell(11).setCellValue(denuncia.getEstadoExpedienteEtapa().getDsValor());
                } else {
                    row.createCell(11).setCellValue("N/A"); // o cualquier valor predeterminado que desees
                }

                row.createCell(12).setCellValue(getDenunciadosString(lstDenunciaPersonas));
            }
        }

        // Ajustar el ancho de las columnas
        for (int i = 0; i < 13; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            // Guardar el archivo Excel
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\z3t4-x\\Desktop\\export-excel\\archivo.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el libro de trabajo
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String getDenunciadosString(List<DenunciaPersona> lstDenunciaPersonas) {
        StringBuilder denunciadosBuilder = new StringBuilder();
        for (DenunciaPersona denunciaPersona : lstDenunciaPersonas) {
            Persona persona = denunciaPersona.getPersona();
            if (persona != null) {
                denunciadosBuilder.append(persona.getNombre())
                        .append(" ")
                        .append(persona.getApellido1())
                        .append(" ")
                        .append(persona.getApellido2())
                        .append(", ");
            }
        }
        // Eliminar la última coma y espacio
        if (denunciadosBuilder.length() > 0) {
            denunciadosBuilder.deleteCharAt(denunciadosBuilder.length() - 1);
            denunciadosBuilder.deleteCharAt(denunciadosBuilder.length() - 1);
        }
        return denunciadosBuilder.toString();
    }

}















