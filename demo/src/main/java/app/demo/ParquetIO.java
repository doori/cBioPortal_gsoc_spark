package app.demo;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.apache.spark.sql.functions.col;


@Component
public class ParquetIO {

	@Autowired
    SparkSession spark;

	// READ parquet file
	public Dataset<Row> readParquet(String path) {
		Dataset<Row> df = spark.read().parquet(path);
		return df;
	}

	// WRITE from txt file to parquet file
	public String writeParquet(String fromPath, String toPath) {
		Dataset<Row> data = spark.read()
			.option("header", "true")
			.option("delimiter", "\t")
			.csv(fromPath);
		data.write().parquet(toPath);
		return "parquet file saved.";
	}
}