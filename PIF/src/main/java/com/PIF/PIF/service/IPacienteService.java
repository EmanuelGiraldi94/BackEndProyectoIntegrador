package com.PIF.PIF.service;

import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    List<Paciente> listarPacientes();

    Paciente guardarPaciente(Paciente paciente) throws BadRequestException;

    Optional<Paciente> buscarPacienteXId(Long id);

    Optional<Paciente> eliminarPaciente(Long id);

    Paciente actualizarPaciente(Paciente paciente) throws BadRequestException;
}
