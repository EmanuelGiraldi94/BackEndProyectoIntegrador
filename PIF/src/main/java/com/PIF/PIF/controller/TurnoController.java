package com.PIF.PIF.controller;


import com.PIF.PIF.entities.Turno;
import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.exceptions.ResourceNotFoundException;
import com.PIF.PIF.service.ITurnoService;
import com.PIF.PIF.service.TurnoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    public static final Logger logger = Logger.getLogger(TurnoServiceImpl.class);

    @Autowired
    private ITurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<Turno>> traerTurnos() throws ResourceNotFoundException {

        if(turnoService.listarTurnos().isEmpty()){

            logger.info("No se encontro ningun turno cargado");

            throw new ResourceNotFoundException("No se encontro ningun turno cargado");
        }
        else {




            return ResponseEntity.ok(turnoService.listarTurnos());
        }



    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado =turnoService.buscarTurnoXId(id);
        if (turnoBuscado.isPresent()){



            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{


            logger.info("No se encontro el turno id:"+
                    id+" ya que no existe en la base de datos. Error.");

            throw new ResourceNotFoundException("No se encontro el turno id:"+
                    id+" ya que no existe en la base de datos. Error.");
        }
    }

    @PostMapping
    public ResponseEntity<Turno> registrarNuevoTurno(@RequestBody Turno turno) throws BadRequestException {




        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Turno> turnoBuscadoParaActualizar= turnoService.buscarTurnoXId(turno.getId());
        if (turnoBuscadoParaActualizar.isPresent()){



            return ResponseEntity.ok(turnoService.actualizarTurno(turno));

        }
        else{

            logger.info("No se pudo actualiza el turno ingresado" +
                    " por que no existe en la base de datos");

            throw new BadRequestException("No se pudo actualiza el turno ingresado" +
                    " por que no existe en la base de datos");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado=turnoService.buscarTurnoXId(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);



            return ResponseEntity.ok("Se eliminó el turno id="+id+" de la base de datos");
        }
        else{

            logger.info("No se eliminó el turno id:"+
                    id+" ya que no existe en la base de datos. Error.");

            throw new ResourceNotFoundException("No se eliminó el turno id:"+
                    id+" ya que no existe en la base de datos. Error.");
        }
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> tratamientoBadRequest(BadRequestException bre){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());

    }

}
