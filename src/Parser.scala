import java.io.{FileInputStream, FileReader}

import scala.collection.mutable

/**
 * Created by Ran Nisim on 2/9/2017.
 */
object Parser {

  def readFile: Array[Array[Int]] = {
    import scala.io.Source
    Source
      .fromFile("src/Drones.txt")
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
  
  def parserWarehouses(id: Int,
                       locationSeq: Array[Int],
                       productsLine: Array[Int],
                        cata: Catalog): Warehouse = {
    Warehouse(id,
      Point(locationSeq(0), locationSeq(1)),
      Inventory(cata, productsLine),
      new Inventory(cata)
    )
  }

  def parserOrder(locationSeq: Array[Int],
                  productsLine: Array[Int],
                  cata: Catalog): Order = {
    def buildOrderItem = {
      val x = productsLine.groupBy(identity)
      var y = Array.fill(cata.productWeights.length)(0)
      x.foreach( entry => {
        y(entry._1) = entry._2.length
      })
      y
    }


    Order(
      Point(locationSeq(0), locationSeq(1)),
      Inventory(cata, buildOrderItem),
      new Inventory(cata),
      new Inventory(cata)
    )

  }

  def parse(): Game ={
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
      parserWarehouses(idx, wareLoc, wareItems, cata)
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
        Drone(x,
          board.maxWeight,
          new Inventory(cata),
          Seq.empty,
          Point(0,0),
          0
        )
    } .toArray

    Game(board,
      cata,
      drones,
      warehoues.toArray,
      orders.toArray)
  }

  case class Game(board: Board,
                  cata: Catalog,
                  drones: Array[Drone],
                   warehoues: Array[Warehouse],
                   orders: Array[Order]) {

    val droneCmds = {
      val mapp = mutable.Map[Drone, Array[Command]]()
      drones.foreach (
        d => mapp + (d -> Array.empty[Command])
      )
      mapp
    }

  }
}

