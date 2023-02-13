package com.brandonsepu.clientsystem.service;

import com.brandonsepu.clientsystem.model.PacienteModel;
import com.brandonsepu.clientsystem.repository.PacienteModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteModelServiceImpl implements PacienteModelService {

    @Autowired
    private PacienteModelRepository pacientemodelRepository;

    @Override
    public PacienteModel save(PacienteModel pacientemodel) {
        return pacientemodelRepository.save(pacientemodel);
    }

    @Override
    public Optional<PacienteModel> find(Long id) {
        return pacientemodelRepository.findById(id);
    }

    @Override
    public Iterable<PacienteModel> findAll() {
        return pacientemodelRepository.findAll();
    }

    @Override
    public void delete(Long id) {
    pacientemodelRepository.deleteById(id);
    }

    @Override
    public void delete(PacienteModel pacientemodel) {
        pacientemodelRepository.delete(pacientemodel);
    }

    @Override
    public void deleteAll() {
        pacientemodelRepository.deleteAll();
    }

    @Override
    public long count() {
        return pacientemodelRepository.count();
    }

}