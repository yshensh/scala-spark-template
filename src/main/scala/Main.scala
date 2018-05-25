import org.slf4j.LoggerFactory
import org.apache.spark._

/**
  * main class and entry point
  */

object Main extends App {

  sys.props += "log4j.configuration" -> "log4j.properties"

  val logger = LoggerFactory.getLogger(this.getClass)

  logger.info(s"Start application...")

  val conf = new SparkConf().setAppName("scala-spark").setMaster("local")
  val sc = new SparkContext(conf)
  val NUM_SAMPLES = 10

  val count = sc.parallelize(1 to NUM_SAMPLES).filter { _ =>
    val x = math.random
    val y = math.random
    x*x + y*y < 1
  }.count()
  println(s"Pi is roughly ${4.0 * count / NUM_SAMPLES}")

}