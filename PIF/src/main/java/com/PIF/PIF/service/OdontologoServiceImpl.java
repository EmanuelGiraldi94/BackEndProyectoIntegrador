package com.PIF.PIF.service;

import com.PIF.PIF.entities.Odontologo;

import com.PIF.PIF.exceptions.BadRequestException;
import com.PIF.PIF.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;



@Service
public class OdontologoServiceImpl implements IOdontologoService{

    private static final Logger logger=Logger.getLogger(Odontologo.class );

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public List<Odontologo> listarOdontologo() {

        logger.info("Se listo los odontologos disponibles");

        return odontologoRepository.findAll();
    }


    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) throws BadRequestException {

        if(Odontologo.odontologoCompleto(odontologo)){

            logger.info("Se guardo el odontologo");

            return odontologoRepository.save(odontologo);

        }

        else{

            logger.info("Datos de odontologo incompleto, Error.");

            throw new BadRequestException("Datos de odontologo incompleto, Error.");

        }
    }

    @Override
    public Optional<Odontologo> buscarOdontologoXId(Long id) {

        logger.info("Se encontro el odontologo id:"+
                id);

        return odontologoRepository.findById(id);
    }

    @Override
    public Optional<Odontologo> eliminarOdontologo(Long id) {

        logger.info("Se elimino el odontologo id:"+
                id);

        odontologoRepository.deleteById(id);


        return null;
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo) throws BadRequestException {


        if(Odontologo.odontologoCompleto(odontologo)){

            logger.info("Se actualizo el odontologo");

            return odontologoRepository.save(odontologo);

        }

        else{

            logger.info("Datos de odontologo incompleto, Error.");

            throw new BadRequestException("Datos de odontologo incompleto, Error.");

        }

    }

}
