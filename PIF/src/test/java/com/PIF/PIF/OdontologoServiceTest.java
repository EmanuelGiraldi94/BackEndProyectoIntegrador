package com.PIF.PIF;


import com.PIF.PIF.entities.Odontologo;
import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest

public class OdontologoServiceTest {


    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    public void agregarOdontologoTest() throws BadRequestException {
        Odontologo odontoDePrueba= new Odontologo("Emanuel","Giraldi",547828);
        odontologoService.guardarOdontologo(odontoDePrueba);

        Optional<Odontologo> odontoDePruebaRecuperado=odontologoService.buscarOdontologoXId(1L);
        assertTrue(odontoDePruebaRecuperado.isPresent());
    }


    @Test
    @Order(2)
    public void buscarOdontologoTest(){
        Long idBuscado=1L;
        Optional<Odontologo> odontoDeRecuperado=odontologoService.buscarOdontologoXId(idBuscado);
        assertTrue(odontoDeRecuperado.isPresent());
    }


    @Test
    @Order(3)
    public void listarOdontologosTest(){
        List<Odontologo> listaDePrueba=odontologoService.listarOdontologo();
        assertTrue(listaDePrueba.size()>0);
    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest() throws BadRequestException {
        Long idBuscado=1L;
        Odontologo odontoConNuevosDatos=
                new Odontologo(idBuscado,"Romina", "Rocha", 236541);
        odontologoService.actualizarOdontologo(odontoConNuevosDatos);
        Optional<Odontologo> odontoRecuperado=odontologoService.buscarOdontologoXId(idBuscado);
        assertEquals("Rocha",odontoRecuperado.get().getApellido());
    }


    @Test
    @Order(5)
    public void EliminarOdontologoTest(){
        Long idEliminar=1L;
        Optional<Odontologo> odontologoEliminar=odontologoService.eliminarOdontologo(idEliminar);
        assertTrue(odontologoEliminar==null);
    }



}