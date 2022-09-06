package com.PIF.PIF.service;



import com.PIF.PIF.entities.Domicilio;
import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {


    private static final Logger logger=Logger.getLogger(Paciente.class);


    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listarPacientes() {

        logger.info("Se listo los pacientes disponibles");

        return pacienteRepository.findAll();
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) throws BadRequestException {


        if(Paciente.pacienteCompleto(paciente)){



            if(Domicilio.domicilioCompleto(paciente.getDomicilio())){


                logger.info("Se guardo el paciente");

                return pacienteRepository.save(paciente);

            }

            else{


                logger.info("Domicilio incompleto, Error.");

                throw new BadRequestException("Domicilio incompleto, Error.");

            }

        }

        else {


            logger.info("Datos de paciente incompleto, Error.");

            throw new BadRequestException("Datos de paciente incompleto, Error.");

        }




    }

    @Override
    public Optional<Paciente> buscarPacienteXId(Long id) {

        logger.info("Se encontro el odontologo id:"+
                id);

        return pacienteRepository.findById(id);
    }

    @Override
    public Optional<Paciente> eliminarPaciente(Long id) {

        logger.info("Se elimino el paciente id:"+
                id);


        pacienteRepository.deleteById(id);


        return null;
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) throws BadRequestException {

        if(Paciente.pacienteCompleto(paciente)){



            if(Domicilio.domicilioCompleto(paciente.getDomicilio())){


                logger.info("Se actualizo el paciente");


                    return pacienteRepository.save(paciente);

            }

            else{



                logger.info("Domicilio incompleto, Error.");

                throw new BadRequestException("Domicilio incompleto, Error.");

            }

        }

        else {



            logger.info("Datos de paciente incompleto, Error.");

            throw new BadRequestException("Datos de paciente incompleto, Error.");

        }


    }

}
