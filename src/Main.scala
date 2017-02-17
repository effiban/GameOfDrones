import Parser.Game

/**
  * Created by effib on 17/02/17.
  */
object Main {

  var game: Game = _
  var allCmds: Seq[Command] = _

  def main(args: Array[String]): Unit = {
    game = Parser.parse()
    println(s"game: $game")
  }

  def getFreeDrones: Array[Drone] = {
    game.drones.filter(_.cmds.isEmpty)
  }

  def getNextUnfulfilledOrder: Option[Order] = {
    game.orders.collectFirst{
      case order if !order.isFulfilled => order
    }
  }

  def issueCommands(drone: Drone, order: Order, whs: Warehouse): Unit = {
    (1 to whs.inventoryToBeRemoved.productAmounts.length).foreach { idx =>
      val cmd = LoadCommand(drone.id, whs.id, idx, whs.inventoryToBeRemoved.productAmounts(idx))
      drone.cmds +:= cmd
      allCmds +:= cmd

      val cmd2 = DeliverCommand(drone.id, whs.id, idx, whs.inventoryToBeRemoved.productAmounts(idx))
      drone.cmds +:= cmd2
      allCmds +:= cmd2
    }
  }

  def mainLoop(): Unit = {
    for (i <- 1 to game.board.turns) {


      getFreeDrones.foreach { drone =>
        getNextUnfulfilledOrder.foreach { nextUnfulfilledOrder =>
            val inventoryToLoad = drone.getAvailableInventoryFrom(nextUnfulfilledOrder.missingInventory)
            nextUnfulfilledOrder.enRouteInventory += inventoryToLoad

            var inventoryToPickUp = new Inventory(game.cata)
            while (!inventoryToPickUp.isEmpty) {
              game.warehoues.foreach { wh =>
                inventoryToPickUp -= wh.getAvailableInventoryFrom(inventoryToLoad)
                if (!inventoryToPickUp.isEmpty) {
                  wh.inventoryToBeRemoved += inventoryToPickUp
                  issueCommands(drone, nextUnfulfilledOrder, wh)
                }
              }
            }

        }
      }

    }
  }


}
