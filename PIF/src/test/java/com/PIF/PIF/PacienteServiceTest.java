package com.PIF.PIF;


import com.PIF.PIF.entities.Domicilio;
import com.PIF.PIF.entities.Odontologo;
import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.service.IPacienteService;
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

public class PacienteServiceTest {


    @Autowired
    IPacienteService pacienteService;



    @Test
    @Order(1)
    public void agregarPacienteTest() throws BadRequestException {
       Paciente pacienteDePrueba= new Paciente("Giraldi","Emanuel","prueba@gmail.com",38055571,LocalDate.of(2012, 10, 20), new Domicilio("Amador Lucero",70,"Tucuman","San Miguel de Tucuman"));
        pacienteService.guardarPaciente(pacienteDePrueba);

        Optional<Paciente> pacienteDePruebaRecuperado=pacienteService.buscarPacienteXId(1L);
        assertTrue(pacienteDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(2)
    public void buscarPacienteTest(){
        Long idBuscado=1L;
        Optional<Paciente> pacienteDeRecuperado=pacienteService.buscarPacienteXId(idBuscado);
        assertTrue(pacienteDeRecuperado.isPresent());
    }


    @Test
    @Order(3)
    public void listarPacoentesTest(){
        List<Paciente> listaDePrueba=pacienteService.listarPacientes();
        assertTrue(listaDePrueba.size()>0);
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest() throws BadRequestException {
        Long idBuscado=1L;
        Paciente pacienteConNuevosDatos=
                new Paciente(idBuscado,"Rocha","Romina","prueba@gmail.com",35816613,LocalDate.of(2012, 10, 20), new Domicilio("Amador Lucero",70,"Tucuman","San Miguel de Tucuman"));
        pacienteService.actualizarPaciente(pacienteConNuevosDatos);
        Optional<Paciente> pacienteRecuperado=pacienteService.buscarPacienteXId(idBuscado);
        assertEquals("Rocha",pacienteRecuperado.get().getApellido());
    }


    @Test
    @Order(5)
    public void EliminarPacienteTest(){
        Long idEliminar=1L;
        Optional<Paciente> pacienteEliminar=pacienteService.eliminarPaciente(idEliminar);
        assertTrue(pacienteEliminar==null);
    }


}
