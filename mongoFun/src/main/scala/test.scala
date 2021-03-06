//import scala.concurrent.{ ExecutionContext, Future }
//
//import reactivemongo.api.{ DefaultDB, MongoConnection, MongoDriver }
//import reactivemongo.bson.{
//BSONDocumentWriter, BSONDocumentReader, Macros, document
//}
//
//object GetStarted {
//  // My settings (see available connection options)
//  val mongoUri = "mongodb://localhost:27017/mydb?authMode=scram-sha1"
//
//  import ExecutionContext.Implicits.global
//
//  // use any appropriate context
//
//  // Connect to the database: Must be done only once per application
//  val driver = MongoDriver()
//  val parsedUri = MongoConnection.parseURI(mongoUri)
//  val connection = parsedUri.map(driver.connection(_))
//
//  // Database and collections: Get references
//  val futureConnection = Future.fromTry(connection)
//
//  def db1: Future[DefaultDB] = futureConnection.flatMap(_.database("unicorns"))
//
//  def db2: Future[DefaultDB] = futureConnection.flatMap(_.database("anotherdb"))
//
//  def personCollection = db1.map(_.collection("unicornsMy"))
//
//  // Write Documents: insert or update
//
//  implicit def personWriter: BSONDocumentWriter[Person] = Macros.writer[Person]
//
//  // or provide a custom one
//
//  def createPerson(person: Person): Future[Unit] =
//    personCollection.flatMap(_.insert(person).map(_ => {})) // use personWriter
//
//  def updatePerson(person: Person): Future[Int] = {
//    val selector = document(
//      "firstName" -> person.firstName,
//      "lastName" -> person.lastName
//    )
//
//    // Update the matching person
//    personCollection.flatMap(_.update(selector, person).map(_.n))
//  }
//
//  implicit def personReader: BSONDocumentReader[Person] = Macros.reader[Person]
//
//  // or provide a custom one
//
//  def findPersonByAge(age: Int): Future[List[Person]] =
//    personCollection.flatMap(_.find(document("age" -> age)). // query builder
//      cursor[Person]().collect[List]())
//
//  // collect using the result cursor
//  // ... deserializes the document using personReader
//
//  // Custom persistent types
//  case class Person(firstName: String, lastName: String, age: Int)
//
//}