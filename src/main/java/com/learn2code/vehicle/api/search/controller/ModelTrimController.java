package com.learn2code.vehicle.api.search.controller;

import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;
import com.learn2code.vehicle.api.search.exception.ModelNotFoundException;
import com.learn2code.vehicle.api.search.exception.TrimTypeNotFoundException;
import com.learn2code.vehicle.api.search.service.ModelTrimService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/model-trim")
public class ModelTrimController {

    @Autowired
    private ModelTrimService modelTrimService;

    @PostMapping
    public ResponseEntity<Model> createModelTrim(@RequestBody Model model){
        Model savedRecord = modelTrimService.saveModel(model);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @PostMapping("/trim-type")
    public ResponseEntity<TrimType> createTrimType(@RequestBody TrimType trimType){
        TrimType savedTrim = modelTrimService.saveTrimType(trimType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrim);
    }

    @GetMapping
    public ResponseEntity<List<Model>> fetchAllModels(){
        List<Model> dbModels = modelTrimService.getAllModels();
        if(dbModels.size()>0){
            return new ResponseEntity<>(dbModels,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable int id,@RequestBody Model model) throws ModelNotFoundException {
        Model dbModel = null;
            dbModel = modelTrimService.modifyModel(id, model);

        return new ResponseEntity<>(dbModel,HttpStatus.OK);
    }

    @PutMapping("/trim-type/{id}")
    public ResponseEntity<TrimType> updateTrimType(@PathVariable int id, @RequestBody TrimType trimType) throws TrimTypeNotFoundException {
        TrimType dbTrim = modelTrimService.modifyTrimType(id, trimType);
       return ResponseEntity.ok(dbTrim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModel(@PathVariable int id) throws ModelNotFoundException {
        modelTrimService.deleteModelById(id);
        return new ResponseEntity<>("Model is deleted from DB for ID-"+id,HttpStatus.OK);
    }

    /*@DeleteMapping("/trim-type/{id}")
    public ResponseEntity<String> deleteTrimType(@PathVariable int id) throws TrimTypeNotFoundException {
        modelTrimService.deleteTrimType(id);
        return ResponseEntity.status(HttpStatus.OK).body("Trim Type is deleted successfully from DB for ID-"+id);
    }*/
}
