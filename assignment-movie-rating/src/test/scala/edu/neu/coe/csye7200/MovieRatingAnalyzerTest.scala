package edu.neu.coe.csye7200

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.util.{Try, Success, Failure}

class MovieDatabaseAnalyzerTest extends AnyFlatSpec with Matchers {

  val spark: SparkSession = SparkSession
    .builder()
    .appName("Rating Analysis")
    .master("local[1]")
    .getOrCreate()

  //  Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
  spark.sparkContext.setLogLevel("ERROR")
  val path = "/Users/jwkz96/Desktop/CSYE7200/assignment-movie-rating/src/main/resources/movie_metadata.csv"
  val analyzer = MovieRatingAnalyzer(path)

  behavior of "read_file"
  it should "get movie_metadata.csv" in {

    val mdy: Try[DataFrame] = analyzer.read_file(spark)
    mdy.isSuccess shouldBe true
    mdy foreach {
      d =>
        d.count() shouldBe 1609
        d.show(10)
    }
  }

  behavior of "calc_stats"
  it should "work on small batch of data" in {
    // scores from first 5 rows
    val small_list = List(7.9, 7.1, 6.8, 8.5, 7.1)
    import spark.implicits._
    analyzer.calc_stats(small_list.toDF(),"value","mean" ) match {
      case Success(df) => df.first().getDouble(0) shouldBe 7.4799999999999995
      case _ => assertThrows _
    }
    analyzer.calc_stats(small_list.toDF(), "value", "std") match {
      case Success(df) => df.first().getDouble(0) shouldBe 0.7014271166700075
      case _ => assertThrows _
    }
  }

  it should "work on the actual dataframe" in {
    val file = analyzer.read_file(spark) match {case Success(df) => df}
    analyzer.calc_stats(file,"imdb_score","mean") match {
      case Success(df) => df.first().getDouble(0) shouldBe 6.453200745804848
      case _ => assertThrows _
    }
    analyzer.calc_stats(file, "imdb_score", "std") match {
      case Success(df) => df.first().getDouble(0) shouldBe 0.9988071293753289
      case _ => assertThrows _
    }

  }

}
