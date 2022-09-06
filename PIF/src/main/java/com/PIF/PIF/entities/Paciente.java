package com.PIF.PIF.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")

public class Paciente {

    @Id
    @SequenceGenerator(name = "paciente_sequence",sequenceName = "paciente_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "paciente_sequence")
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private int dni;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_domicilio",referencedColumnName = "id")
    //@JsonIgnore
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos=new HashSet<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public static boolean pacienteCompleto (Paciente paciente){

        boolean resp=false;

        if(paciente!=null){

            if(paciente.nombre!=null
                    &&paciente.apellido!=null
                    &&paciente.dni>0
                    &&paciente.email!=null){

                resp=true;
            }
        }

        return resp;

    }

    public static boolean turnoPacientegoCompleto (Paciente paciente){

        boolean resp=false;

        if(paciente!=null){

            if(paciente.id!=null){

                resp=true;
            }
        }

        return resp;

    }

    public Paciente() {
    }

    public Paciente(String apellido, String nombre, String email, int dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Paciente(Long id, String apellido, String nombre, String email, int dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }
}
