package lection2s

import scala.util.{Failure, Success}


object SApplication extends App {

  case class ReserveApp(init_id: Int, end_id: Int, ts: Map[Int, List[Int]], states: Map[Int, SState]) {
    private val printVars = (id: Int) => {
      val possible_t = states(id).transitions
      val res = states.withFilter(kv => possible_t.contains(kv._1)) map { case (k, v) => s"$k - $v " } mkString " :: "
      println(res)
    }

    private def excec(state: Int): Unit =
      if (state == end_id) sys.exit()
      else {
        states(state).execute(printVars(state)) match {
          case Success(id) =>
            excec(id)
          case Failure(ex) =>
            System.err.println(s"\nError ${ex.getMessage}. Please retry.\n")
            Thread.sleep(1000)
            excec(state)
        }
      }

    def run() = excec(init_id)
  }

  val tr: Map[Int, List[Int]] = Map(
    SRegister.id -> List(SFlights.id, SFinishReg.id),
    SFlights.id -> List(SRegister.id, SFinishReg.id),
  )


  val states = Map(
    SRegister.id -> SRegister(tr(SRegister.id)),
    SFlights.id -> SFlights(tr(SFlights.id)),
    SFinishReg.id -> SFinishReg
  )

  val app = ReserveApp(SRegister.id, SFinishReg.id, tr, states)
  app.run()

}
