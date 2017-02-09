import java.io.{FileReader, FileInputStream}

/**
 * Created by Ran Nisim on 2/9/2017.
 */
class Parser {
  def readFile: Array[Array[Int]] = {
    import scala.io.Source
    Source
      .fromFile("Drones.txt")
      .getLines()
    .map(_.split(" ")).toArray
    .map(_.map(_.toInt).toArray)
  }
  
  case class Board(rows: Int , 
                   cols: Int, 
                   drones: Int , 
                   turns: Int ,
                   maxWeight:Int)
 
  def parser(): Unit ={
    val lines = readFile
  }
  
  def parserBoard(line: Array[Int]): Board = {
    Board(line(0),line(1),line(2),line(3),line(4))
  }
  
  def parserProducts(line: Array[Int]) = Catalog(line)
  
  def parserWarehouses(locationSeq: Array[Int],
                       productsLine: Array[Int],
                        cata: Catalog) = {
    Warehouse(
      Point(locationSeq(0), locationSeq(1)),
      Inventory(cata, productsLine)
    )
  }

  def parserOrder(locationSeq: Array[Int],
                  productsLine: Array[Int],
                  cata: Catalog) = {
    Order(
      Point(locationSeq(0), locationSeq(1)),
      Inventory(cata, buildOrderItem),
      Inventory(cata, Array.fill(productsLine.length)(0))
    )

    def buildOrderItem = {
      val x = productsLine.groupBy(identity)
      var y = Array.fill(productsLine.length)(0)
      x.foreach( entry => {
        y(entry._1) = entry._2.length
      })
      y
    }


  }

  def parse(): Unit ={
    var lines = readFile
    val board = parserBoard(lines(0))
    val cata = parserProducts(lines(2))


    var numware = lines(3)(0)

    lines = lines.drop(4)

    val warehoues =
      (1 to numware).map{ idx =>
      val wareLoc = lines(0)
      val wareItems = lines(1)
      lines = lines.drop(2)
      parserWarehouses(wareLoc, wareItems, cata)
    }
    var numOrder = lines(0)(0)
    lines = lines.drop(1)

    val orders =
      (1 to numOrder).map{ idx =>
        val loc = lines(0)
        val items = lines(2)
        lines = lines.drop(3)
        parserOrder(loc, items, cata)
      }

    var drones = (1 to board.drones).map{
      x =>
        Drone(
          board.maxWeight,
          Inventory(cata,Array.fill(cata.productWeights.length)(0)),
          None,
          Point(0,0),
          0
        )
    } .toArray

    Game(board,
      drones,
    warehoues.toArray,
    orders.toArray)
  }

  case class Game(board: Board,
                  drones: Array[Drone],
                   warehoues: Array[Warehouse],
                   orders: Array[Order])
}

