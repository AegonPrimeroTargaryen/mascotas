package cl.ms.mascotas.service.impl;

import cl.ms.mascotas.dto.MascotaDto;
import cl.ms.mascotas.entity.MascotaEntity;
import cl.ms.mascotas.exception.MascotaNotFoundException;
import cl.ms.mascotas.repository.MascotaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MascotaServiceImplTest {
    @InjectMocks
    private MascotaServiceImpl mascotaServiceImpl;

    @Mock
    private MascotaRepository mascotaRepositoryMock;

    @Test
    void getMascotas() {
        List<MascotaEntity> mascotas = new ArrayList<>();
        mascotas.add(new MascotaEntity(1L,"test","Perro",15,"kevin"));

        Mockito.when(mascotaRepositoryMock.findAll(Sort.by("idMascota").ascending())).thenAnswer(invocation -> mascotas);

        List<MascotaDto> actual = mascotaServiceImpl.getMascotas();

        Assertions.assertEquals(mascotas.getFirst().toDto(),actual.getFirst());

    }

    @Test
    void getMascotasById() {
        MascotaEntity mascotas = new MascotaEntity(1L,"test","Perro",15,"kevin");

        Mockito.lenient().when(mascotaRepositoryMock.findById(1L)).thenReturn(Optional.of(mascotas));

        List<MascotaDto> actual = mascotaServiceImpl.getMascotaById(1);

        Assertions.assertEquals(mascotas.toDto(),actual.getFirst());
    }

    @Test
    void getMascotaNotFound() {
        Mockito.when(mascotaRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertEquals(0,mascotaServiceImpl.getMascotaById(1).size());
    }
}
