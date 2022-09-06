package com.PIF.PIF.service;



import com.PIF.PIF.entities.Odontologo;
import com.PIF.PIF.entities.Paciente;
import com.PIF.PIF.entities.Turno;
import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TurnoServiceImpl implements ITurnoService {


    private static final Logger logger=Logger.getLogger(Turno.class);

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public List<Turno> listarTurnos() {

        logger.info("Se listo los turnos disponibles");

        return turnoRepository.findAll();
    }

    @Override
    public Turno guardarTurno(Turno turno) throws BadRequestException  {

        if(Odontologo.turnoOdontologoCompleto(turno.getOdontologo())){

            if(Paciente.turnoPacientegoCompleto(turno.getPaciente())){

                if(Turno.fechaCompleta(turno)){

                    logger.info("Se guardo el turno");

                    return turnoRepository.save(turno);

                }
                else{

                    logger.info("No se seleciono una fecha para el turno, Error.");

                    throw new BadRequestException("No se seleciono una fecha para el turno, Error.");

                }

            }
            else{

                logger.info("No se seleciono un paciente para el turno, Error.");

                throw new BadRequestException("No se seleciono un paciente para el turno, Error.");

            }

        }

        else{


            logger.info("No se seleciono un odontologo para el turno, Error.");

            throw new BadRequestException("No se seleciono un odontologo para el turno, Error.");

        }

    }

    @Override
    public Optional<Turno> buscarTurnoXId(Long id) {


        logger.info("Se encontro el turno id:"+
                id);

        return turnoRepository.findById(id);
    }

    @Override
    public Optional<Turno> eliminarTurno(Long id) {


        logger.info("Se elimino el turno id:"+
                id);

        turnoRepository.deleteById(id);


        return null;
    }

    @Override
    public Turno actualizarTurno(Turno turno) throws BadRequestException {

        if(Odontologo.turnoOdontologoCompleto(turno.getOdontologo())){

            if(Paciente.turnoPacientegoCompleto(turno.getPaciente())){

                if(Turno.fechaCompleta(turno)){

                    logger.info("Se actualizo el turno");


                    return turnoRepository.save(turno);

                }
                else{

                    logger.info("No se seleciono una fecha para el turno, Error.");

                    throw new BadRequestException("No se seleciono una fecha para el turno, Error.");

                }

            }
            else{

                logger.info("No se seleciono un paciente para el turno, Error.");

                throw new BadRequestException("No se seleciono un paciente para el turno, Error.");

            }

        }

        else{

            logger.info("No se seleciono un odontologo para el turno, Error.");

            throw new BadRequestException("No se seleciono un odontologo para el turno, Error.");

        }

    }
}
