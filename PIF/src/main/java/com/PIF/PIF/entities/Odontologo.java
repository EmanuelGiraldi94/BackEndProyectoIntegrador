package com.PIF.PIF.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")

public class Odontologo {

    @Id
    @SequenceGenerator(name = "odontologo_sequence",sequenceName = "odontologo_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "odontologo_sequence")
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer matricula;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }



    public static boolean odontologoCompleto (Odontologo odontologo){

        boolean resp=false;

        if(odontologo!=null){

            if(odontologo.nombre!=null
                    &&odontologo.apellido!=null
                    &&odontologo.matricula!=null){

                resp=true;
            }
        }

        return resp;

    }

    public static boolean turnoOdontologoCompleto (Odontologo odontologo){

        boolean resp=false;

        if(odontologo!=null){

            if(odontologo.id!=null){

                resp=true;
            }
        }

        return resp;

    }

    public Odontologo() {
    }

    public Odontologo(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo(Long id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }
}
