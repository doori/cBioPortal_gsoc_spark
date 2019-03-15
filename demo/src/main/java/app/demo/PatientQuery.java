package app.demo;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.apache.spark.sql.functions.col;

@Component
public class PatientQuery {

    @Autowired
    SparkSession spark;

    @Autowired
    private ParquetIO parquetIO;

    public static final String DATA_FOLDER = "src/main/resources/data/";
    public static final String PATIENT_FILE = "data_clinical_patient.parquet";

    public List<Patient> getPatients() {
        
        Dataset<Row> df = parquetIO.readParquet(DATA_FOLDER+PATIENT_FILE);
        df.show();
        List<Row> rows = df.collectAsList();
        return rows.stream().map(r -> 
            new Patient(r.getString(0), r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5)))
            .collect(Collectors.toList());
    }

    public List<Patient> findPatient(String id) {
        Dataset<Row> df = parquetIO.readParquet(DATA_FOLDER+PATIENT_FILE);
        df.createOrReplaceTempView("patient");

        String[] pid = id.split("\\-");
        String query = String.format("SELECT * FROM patient WHERE patient_id = '%s-%s'", pid[0], pid[1]);
        Dataset<Row> pdf = spark.sql(query);

        List<Row> rows = pdf.collectAsList();
        return rows.stream().map(r -> 
            new Patient(r.getString(0), r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5)))
            .collect(Collectors.toList());
    }
}