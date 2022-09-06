package com.PIF.PIF.controller;


import com.PIF.PIF.entities.Odontologo;

import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.exceptions.ResourceNotFoundException;
import com.PIF.PIF.service.IOdontologoService;
import com.PIF.PIF.service.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")



public class OdontologoController {

    public static final  Logger logger = Logger.getLogger(OdontologoServiceImpl.class);

    @Autowired
    private IOdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> traerOdontologos() throws ResourceNotFoundException {

        if(odontologoService.listarOdontologo().isEmpty()){

            logger.info("No se encontro ningun odontologo cargado");
            throw new ResourceNotFoundException("No se encontro ningun odontologo cargado");
        }
        else {

            return ResponseEntity.ok(odontologoService.listarOdontologo());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){

            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            logger.info("No se encontro el odontologo id:"+
                    id+" ya que no existe en la base de datos. Error.");

            throw new ResourceNotFoundException("No se encontro el odontologo id:"+
                    id+" ya que no existe en la base de datos. Error.");
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarNuevoOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {



        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {

        Optional<Odontologo> odontologoBuscadoParaActualizar= odontologoService.buscarOdontologoXId(odontologo.getId());
        if (odontologoBuscadoParaActualizar.isPresent()){




            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));

        }
        else{

            logger.info("No se pudo actualiza ael odontologo ingresado por que " +
                    "no existe en la base de datos");

            throw new BadRequestException("No se pudo actualiza ael odontologo ingresado por que " +
                    "no existe en la base de datos");
        }



    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);




            return ResponseEntity.ok("Se eliminó al odontólogo id="+id+" de la base de datos");
        }

        else {


            logger.info("No se eliminó al odontologo id:"+
                    id+" ya que no existe en la base de datos. Error.");

            throw new ResourceNotFoundException("No se eliminó al odontologo id:"+
                    id+" ya que no existe en la base de datos. Error.");
        }
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> tratamientoBadRequest(BadRequestException bre){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());

    }



}
