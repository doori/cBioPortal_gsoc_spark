package app.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@Controller
public class ApiController {

    @Autowired
    PatientQuery patientQuery;

    @Autowired
    ParquetIO parquetIO;

    public static final String DATA_FOLDER = "resources/data/";

    // TODO try with yarn

	/*
    * Return all patients
    */
    @GetMapping("patients")
    public ResponseEntity<List<Patient>> getPatients() {
        return new ResponseEntity<>(patientQuery.getPatients(), HttpStatus.OK);
    }

    /*
    * Return patients given a paitent_id
    */
    @GetMapping("patients/{id}")
    public ResponseEntity<List<Patient>> findPatient(@PathVariable String id) {
        return new ResponseEntity<>(patientQuery.findPatient(id), HttpStatus.OK);
    }

    /*
    * Writes provided .txt file in data directory to .parquet
    */
    @GetMapping("writeParquet/{file}")
    public ResponseEntity<String> writeParquet(@PathVariable String file) {
    	String filename = file.split("\\.")[0];
    	return new ResponseEntity<>(
    		parquetIO.writeParquet(DATA_FOLDER+file, DATA_FOLDER+filename+".parquet"), 
    		HttpStatus.OK);
    }
}