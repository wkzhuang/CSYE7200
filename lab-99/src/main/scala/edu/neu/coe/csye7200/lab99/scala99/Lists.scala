/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.csye7200.lab99.scala99

import scala.annotation.tailrec

object P00 {
  def flatten[X](xss: List[List[X]]): List[X] = {
    @scala.annotation.tailrec
    def inner(r: List[X], wss: List[List[X]]): List[X] = wss match {
      case Nil => r
      case h :: t => inner(r ++ h, t)
    }

    inner(Nil, xss)
  }

  def fill[X](n: Int)(x: X): List[X] = {
    @scala.annotation.tailrec
    def inner(r: List[X], l: Int): List[X] = if (l <= 0) r else inner(r :+ x, l - 1)

    inner(Nil, n)
  }
}

object P01 {

//  @scala.annotation.tailrec
  def last[X](xs: List[X]): X = xs match{
    case Nil => throw new NoSuchElementException("empty list")
    case h :: Nil => h
    // h will not be used, so you can you _ to replace it
    case _ :: t => last(t)
  }
}

object P02 {

//  @scala.annotation.tailrec
  def penultimate[X](xs: List[X]): X = xs match{
    // if only 1 element, doesn't have a penultimate one
    // what we really want
    case h :: _ :: Nil => h
    case _ :: t => penultimate(t)
    // match whatever
    case _ => throw new NoSuchElementException("empty list")
  }
}

object P03 {

//  @scala.annotation.tailrec
  def kth[X](k: Int, xs: List[X]): X = (k,xs) match{
    // base case: head of the list
    case (0, h :: _) => h
    // recursive case with guard clause (although it won't loop forever)
    case (n, _ :: t) if n > 0 => kth(n-1, t)
    case (_, Nil) => throw new NoSuchElementException("empty list")
    // takes care of the case where k is negative
    case _ => throw new NoSuchElementException(s"k is negative: $k")
  }
}

object P04 {

  def length[X](xs: List[X]): Int = xs match{
    case Nil => 0
    // to deal with very long list, needs to use tail recursion
    case _ =>
      @tailrec // optional annotation
      def inner(r: Int, work: List[X]): Int = work match{
        case Nil => r
        case _ :: t => inner(r+1, t)
      }
      inner(0, xs)
  }
}

object P05 {

  def reverse[X](xs: List[X]): List[X] = {
    def inner(cur: List[X], prev: List[X]): List[X] = cur match {
      case Nil => prev
      case h :: t => inner(t, h::prev)
    }
    inner(xs, Nil)
  }
}

object P06 {

//@tailrec
def isPalindrome[X](xs: List[X]): Boolean =
  // xs == P05.reverse(xs)

}

object P07 {

  type ListAny = List[Any]

  def flatten(xs: ListAny): ListAny = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P08 {

  def compress[X](xs: List[X]): List[X] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P09 {

  def pack[X](xs: List[X]): List[List[X]] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P10 {

  def encode[X](xs: List[X]): List[(Int, X)] = ??? // TO BE IMPLEMENTED
}

object P11 {

  def encodeModified[X](xs: List[X]): List[Any] = ??? // TO BE IMPLEMENTED
}

object P12 {

  def decode[X](xIs: List[(Int, X)]): List[X] = ??? // TO BE IMPLEMENTED
}

object P13 {

  def encodeDirect[X](xs: List[X]): List[(Int, X)] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P14 {

  def duplicate[X](xs: List[X]): List[X] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P15 {

  def duplicateN[X](n: Int, xs: List[X]): List[X] = {
    // TO BE IMPLEMENTED
    ???
  }
}
