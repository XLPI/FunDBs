import com.typesafe.config.ConfigFactory

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.{MongoConnection, MongoDriver}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONDocument

import scala.util.{Failure, Success, Try}



object run extends App {

  val config = ConfigFactory.load()
  val config2 = config.getString("mongodb.uri")
  val database = config.getString("mongodb.database")
  println(s"mongodb.uri config: $config2")
  println(s"database config: $database")
  println(s"All configs: $config")


}