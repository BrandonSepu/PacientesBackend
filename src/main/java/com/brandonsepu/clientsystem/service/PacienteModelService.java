package com.brandonsepu.clientsystem.service;

import com.brandonsepu.clientsystem.model.PacienteModel;

import java.util.List;
import java.util.Optional;

public interface PacienteModelService {

    PacienteModel save(PacienteModel pacientemodel);

    Optional<PacienteModel> find(Long id);

    Iterable<PacienteModel> findAll();

    void delete(Long id);

    void delete(PacienteModel pacientemodel);

    void deleteAll();

    long count();

}