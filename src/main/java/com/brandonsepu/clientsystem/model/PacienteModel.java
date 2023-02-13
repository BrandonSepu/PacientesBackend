package com.brandonsepu.clientsystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PACIENTE")
public class PacienteModel {
    @Id
    @SequenceGenerator(name = "SPAC", sequenceName = "SPAC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPAC")
    @Column(name = "ID_PACIENTE")
    Long ID_PACIENTE;
    @Column(name = "NOMBRE")
    String NOMBRE;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.DATE)
    Date FECHA_INGRESO;
    @Column(name = "CORREO")
    String CORREO;

    @PrePersist
    public void preSave(){
        FECHA_INGRESO = new Date();
    }

    public PacienteModel() {
    }

    public PacienteModel(Long ID_PACIENTE, String NOMBRE, Date FECHA_INGRESO, String CORREO) {
        this.ID_PACIENTE = ID_PACIENTE;
        this.NOMBRE = NOMBRE;
        this.FECHA_INGRESO = FECHA_INGRESO;
        this.CORREO = CORREO;
    }

    public Long getID_PACIENTE() {
        return ID_PACIENTE;
    }

    public void setID_PACIENTE(Long ID_PACIENTE) {
        this.ID_PACIENTE = ID_PACIENTE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public Date getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(Date FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    @Override
    public String toString() {
        return "PacienteModel{" +
                "ID_CLIENTE=" + ID_PACIENTE +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", FECHA_INGRESO=" + FECHA_INGRESO +
                ", CORREO='" + CORREO + '\'' +
                '}';
    }
}
