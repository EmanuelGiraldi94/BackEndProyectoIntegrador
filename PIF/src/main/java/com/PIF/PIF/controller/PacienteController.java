package com.PIF.PIF.controller;


import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.exceptions.ResourceNotFoundException;
import com.PIF.PIF.service.IPacienteService;

import com.PIF.PIF.service.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    public static final Logger logger = Logger.getLogger(PacienteServiceImpl.class);

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> traerPacientes() throws ResourceNotFoundException {

        if(pacienteService.listarPacientes().isEmpty()){

            logger.info("No se encontro ningun paciente cargado");

            throw new ResourceNotFoundException("No se encontro ningun paciente cargado");
        }
        else {



            return ResponseEntity.ok(pacienteService.listarPacientes());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteXId(id);
        if (pacienteBuscado.isPresent()){


            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{

            logger.info("No se encontro el paciente id:"+
                    id+" ya que no existe en la base de datos. Error.");

            throw new ResourceNotFoundException("No se encontro el paciente id:"+
                    id+" ya que no existe en la base de datos. Error.");
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente) throws BadRequestException {



        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Optional<Paciente> pacienteBuscadoParaActualizar= pacienteService.buscarPacienteXId(paciente.getId());
        if (pacienteBuscadoParaActualizar.isPresent()){



                return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));

        }
        else{

            logger.info("No se pudo actualiza el paciente ingresado por que " +
                    "no existe en la base de datos");

            throw new BadRequestException("No se pudo actualiza el paciente ingresado por que " +
                    "no existe en la base de datos");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteXId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);


            return ResponseEntity.ok("Se eliminó al paciente id="+id+" de la base de datos");
        }
        else{


            logger.info("No se elimino al paciente id:"+
                    id+" ya que no existe en la base de datos. Error.");

            throw new ResourceNotFoundException("No se eliminó al paciente id:"+
                    id+" ya que no existe en la base de datos. Error.");
        }
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> tratamientoBadRequest(BadRequestException bre){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());

    }

}
