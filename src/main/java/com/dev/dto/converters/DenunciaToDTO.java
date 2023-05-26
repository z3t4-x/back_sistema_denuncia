package com.dev.dto.converters;

import com.dev.domain.Denuncia;
import com.dev.dto.DenunciaDTO;
import com.dev.dto.DenunciaPersonaDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DenunciaToDTO implements Function<Denuncia, DenunciaDTO> {


    INSTANCE;

    @Override
    public DenunciaDTO apply(Denuncia entity) {
        DenunciaDTO dto = new DenunciaDTO();

        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            this.replaceNull(dto, entity);

            if (entity.getFiscalia() != null) {
                dto.setFiscalia(CatalogosValoresToDTO.INSTANCE.apply(entity.getFiscalia()));
            }
            if (entity.getTipoDelito() != null) {
                dto.setTipoDelito(CatalogosValoresToDTO.INSTANCE.apply(entity.getTipoDelito()));
            }
            if (entity.getAuxiliar() != null) {
                dto.setAuxiliar(CatalogosValoresToDTO.INSTANCE.apply(entity.getAuxiliar()));
            }
            if (entity.getEstadoDenuncia() != null) {
                dto.setEstadoDenuncia(CatalogosValoresToDTO.INSTANCE.apply(entity.getEstadoDenuncia()));
            }
            if (entity.getMesaParte() != null) {
                dto.setMesaParte(CatalogosValoresToDTO.INSTANCE.apply(entity.getMesaParte()));
            }
            if (entity.getTipoDocumento() != null) {
                dto.setTipoDocumento(CatalogosValoresToDTO.INSTANCE.apply(entity.getTipoDocumento()));
            }

/*          if (entity.getLstDenunciantes() != null) {
                List<DenunciaPersonaDTO> lstDenunciantesDTO = entity.getLstDenunciantes().stream()
                        .map(denunciaPersona -> DenunciaPersonaToDTO.INSTANCE.apply(denunciaPersona))
                        .collect(Collectors.toList());
                dto.setLstDenunciantes(lstDenunciantesDTO);
            }

            if (entity.getLstDenunciados() != null) {
                List<DenunciaPersonaDTO> lstDenunciadosDTO = entity.getLstDenunciados().stream()
                        .map(denunciaPersona -> DenunciaPersonaToDTO.INSTANCE.apply(denunciaPersona))
                        .collect(Collectors.toList());
                dto.setLstDenunciados(lstDenunciadosDTO);
            }*/

        }

        return dto;
    }


    /**
     * Reemplaza los null de los Strings no obligatorios por EMPTY
     *
     * @param dto
     * @param entity
     */
    private void replaceNull(DenunciaDTO dto, Denuncia entity) {

        if (StringUtils.isBlank(entity.getDsDescripcion())) {
            dto.setDsDescripcion(StringUtils.EMPTY);
        }

    }

}
