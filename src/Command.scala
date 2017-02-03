import CommandType.CommandType

/**
  * Created by Effi on 2/2/2017.
  */
sealed trait Command {
  val droneId: Int
  val `type`: CommandType
}

case class LoadCommand(droneId: Int, warehouseId: Int, productId: Int, amount: Int) extends Command {
  override val `type` = CommandType.LOAD
}

case class UnloadCommand(droneId: Int, warehouseId: Int, productId: Int, amount: Int) extends Command {
  override val `type` = CommandType.UNLOAD
}

case class DeliverCommand(droneId: Int, orderId: Long, productId: Int, amount: Int) extends Command {
  override val `type` = CommandType.DELIVER
}

case class WaitCommand(droneId: Int, numTurns: Int) extends Command {
  override val `type` = CommandType.WAIT
}
