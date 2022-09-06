package com.PIF.PIF.entities;


import javax.persistence.*;

@Entity
@Table(name = "domicilios")

public class Domicilio {

    @Id
    @SequenceGenerator(name = "domicilio_sequence",sequenceName = "domicilio_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "domicilio_sequence")
    private Long id;
    @Column
    private String calle;
    @Column
    private int numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public static boolean domicilioCompleto (Domicilio domicilio){

        boolean resp=false;

        if(domicilio!=null){

            if(domicilio.calle!=null
                    &&domicilio.calle!=null
                    &&domicilio.localidad!=null
                    &&domicilio.provincia!=null
                    &&domicilio.numero>0){

                resp=true;
            }
        }

        return resp;
    }


    public Domicilio() {


    }

    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
