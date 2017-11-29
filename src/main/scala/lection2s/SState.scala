package lection2s

import java.io.{BufferedReader, InputStreamReader, InvalidObjectException}

import scala.util.Try

trait SState {
  val transitions: List[Int]
  final protected val ESC = "\033["
  protected var answer: Option[SAnswer] = None
  private val bf = new BufferedReader(new InputStreamReader(System.in))

  def read(): Unit = {
    answer = Some(new SAnswer)
  }

  def correct: Boolean = if (answer == null) false
  else true

  def process(): Unit = {
  }

  def message(): Unit = {
  }

  def display(): Unit

  def makeChoice(): Try[Int] = {
    val input = scala.io.StdIn.readLine()
    Try(input.toInt).orElse(throw new BadInput("Bad input"))
      .map {
        case x if transitions.contains(x) => x
        case _ => throw new BadInput(s"Invalid transition")
      }
  }

  def execute(printer: => Unit): Try[Int] = {
    var res = false
    display()
    do {
      read()
      res = correct
      if (!res) message()
    } while ( {
      !res
    })
    process()
    printer
    makeChoice()
  }
}

case class SRegister(transitions: List[Int]) extends SState {
  override def display(): Unit = {
    System.out.print(ESC + "2J")
    System.out.println("Система резервирования билетов")
    System.out.println()
    System.out.println()
    System.out.println("логин    ХХХХХХХХХ")
    System.out.println("пароль   ХХХХХХХХХ")
    System.out.println()
  }

  override def toString: String = "Регистрация"
}

object SRegister {
  val id = 1
}

case class SFlights(transitions: List[Int]) extends SState {
  override def display(): Unit = {
    System.out.print(ESC + "2J")
    System.out.println("Из ХХХХХХХХ   В ХХХХХХХХ")
    System.out.println()
    System.out.println()
    System.out.println("Дата вылета с ХХ.ХХ.ХХ   по ХХ.ХХ.ХХ")
    System.out.println()
    System.out.println()
    System.out.println("№ рейса Дата и время вылета Дата и время прибытия")
    System.out.println("1 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
    System.out.println("2 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
    System.out.println("3 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
    System.out.println("4 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
    System.out.println()
    System.out.println()
  }

  override def toString: String = "Вылеты"
}

object SFlights {
  val id = 2
}

//case class Seats(transitions: List[Int]) extends MState {
//  override def display(): Unit = {
//    System.out.print(ESC + "2J")
//    System.out.println("Ваш рейс")
//    System.out.println("№ рейса Дата и время вылета Дата и время прибытия")
//    System.out.println(" ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
//    System.out.println()
//    System.out.println()
//    System.out.println(" Класс    Цена   В наличии")
//    System.out.println(" Эконом   $XXXX      XX")
//    System.out.println(" Бизнес   $XXXX      XX")
//    System.out.println()
//  }
//}
//
//object Seats {
//  val id = 3
//}
//
//case class Reserve(transitions: List[Int]) extends MState {
//  override def display(): Unit = {
//    System.out.print(ESC + "2J")
//    System.out.println("Ваш рейс")
//    System.out.println("№ рейса Дата и время вылета Дата и время прибытия")
//    System.out.println(" ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
//    System.out.println()
//    System.out.println()
//    System.out.println(" Класс       Резервирование")
//    System.out.println(" Эконом           ХХ")
//    System.out.println(" Бизнес           ХХ")
//    System.out.println()
//  }
//}
//
//object Reserve {
//  val id = 4
//}
//
//case class Confirm(transitions: List[Int]) extends MState {
//  override def display(): Unit = {
//    System.out.print(ESC + "2J")
//    System.out.println("Ваш рейс")
//    System.out.println("№ рейса Дата и время вылета Дата и время прибытия")
//    System.out.println(" ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ")
//    System.out.println()
//    System.out.println()
//    System.out.println(" Класс       Резервирование")
//    System.out.println(" Эконом           ХХ")
//    System.out.println(" Бизнес           ХХ")
//    System.out.println("Сумма к оплате  $XXX.XX")
//    System.out.println()
//    System.out.println()
//    System.out.println("Номер карты для оплаты   ХХXXXXX")
//    System.out.println("подтверждение оплаты   ХХX")
//  }
//}
//
//object Confirm {
//  val id = 5
//}
//
//case class Help(transitions: List[Int]) extends MState {
//  override def display(): Unit = {
//    System.out.print(ESC + "2J")
//    System.out.println("HELP how to use the system")
//    System.out.println()
//    System.out.println()
//    System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX")
//    System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX")
//    System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX")
//    System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX")
//    System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX")
//    System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX")
//    System.out.println()
//    System.out.println()
//    System.out.println("Любое число - возврат к предыдущему состоянию")
//  }
//
//  //    override def getChoice: Int = history
//}
//
//object Help {
//  val id = 6
//}

case object SFinishReg extends SState {
  val id = 0

  override def display(): Unit = println("Exit")

  override val transitions = List.empty
  override def toString: String = "Выход"
}



class BadInput(msg: String) extends Exception(msg)

class BadSchema(msg: String) extends Exception(msg)

class SAnswer {}
