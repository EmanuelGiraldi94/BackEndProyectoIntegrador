package com.PIF.PIF.service;

import com.PIF.PIF.entities.Turno;
import com.PIF.PIF.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {

    List<Turno> listarTurnos();

    Turno guardarTurno(Turno turno) throws BadRequestException;

    Optional<Turno> buscarTurnoXId(Long id);

    Optional<Turno> eliminarTurno(Long id);

    Turno actualizarTurno(Turno turno) throws BadRequestException;
}
