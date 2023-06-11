package com.dev.dto.converters;

import com.dev.domain.Denuncia;
import com.dev.domain.DenunciaPersona;
import com.dev.dto.DenunciaDTO;
import com.dev.dto.DenunciaPersonaDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public enum DenunciaToEntity implements Function<DenunciaDTO, Denuncia> {

    INSTANCE;

    @Override
    public Denuncia apply(DenunciaDTO dto) {
        Denuncia entity = new Denuncia();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);

            if (dto.getFiscalia() != null) {
                entity.setFiscalia(CatalogosValoresToEntity.INSTANCE.apply(dto.getFiscalia()));
            }
            if (dto.getTipoDelito() != null) {
                entity.setTipoDelito(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoDelito()));
            }
            if (dto.getInvestigador() != null) {
                entity.setInvestigador(UsuarioToEntity.INSTANCE.apply(dto.getInvestigador()));
            }
            if (dto.getEstadoDenuncia() != null) {
                entity.setEstadoDenuncia(CatalogosValoresToEntity.INSTANCE.apply(dto.getEstadoDenuncia()));
            }
            if (dto.getMesaParte() != null) {
                entity.setMesaParte(CatalogosValoresToEntity.INSTANCE.apply(dto.getMesaParte()));
            }
            if (dto.getTipoDocumento() != null) {
                entity.setTipoDocumento(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoDocumento()));
            }

            if (dto.getAuxiliar() != null) {
                entity.setAuxiliar(CatalogosValoresToEntity.INSTANCE.apply(dto.getAuxiliar()));
            }

            // Agregar las listas de denunciantes y denunciados
            // Agregar los denunciantes y denunciados como conjuntos
           if (dto.getLstDenunciantes() != null) {
                Set<DenunciaPersona> denunciantes = new HashSet<>();
                for (DenunciaPersonaDTO denuncianteDTO : dto.getLstDenunciantes()) {
                    DenunciaPersona denunciante = DenunciaPersonaToEntity.INSTANCE.apply(denuncianteDTO);
                    denunciantes.add(denunciante);
                }
                entity.setLstDenunciantes(denunciantes);
            }
            if (dto.getLstDenunciados() != null) {
                Set<DenunciaPersona> denunciados = new HashSet<>();
                for (DenunciaPersonaDTO denunciadoDTO : dto.getLstDenunciados()) {
                    DenunciaPersona denunciado = DenunciaPersonaToEntity.INSTANCE.apply(denunciadoDTO);
                    //denunciado.setDenuncia(entity);
                    denunciados.add(denunciado);
                }
                entity.setLstDenunciados(denunciados);
            }
        }

        return entity;
    }

}
