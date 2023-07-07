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
import java.util.List;

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

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID Denuncia Persona");
        headerRow.createCell(1).setCellValue("Num. Denuncia");
        headerRow.createCell(2).setCellValue("Estado Denuncia");
        headerRow.createCell(3).setCellValue("Tipo Documento");
        headerRow.createCell(4).setCellValue("Investigador");
        headerRow.createCell(5).setCellValue("Fecha Ingreso Documento");
        headerRow.createCell(6).setCellValue("Fecha Alta Denuncia");
        headerRow.createCell(7).setCellValue("Nombre Documento");
        headerRow.createCell(8).setCellValue("Denunciados");

        // Llenar los datos de las denuncias
        int rowNum = 1;
        for (Denuncia denuncia : lstDenuncias) {
            List<DenunciaPersona> lstDenunciaPersonas = denunciaPersonaDAO.findByDenunciaAndTipoPersonaIdValor(denuncia.getIdDenuncia(), Constantes.tipoPersonaId.DENUNCIADO);

            if (!lstDenunciaPersonas.isEmpty()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rowNum -1);
                row.createCell(1).setCellValue(denuncia.getNmDenuncia());
                row.createCell(2).setCellValue(denuncia.getEstadoDenuncia().getDsValor());
                row.createCell(3).setCellValue(denuncia.getTipoDocumento().getDsValor());
                row.createCell(4).setCellValue(denuncia.getInvestigador().getNombre() + " " + denuncia.getInvestigador().getApellido());
                row.createCell(5).setCellValue(denuncia.getFcIngresoDocumento());
                row.createCell(6).setCellValue(denuncia.getFcAltaDenuncia());
                row.createCell(7).setCellValue(denuncia.getNmDocumento());
                row.createCell(8).setCellValue(getDenunciadosString(lstDenunciaPersonas));
            }
        }

        // Ajustar el ancho de las columnas
        for (int i = 0; i < 9; i++) {
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















