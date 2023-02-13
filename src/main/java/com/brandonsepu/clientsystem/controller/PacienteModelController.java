package com.brandonsepu.clientsystem.controller;

import com.brandonsepu.clientsystem.model.PacienteModel;
import com.brandonsepu.clientsystem.service.PacienteModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PacienteModelController {

    @Autowired
    private PacienteModelService pacientemodelService;

    @PostMapping("/pacientemodel")
    public ResponseEntity<?> save(@RequestBody PacienteModel pacientemodel){
        PacienteModel pacienteModelNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            pacienteModelNew = pacientemodelService.save(pacientemodel);
            response.put("Mensaje:", "Paciente creado con Exito!");
            response.put("Paciente:", pacienteModelNew);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (DataAccessException e){
            response.put("Mensaje", "Paciente no guardado en la Base de Datos");
            response.put("Error", e.getMessage());
            response.put("Error Específico", e.getMostSpecificCause().getMessage());
            System.out.println(response);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/pacientemodel/{id}")
    public ResponseEntity<?> save(@RequestBody PacienteModel pacientemodel, @PathVariable(value = "id") Long id){
        Optional<PacienteModel> pacienteActual = pacientemodelService.find(id);
        Map<String, Object> response = new HashMap<>();
        try{
            if (pacienteActual.isEmpty()){
                response.put("Mensaje", "Paciente no encontrado en la Base de Datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                pacienteActual.get().setCORREO(pacientemodel.getCORREO());
                pacienteActual.get().setNOMBRE(pacientemodel.getNOMBRE());
                pacienteActual.get().setFECHA_INGRESO(pacientemodel.getFECHA_INGRESO());
                pacientemodelService.save(pacienteActual.get());
                response.put("Mensaje", "Paciente Actualizado correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
            }
        }catch (DataAccessException e){
            response.put("Mensaje", "Paciente no updateado en la Base de Datos");
            response.put("Error", e.getMessage());
            response.put("Error Específico", e.getMostSpecificCause().getMessage());
            System.out.println(response);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pacientemodel/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long id){
        Optional<PacienteModel> pacienteModel;
        Map<String, Object> response = new HashMap<>();
        try {
            pacienteModel = pacientemodelService.find(id);
            System.out.println(pacienteModel);
            if (pacienteModel.isEmpty()){
                System.out.println("isPresent  " + pacienteModel.isPresent());
                System.out.println("isEmpty  " +pacienteModel.isEmpty());
                response.put("Mensaje", "El Paciente ".concat(id.toString()).concat(" no encontrado"));
                System.out.println(response + " " + pacienteModel.get().getID_PACIENTE());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                //System.out.println(pacienteModel.isPresent());
                return new ResponseEntity<Optional<PacienteModel>>(pacienteModel, HttpStatus.FOUND);
            }
        } catch(DataAccessException e){
            response.put("Mensaje", "Paciente no encontrado en la Base de Datos");
            response.put("Error", e.getMessage());
            response.put("Error Específico", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pacientemodel")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<>();
        try{
            Iterable<PacienteModel> pacientesList = pacientemodelService.findAll();
            response.put("Pacientes", pacientesList);
            response.put("Mensaje", "Pacientes Encontrados Correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FOUND);
        }catch(DataAccessException e){
            response.put("Mensaje", "Pacientes no encontrados en la Base de Datos");
            response.put("Error", e.getMessage());
            response.put("Error Específico", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/pacientemodel/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            pacientemodelService.delete(id);
            response.put("Mensaje", "Paciente Eliminado Correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch(DataAccessException e){
            response.put("Mensaje", "Paciente no Eliminado de la Base de Datos");
            response.put("Error", e.getMessage());
            response.put("Error Específico", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    /*@DeleteMapping("/pacientemodel")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        pacientemodelService.deleteAll();
    }*/

    @GetMapping("/pacientemodel/count")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<?> count(){
        Map<String, Object> response = new HashMap<>();
        try{
            Long number = pacientemodelService.count();
            response.put("Mensaje", "Pacientes Contados Correctamente");
            response.put("Nro. ", number );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch(DataAccessException e) {
            response.put("Mensaje", "Pacientes no Contados");
            response.put("Error", e.getMessage());
            response.put("Error Específico", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}