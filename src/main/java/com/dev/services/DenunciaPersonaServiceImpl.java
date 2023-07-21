package com.dev.services;


import com.dev.dao.IDenunciaDAO;
import com.dev.dao.IDenunciaPersonaDAO;
import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.domain.Persona;

import com.dev.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import com.dev.utils.Constantes;


@Slf4j
@Service
public class DenunciaPersonaServiceImpl implements  DenunciaPersonaService {

    @Autowired
    private IDenunciaPersonaDAO denunciaPersonaDAO;

    @Autowired
    private IDenunciaDAO denunciaDAO;

    /**
     *
     */
    @Override
    public List<DenunciaPersona> obtenerDenunciantes(Denuncia denuncia) {
        String cdCodigo = "DNCTE";
        return denunciaPersonaDAO.findByIdDenunciaAndTipoPersonaCdCodigo(denuncia.getIdDenuncia(), cdCodigo);
    }

    /**
     *
     */
    @Override
    public List<DenunciaPersona> obtenerDenunciados(Denuncia denuncia) {
        String cdCodigo = "DNCDO";
        return denunciaPersonaDAO.findByIdDenunciaAndTipoPersonaCdCodigo(denuncia.getIdDenuncia(), cdCodigo);
    }


    /**
     *
     */
    @Override
    public byte[] exportarDenunciasExcel(Integer idEtapaInvestigacion, LocalDate fecha) {

        LocalDate fechaInicio;
        LocalDate fechaFin ;
        List<Denuncia> lstDenuncias;
        if (fecha != null) {
            fechaInicio = fecha.withDayOfMonth(1);
            fechaFin = fecha.withDayOfMonth(fecha.lengthOfMonth());
             lstDenuncias = denunciaDAO.findByEstadoDenunciaIdValorAndFcAltaDenunciaBetween(idEtapaInvestigacion, fechaInicio, fechaFin);
        } else {
            lstDenuncias = denunciaDAO.findByEstadoDenunciaIdValorAndFcBajaFilaIsNull(idEtapaInvestigacion);
        }


        //List<Denuncia> lstDenuncias = denunciaDAO.findAll();

        // Crear el libro de trabajo (workbook)
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;
        if(Constantes.estadoInvestigacion.ID_DENUNCIA.equals(idEtapaInvestigacion)){
        // Crear la hoja en el libro de trabajo
         sheet = workbook.createSheet("Denuncias");

        }

      else if(Constantes.estadoInvestigacion.ID_PRELIMINAR.equals(idEtapaInvestigacion)){
            // Crear la hoja en el libro de trabajo
             sheet = workbook.createSheet("Inv. preliminar");

        }
        else if(Constantes.estadoInvestigacion.ID_PREPARATORIA.equals(idEtapaInvestigacion)){
            // Crear la hoja en el libro de trabajo
             sheet = workbook.createSheet("Inv. preparatoria");

        }
        else if(Constantes.estadoInvestigacion.ID_INTERMEDIA.equals(idEtapaInvestigacion)){
            // Crear la hoja en el libro de trabajo
             sheet = workbook.createSheet("Inv. intermedia");

        }

        createHeaders(idEtapaInvestigacion, workbook, sheet);

        // Crear estilo de fecha
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));


        // Llenar los datos de las denuncias
        int rowNum = 1;
        for (Denuncia denuncia : lstDenuncias) {
            List<DenunciaPersona> lstDenunciaPersonas = denunciaPersonaDAO.findByDenunciaAndTipoPersonaIdValor(denuncia.getIdDenuncia(), Constantes.tipoPersonaId.DENUNCIADO);

            if (!lstDenunciaPersonas.isEmpty()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rowNum - 1);
                row.createCell(1).setCellValue(getNumeroExpediente(denuncia, idEtapaInvestigacion));
                row.createCell(2).setCellValue(denuncia.getEstadoDenuncia().getDsValor());
                row.createCell(3).setCellValue(denuncia.getTipoDocumento().getDsValor());
                row.createCell(4).setCellValue(denuncia.getInvestigador().getNombre() + " " + denuncia.getInvestigador().getApellido());

                Cell dateFcIngresoDoc = row.createCell(5);
                LocalDate localDateFcIngresoDoc = denuncia.getFcIngresoDocumento();

                if (Objects.nonNull(localDateFcIngresoDoc)) {
                    Date dateIngreso = Date.from(localDateFcIngresoDoc.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    dateFcIngresoDoc.setCellValue(dateIngreso);
                    dateFcIngresoDoc.setCellStyle(dateStyle);
                }
                Cell dateFcAltaDenuncia = row.createCell(6);
                LocalDate localDateAltaDen = denuncia.getFcAltaDenuncia();

                if (Objects.nonNull(localDateAltaDen)) {
                    Date dateAltaDen = Date.from(localDateAltaDen.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    dateFcAltaDenuncia.setCellValue(dateAltaDen);
                    dateFcAltaDenuncia.setCellStyle(dateStyle);
                }
                row.createCell(7).setCellValue(denuncia.getNmDocumento());
                row.createCell(8).setCellValue(denuncia.getTipoDelito().getDsValor());
                // Comprobar si getEstadoExpedienteEtapa() es null
                if (denuncia.getEstadoExpedienteEtapa() != null) {
                    row.createCell(9).setCellValue(denuncia.getEstadoExpedienteEtapa().getDsValor());
                } else {
                    row.createCell(9).setCellValue("N/A"); // o cualquier valor predeterminado que desees
                }
                if (denuncia.getLinkFile() != null) {
                    row.createCell(10).setCellValue(denuncia.getLinkFile());
                } else {
                    row.createCell(10).setCellValue("N/A"); // o cualquier valor predeterminado que desees
                }

                row.createCell(11).setCellValue(getDenunciadosString(lstDenunciaPersonas));
            }
        }

        // Ajustar el ancho de las columnas
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000);  // Aumentar el ancho de columna en 1000 unidades (puedes ajustar este valor según tus necesidades)
        }
           // Guardar el archivo Excel
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * método para concatenar los denunciados en un solo recuadro del excel
     *
     * @param lstDenunciaPersonas
     * @return
     */
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


    /**
     * se crea las cabeceras por etapa de investigación
     * @param idEtapaInvestigacion
     * @return
     */
    private LinkedHashMap<String, Integer> getHeadersForEtapa(Integer idEtapaInvestigacion) {
        LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();


        if (Constantes.estadoInvestigacion.ID_DENUNCIA.equals(idEtapaInvestigacion)) {

            headers.put("ID", 0);
            headers.put("N°. Denuncia", 1);
            headers.put("Etapa", 2);
            headers.put("Tipo documento", 3);
            headers.put("Investigador", 4);
            headers.put("Fecha ingreso documento", 5);
            headers.put("fecha alta Denuncia", 6);
            headers.put("N° documento", 7);
            headers.put("Tipo delito", 8);
            headers.put("Estado", 9);
            headers.put("Url", 10);
            headers.put("Denunciados", 11);
        }

      else  if (Constantes.estadoInvestigacion.ID_PRELIMINAR.equals(idEtapaInvestigacion)
                || Constantes.estadoInvestigacion.ID_PREPARATORIA.equals(idEtapaInvestigacion)
                || Constantes.estadoInvestigacion.ID_INTERMEDIA.equals(idEtapaInvestigacion)) {

            headers.put("ID", 0);
            headers.put("N°. expediente", 1);
            headers.put("Etapa", 2);
            headers.put("Tipo documento", 3);
            headers.put("Investigador", 4);
            headers.put("Fecha ingreso documento", 5);
            headers.put("fecha alta Denuncia", 6);
            headers.put("N° documento", 7);
            headers.put("Tipo delito", 8);
            headers.put("Estado", 9);
            headers.put("Url", 10);
            headers.put("Denunciados", 11);
        }
        return headers;
    }


    /**
     * método para crear los estilos de la cabecera
     * @param idEtapaInvestigacion
     * @param workbook
     * @param sheet
     */
    private void createHeaders(Integer idEtapaInvestigacion, Workbook workbook, Sheet sheet) {
        // Obtener encabezados para la etapa específica
        LinkedHashMap<String, Integer> headers = getHeadersForEtapa(idEtapaInvestigacion);

        // Crear estilo de fecha
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        // Crear un estilo de celda para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        // Crear fila de encabezado
        Row headerRow = sheet.createRow(0);

        // Iterar sobre encabezados y agregar a la hoja de trabajo
        for (Map.Entry<String, Integer> entry : headers.entrySet()) {
            Cell headerCell = headerRow.createCell(entry.getValue());
            headerCell.setCellValue(entry.getKey());
            headerCell.setCellStyle(headerStyle);
        }
    }


    /**
     * obtener el numero de expediente segun la etapa investigación que se encuentre
     * @param denuncia
     * @param idEtapaInvestigacion
     * @return
     */
    private String getNumeroExpediente(Denuncia denuncia, Integer idEtapaInvestigacion) {
        if (Constantes.estadoInvestigacion.ID_DENUNCIA.equals(idEtapaInvestigacion) && Objects.isNull(denuncia.getNmExpedienteInvPreliminar())) {
            return denuncia.getNmDenuncia();
        } else if (Constantes.estadoInvestigacion.ID_PRELIMINAR.equals(idEtapaInvestigacion) && Objects.isNull(denuncia.getNmExpedientePreparatoria())) {
            return denuncia.getNmExpedienteInvPreliminar() != null ? denuncia.getNmExpedienteInvPreliminar() : "N/A";
        } else if (Constantes.estadoInvestigacion.ID_PREPARATORIA.equals(idEtapaInvestigacion) || Constantes.estadoInvestigacion.ID_INTERMEDIA.equals(idEtapaInvestigacion)) {
            return denuncia.getNmExpedientePreparatoria() != null ? denuncia.getNmExpedientePreparatoria() : "N/A";
        }
        return "";
    }





}













