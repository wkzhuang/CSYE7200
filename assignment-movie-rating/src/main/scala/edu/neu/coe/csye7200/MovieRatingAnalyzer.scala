package edu.neu.coe.csye7200

/**
  The following code is built upon existing module spark-csv in the class repo
 */

import org.apache.spark.sql.{DataFrame, SparkSession}
import scala.util.{Try,Success,Failure}
import org.apache.spark.sql.functions.{avg, stddev}
// import org.apache.log4j.{Level, Logger}

object MovieRatingAnalyzer extends App{

  // Establish spark connection
  val spark: SparkSession = SparkSession
          .builder()
          .appName("Rating Analysis")
          .master("local[1]")
          .getOrCreate()

  // Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
  spark.sparkContext.setLogLevel("ERROR") // We want to ignore all of the INFO and WARN messages.


  val path = "/Users/jwkz96/Desktop/CSYE7200/assignment-movie-rating/src/main/resources/movie_metadata.csv"
  val analyzer = MovieRatingAnalyzer(path)
  val df = {
    analyzer.read_file(spark) match{
      case Success(df) => df
      case Failure(f) => throw new Exception(s"Error reading in file! Thrown $f")
    }
  }
  // check the df that was read in
  df.show(5)
  val mean_df = analyzer.calc_stats(df, "imdb_score", "mean")
  val std_df = analyzer.calc_stats(df, "imdb_score", "std")

  val mean = mean_df match{
    case Success(df) => df.first().getDouble(0)
    case Failure(_) => println("Error calculating the mean")
  }

  val std = std_df match {
    case Success(df) => df.first().getDouble(0)
    case Failure(_) => println("Error calculating the mean")
  }
  println(s"The mean rating is: $mean");
  println(s"The standard deviation of ratings is: $std")
}

case class MovieRatingAnalyzer(resource: String){

  // Method to read_file using existing SparkSession
  def read_file(ss:SparkSession): Try[DataFrame] = {
     Try(ss.read.option("header", true).csv(resource))
  }

  // Method to process DataFrame and return desired statistics
  def calc_stats(df: DataFrame, col: String, stats: String): Try[DataFrame] = {
    stats match {
      case "mean" => Success(df.select(avg(col)))
      case "std" => Success(df.select(stddev(col)))
      case _ => Failure(new Exception("Only calculates mean and standard deviation!"))
    }

  }

}


