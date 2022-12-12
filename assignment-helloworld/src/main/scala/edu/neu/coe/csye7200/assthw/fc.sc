// arc tangent of y/x
// takes 2 parameters but not a tuple explicitly
// if explicitly gives the type then _ can be removed
val f1: (Double, Double) => Double = Math.atan2 _

def tupleMethod (t:(Double, Double)): Unit =
  println(s"${t._1}, ${t._2}")

tupleMethod((1,0))

f1(1,0)

def swapper[T1,T2,R](f:(T1,T2)=>R):(T2,T1)=>R =
  (t2,t1) => f(t1,t2)

val f2 = swapper(f1)

f2(0,1)

val ts: Seq[(Double, Double)] = Seq((4, 3), (0, 1), (0.5, 1))

def point(x:Int): (Double, Double) = ts(x)

// tupled defined by Function2
val f3 : ((Double, Double)) => Double = f2.tupled
println(point(0))
println(point(0).swap)
println(f3(point(0)))
val g: Int => Double = x => f3(point(x).swap) // adding swap

g(0)

case class Date(year:Int, month:Int, day: Int)


val fDate: (Int, Int, Int) => Date = Date.apply

val fDateCurried: Int => Int => Int => Date = fDate.curried

def compareBirthdays(x1: Date, x2: Date): Boolean =
  x1.month == x2.month && x1.day == x2.day

compareBirthdays(fDateCurried(2000)(1)(1), fDate(2000,1,1))

val g_new : Int => Date = fDateCurried(_)(1)(31)

def reverse[T1,T2,T3,R](f:T1=>T2=>T3=>R): T3=>T2=>T1=>R = {
  t3 => t2 => t1 => f(t1)(t2)(t3)
}

val g3 = reverse(fDateCurried)

val h: Int => Date = g3(31)(1)
h(2001)

val g2: (Int, Int) => Date = Date.apply(2000,_,_)

// if only interested in a subset of parameters, convenient to use curried function

g2(1,31)

