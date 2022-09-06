package com.PIF.PIF.service;

import com.PIF.PIF.entities.Odontologo;
import com.PIF.PIF.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    List<Odontologo> listarOdontologo();

    Odontologo guardarOdontologo(Odontologo odontologo) throws BadRequestException;

    Optional<Odontologo> buscarOdontologoXId(Long id);

    Optional<Odontologo> eliminarOdontologo(Long id);

    Odontologo actualizarOdontologo(Odontologo odontologo) throws BadRequestException;
}
