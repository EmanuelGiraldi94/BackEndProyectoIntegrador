package com.PIF.PIF;



import com.PIF.PIF.entities.Domicilio;
import com.PIF.PIF.entities.Odontologo;
import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.entities.Turno;

import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.service.IOdontologoService;
import com.PIF.PIF.service.IPacienteService;
import com.PIF.PIF.service.ITurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest

public class TurnoServiceTest {


    @Autowired
    ITurnoService turnoService;

    @Autowired
    IOdontologoService odontologoService;

    @Autowired
    IPacienteService pacienteService;


    @Test
    @Order(1)
    public void agregarOdontologoTest() throws BadRequestException {
        Odontologo odontoDePrueba= new Odontologo("Romina","Rocha",236541);
        odontologoService.guardarOdontologo(odontoDePrueba);

        Optional<Odontologo> odontoDePruebaRecuperado=odontologoService.buscarOdontologoXId(1L);
        assertTrue(odontoDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(2)
    public void agregarPacienteTest() throws BadRequestException {
        Paciente pacienteDePrueba= new Paciente("Giraldi","Emanuel","prueba@gmail.com",38055571,LocalDate.of(2012, 10, 20), new Domicilio("Amador Lucero",70,"Tucuman","San Miguel de Tucuman"));
        pacienteService.guardarPaciente(pacienteDePrueba);

        Optional<Paciente> pacienteDePruebaRecuperado=pacienteService.buscarPacienteXId(1L);
        assertTrue(pacienteDePruebaRecuperado.isPresent());
    }


    @Test
    @Order(3)
    public void agregarTurnoTest() throws BadRequestException {


        Turno turnoDePrueba= new Turno(new Odontologo(1L,"Romina","Rocha",236541),new Paciente(1L,"Giraldi","Emanuel","prueba@gmail.com",38055571,LocalDate.of(2012, 10, 20), new Domicilio("Amador Lucero",70,"Tucuman","San Miguel de Tucuman")),LocalDate.of(2022, 10, 20));
        turnoService.guardarTurno(turnoDePrueba);

        Optional<Turno> turnoDePruebaRecuperado=turnoService.buscarTurnoXId(1L);
        assertTrue(turnoDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(4)
    public void buscarTurnoTest(){
        Long idBuscado=1l;
        Optional<Turno> turnoDeRecuperado=turnoService.buscarTurnoXId(idBuscado);
        assertTrue(turnoDeRecuperado.isPresent());
    }


    @Test
    @Order(5)
    public void listarTurnosTest(){
        List<Turno> listaDePrueba=turnoService.listarTurnos();
        assertTrue(listaDePrueba.size()>0);
    }


    @Test
    @Order(6)
    public void actualizarTurnoTest() throws BadRequestException {
        Long idBuscado=1L;
        Turno turnoConNuevosDatos=
                new Turno(idBuscado,new Odontologo(1L,"Romina","Rocha",236541),new Paciente(1L,"Giraldi","Emanuel","prueba@gmail.com",38055571,LocalDate.of(2012, 10, 20), new Domicilio("Amador Lucero",70,"Tucuman","San Miguel de Tucuman")),LocalDate.of(2022, 10, 25));
       turnoService.actualizarTurno(turnoConNuevosDatos);
        Optional<Turno> turnoRecuperado=turnoService.buscarTurnoXId(idBuscado);
        assertEquals(LocalDate.of(2022, 10, 25),turnoRecuperado.get().getFecha());
    }

    @Test
    @Order(7)
    public void EliminarTurnoTest(){
        Long idEliminar=1L;
        Optional<Turno> turnoEliminar=turnoService.eliminarTurno(idEliminar);
        assertTrue( turnoEliminar==null);
    }

}
