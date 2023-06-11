package com.dev.services;

import java.util.List;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.dto.DenunciaDTO;
import org.springframework.transaction.annotation.Transactional;


public interface DenunciaService {

    /**
     * se encarga de registrar la denuncia, luego recorre
     * la lista de denunciantes y denunciados para luego
     * persistir en la tabla denuncia_persona
     * @param denunciaDTO
     * @return
     * @throws Exception
     */
    DenunciaDTO registrarTransaccional(DenunciaDTO denunciaDTO) throws Exception;

    /**
     * modificará y mostrará comportamientos según el estado de la denuncia cambie
     * @param denunciaDTO
     * @return
     * @throws Exception
     */
    @Transactional
    DenunciaDTO modificar(DenunciaDTO denunciaDTO, String usuarioOperacion) throws Exception;

    /**
     * solo listará la denuncia en estado "denuncia"
     * @return
     * @throws Exception
     */
   // List<DenunciaDTO> lstDenunciado() throws Exception;

    List<DenunciaDTO> lstDenuncias() throws Exception;

    /**
     * listará la denuncia en estado investigación preliminar
     * @return
     * @throws Exception
     */
    List<DenunciaDTO> lstInvestigacionPreliminar() throws Exception;

    /**
     * listará la denuncia en estado investigación preparatoria
     * @return
     * @throws Exception
     */
    List<DenunciaDTO> lstInvestigacionPreparatoria() throws Exception;

    /**
     * listará por id de denuncia
     * @param id
     * @return
     * @throws Exception
     */
    DenunciaDTO lstPorId(Integer id) throws Exception;

    /**
     * eliminará de forma lógica.
     * @param id
     * @throws Exception
     */

    void eliminar(Integer id) throws Exception;

}
