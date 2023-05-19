package com.dev.services;

import com.dev.dbmemory.H2DatabaseConfig;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Sql("/data-test/data-vocalia.sql")
@SpringBootTest
@ActiveProfiles(profiles = {"BaseDatosEnMemoria"})
@Import(H2DatabaseConfig.class)
//@ContextConfiguration(classes = {H2DatabaseConfig.class}, initializers = ConfigDataApplicationContextInitializer.class)
public class DenunciaServiceTest {

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private CatalogosValoresService catalogosValoresService;

    @Autowired
    private PersonaService personaService;



    @Test
    public void testListarPersonas() {
        try {
        //    List<Persona> personas = personaService.listarPersonas();

         //   assertEquals("GERARDO", personas.get(0).getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void testRegistrarTransaccional() throws Exception {
        // Crear instancia de Persona
 /*       Persona persona1 = new Persona();
        persona1.setNombre("Mario");
        persona1.setApellido1("Lumbre");
        persona1.setApellido2("Lopez");
        persona1.setDni("45788741");
        CatalogosValores genero1 = new CatalogosValores();
        genero1.setIdValor(1);
        persona1.setGenero(genero1);
        CatalogosValores tipoIdentificacion1 = new CatalogosValores();
        tipoIdentificacion1.setIdValor(3);
        persona1.setTipoIdentificacion(tipoIdentificacion1);
        CatalogosValores grado1 = new CatalogosValores();
        grado1.setIdValor(35);
        persona1.setGrado(grado1);
        LocalDate fcNacimiento1 = LocalDate.parse("1987-04-15");
        persona1.setFcNacimiento(fcNacimiento1);

        // Registrar Persona en base de datos
        Persona persona1Registrada = personaService.registrar(persona1);

        // Crear instancia de Persona
        Persona persona2 = new Persona();
        persona2.setNombre("Jorge");
        persona2.setApellido1("Negrete");
        persona2.setApellido2("Cuya");
        persona2.setDni("45788722");
        CatalogosValores genero2 = new CatalogosValores();
        genero2.setIdValor(1);
        persona2.setGenero(genero2);
        CatalogosValores tipoIdentificacion2 = new CatalogosValores();
        tipoIdentificacion2.setIdValor(3);
        persona2.setTipoIdentificacion(tipoIdentificacion2);
        CatalogosValores grado2 = new CatalogosValores();
        grado2.setIdValor(35);
        persona2.setGrado(grado2);
        LocalDate fcNacimiento2 = LocalDate.parse("1987-04-15");
        persona2.setFcNacimiento(fcNacimiento2);

        // Registrar Persona en base de datos
        Persona persona2Registrada = personaService.registrar(persona2);

        // Crear instancia de Persona
        Persona persona3 = new Persona();
        persona3.setNombre("Van");
        persona3.setApellido1("Messi");
        persona3.setApellido2("Rodriguez");
        persona3.setDni("45788733");
        CatalogosValores genero3 = new CatalogosValores();
        genero3.setIdValor(1);
        persona3.setGenero(genero3);
        CatalogosValores tipoIdentificacion3 = new CatalogosValores();
        tipoIdentificacion3.setIdValor(3);
        persona3.setTipoIdentificacion(tipoIdentificacion3);
        CatalogosValores grado3 = new CatalogosValores();
        grado3.setIdValor(35);
        persona3.setGrado(grado3);
        LocalDate fcNacimiento3 = LocalDate.parse("1987-04-15");
        persona3.setFcNacimiento(fcNacimiento3);

        Persona persona3Registrada = personaService.registrar(persona3);

        Denuncia denuncia = new Denuncia();

        CatalogosValores fiscalia =  new CatalogosValores();
        fiscalia.setIdValor(39);
        denuncia.setFiscalia(fiscalia);
        CatalogosValores tipoDelito = new CatalogosValores();
        tipoDelito.setIdValor(25);
        denuncia.setTipoDelito(tipoDelito);
        LocalDate fcHechos = LocalDate.parse("2023-04-10");
        denuncia.setFcHechos(fcHechos);
        CatalogosValores auxiliar = new CatalogosValores();
        auxiliar.setIdValor(18);
        denuncia.setAuxiliar(auxiliar);
        CatalogosValores mesaPartes =  new CatalogosValores();
        mesaPartes.setIdValor(41);
        denuncia.setMesaParte(mesaPartes);
        denuncia.setDsDescripcion("DELITO ABUSO DE AUTORIDAD -> PRUEBA");
        CatalogosValores tipoDocumento = new CatalogosValores();
        tipoDocumento.setIdValor(14);
        denuncia.setTipoDocumento(tipoDocumento);
        LocalDate fcIngresoDocumento = LocalDate.parse("2023-04-13");
        denuncia.setFcIngresoDocumento(fcIngresoDocumento.atStartOfDay());
        denuncia.setNmDocumento("Doc-003");


        // Asignar denunciantes y denunciados
        DenunciaPersonaDTO denunciaPersonaDTO = new DenunciaPersonaDTO();
        List<DenunciaPersona> lstDenunciantesPersona = new ArrayList<>();
        DenunciaPersona denunciante1 = new DenunciaPersona();
        denunciante1.setPersona(persona1Registrada);
        denunciante1.setDenuncia(denuncia);
        CatalogosValores tipoDenunciante1 =  new CatalogosValores();
        tipoDenunciante1.setIdValor(33);
        denunciante1.setTipoPersona(tipoDenunciante1);
        lstDenunciantesPersona.add(denunciante1);

        DenunciaPersona denunciante2 = new DenunciaPersona();
        denunciante2.setPersona(persona2Registrada);
        denunciante2.setDenuncia(denuncia);
        CatalogosValores tipoDenunciante2 = new CatalogosValores();
        tipoDenunciante2.setIdValor(33);
        denunciante2.setTipoPersona(tipoDenunciante2);
        lstDenunciantesPersona.add(denunciante2);
        denunciaPersonaDTO.setLstDenunciantesPersonaDTO(lstDenunciantesPersona);

        // persona denunciada
*//*        DenunciaPersona denunciado = new DenunciaPersona();
        denunciado.setPersona(persona3Registrada);
        CatalogosValores tipoDenunciado = new CatalogosValores();
        tipoDenunciado.setIdValor(33);
        denunciante2.setTipoPersona(tipoDenunciado);
        lstDenunciantesPersona.add(denunciado);
        denunciaPersonaDTO.setLstDenunciadosPersonaDTO(lstDenunciantesPersona);*//*


        List<DenunciaPersona> lstDenunciadosPersona = new ArrayList<>();
        DenunciaPersona denunciado1 = new DenunciaPersona();
        denunciado1.setPersona(persona3Registrada);
        denunciado1.setDenuncia(denuncia);
        CatalogosValores tipoDenunciado = new CatalogosValores();
        tipoDenunciado.setIdValor(33);
        denunciado1.setTipoPersona(tipoDenunciado);
        lstDenunciadosPersona.add(denunciado1);
        denunciaPersonaDTO.setLstDenunciadosPersonaDTO(lstDenunciadosPersona);

        denunciaPersonaDTO.setDenuncia(denuncia);

        // Registrar denuncia
        DenunciaPersonaDTO denunciaRegistrada = denunciaService.registrarTransaccional(denunciaPersonaDTO);

        // Validar que la denuncia se haya registrado correctamente
        assertNotNull(denunciaRegistrada);
        assertNotNull(denunciaRegistrada.getDenuncia());
        assertEquals(denuncia.getFcHechos(), denunciaRegistrada.getDenuncia().getFcHechos());
        assertEquals(denuncia.getDsDescripcion(), denunciaRegistrada.getDenuncia().getDsDescripcion());
        assertEquals(denuncia.getFcIngresoDocumento(), denunciaRegistrada.getDenuncia().getFcIngresoDocumento());

        // Validar que la fiscalía y el tipo de delito sean válidos
        assertNotNull(denuncia.getFiscalia());
        assertNotNull(denuncia.getTipoDelito());

        // Validar que la fecha de hechos sea anterior a la fecha de ingreso del documento
        assertTrue(denuncia.getFcHechos().isBefore(denuncia.getFcIngresoDocumento().toLocalDate()));

        // Validar que al menos haya un denunciante y un denunciado
        assertNotNull(denunciaPersonaDTO.getLstDenunciantesPersonaDTO());
        assertNotNull(denunciaPersonaDTO.getLstDenunciadosPersonaDTO());
        assertFalse(denunciaPersonaDTO.getLstDenunciantesPersonaDTO().isEmpty());
        assertFalse(denunciaPersonaDTO.getLstDenunciadosPersonaDTO().isEmpty());

// Validar que los tipos de persona del denunciante y el denunciado sean válidos
        for (DenunciaPersona denunciante : denunciaPersonaDTO.getLstDenunciantesPersonaDTO()) {
            assertNotNull(denunciante.getTipoPersona());
        }
        for (DenunciaPersona denunciado : denunciaPersonaDTO.getLstDenunciadosPersonaDTO()) {
            assertNotNull(denunciado.getTipoPersona());
        }

    }
*/
    }
    }
