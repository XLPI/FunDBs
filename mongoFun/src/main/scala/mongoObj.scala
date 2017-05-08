import com.typesafe.config.ConfigFactory

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.{MongoConnection, MongoDriver}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONDocument

import scala.util.{Failure, Success, Try}

object mongoObj {

    // config from http://reactivemongo.org/releases/0.12/documentation/tutorial/connect-database.html

	import com.typesafe.config.ConfigFactory
	val baseDir = baseDirectory.value
	val config = ConfigFactory.parseFile(baseDir / "conf/application.conf")

  val config = ConfigFactory.load()
  val driver1 = new reactivemongo.api.MongoDriver
  val connection3 = driver1.connection(List("localhost"))
  val database = config.getString("mongodb.database")

  val document1 = BSONDocument(
    "name" -> "Stephane",
    "lastName" -> "Godbillon",
    "age" -> 29)

  def insertDoc1(coll: BSONCollection, doc: BSONDocument): Future[Unit] = {
    val writeRes: Future[WriteResult] = coll.insert(document1)

    writeRes.onComplete { // Dummy callbacks
      case Failure(e) => e.printStackTrace()
      case Success(writeResult) => {
        println(s"successfully inserted document with result: $writeResult")
      }
    }

    writeRes.map(_ => {}) // in this example, do nothing with the success
  }

  def dbFromConnection(connection: MongoConnection): Future[BSONCollection] = {
    connection.database("test").
      map(_.collection("unicorns"))
  }

  val query = BSONDocument("weight" -> BSONDocument(
        "$lt" -> 500 ))

  val findAnimals = dbFromConnection(connection3).onComplete{
    case Failure(e) => e.printStackTrace()
    case Success(coll) => coll.find(query)

  }



}
///////////////////////////////////////////



//  val cursor = collection.find(query, filter).cursor[BSONDocument]
//   // return the 3 first documents in a Vector[BSONDocument].
//   val vector = cursor.collect[Vector](3, Cursor.FailOnError())











//  val mongoUri = "mongodb://localhost:27017/mydb?authMode=scram-sha1"
//val connectionMy = driver.connection(List("localhost"))
//
//  val db = connectionMy.db("test")
//  val collectionMy = db.collection("unicorns")
//
//  val query = BSONDocument("weight" -> BSONDocument(
//      "$lt" -> 500 ))
//
//  val animals = collectionMy.find(query).cursor[BSONDocument].collect[List]()
//
//  println(s"My animals: $animals")
//
//}
//
object runMyDB extends App {

  import mongoObj._

  val collFuture = dbFromConnection(connection3)
  val coll = collFuture.onComplete {
    case Success(collectionMy) => {
      insertDoc1(collectionMy, document1)
      println(s"successfully get collection from DB: $collectionMy")
    }
    case Failure(e) => e.printStackTrace()
  }



}
