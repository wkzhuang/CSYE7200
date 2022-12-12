package edu.neu.coe.csye7200.assthw

import scala.language.implicitConversions

object Primes {
    /**
     * Implicit class to allow easy BigInt operations on a Long.
     *
     * @param x a Long.
     */
    implicit class MaybePrime(x: Long) {
        private val bx = BigInt(x)

        /**
         * Method to yield bx modulo that.
         *
         * @param that a BigInt.
         * @return bx % that.
         */
        def %(that: BigInt): BigInt = bx % that

        /**
         * Method to yield bx gcd that, i.e. get the greatest common divisor of bx and that.
         *
         * @param that a BigInt.
         * @return bx gcd that.
         */
        def gcd(that: BigInt): BigInt = bx gcd that

        /**
         * Method to determine if bx has that as a factor.
         *
         * @param that a BigInt.
         * @return true if that divides exactly into bx.
         */
        def hasFactor(that: BigInt): Boolean = {
            /*TODO IMPLEMENT ME 3*/
            val res = %(that).toInt
            res match {
                case 0 => true
                case _ => false
            }
        }
        /*END*/

        /**
         * Method to determine if bx is coprime with (relatively prime to) that.
         *
         * @param that a BigInt.
         * @return true if bx and that are coprime.
         */
        def coprime(that: BigInt): Boolean = {
            /*TODO IMPLEMENT ME 3 */
            val res = gcd(that).toInt
            res match {
                case 1 => true
                case _ => false
            }

            /*END*/
        }

        def modPow(exp: BigInt, m: BigInt): BigInt = bx.modPow(exp, m)

        /**
         * method to test if bx is a probable prime with confidence dependent on the certainty parameter.
         *
         * @param certainty a certainty of n will yield a probability of error of approx 1 in 2 to the power of n.
         * @return true if bx is probably prime.
         */
        def isProbablePrime(certainty: Int): Boolean = bx.isProbablePrime(certainty)

        /**
         * Method to determine if x is actually prime.
         * Test whether isProbablePrime is true first (with a certainty of 20) and then check that no prime numbers
         * smaller than sqrt(x) are factors.
         * For the supply of primes to test, you should use primes.
         *
         * @return true if x passes both tests.
         */
        def isPrime: Boolean = {
            /*TODO IMPLEMENT ME 8 */
            isProbablePrime(20) match{
                case true =>
                    val check_factors = primes.takeWhile( _ < math.sqrt(x))
                    val bool_res = check_factors.map(hasFactor(_)).forall(_ == false)
                    bool_res match{
                        case true => true
                        case false => false
                    }

                case false => false
            }

            /*END*/
        }
    }

    /**
     * Calculate the value of a prime number based on the formula n * n - n + 41 where n > 0.
     * If the result is prime then return it wrapped in Some. Otherwise, return None.
     *
     * @param n a positive Int.
     * @return Some(prime) otherwise None.
     */
    def EulerPrime(n: Int): Option[BigInt] = { /*TODO IMPLEMENT ME 10*/
        val formula = n*n-n+41
        if (MaybePrime(formula.toLong).isPrime) Some(BigInt(formula))
        else None
    }
    /*END*/

    /**
     * Create an infinitely long lazy list of Longs, that are prime numbers.
     * All primes are odd, except for the very first prime number (2).
     */
    val primes: LazyList[Long] = { /*TODO IMPLEMENT ME 10*/
        2L #:: LazyList.from(3,2).map(_.toLong).filter(MaybePrime(_).isPrime)
    }
    /*END*/

    /**
     * Create a finite list of Option[BigInt], such that each element is the (successful) result of invoking EulerPrime
     * on the current Int.
     * Start with a lazy list of Ints beginning with 1.
     * Do not include any results that are empty.
     */
    val eulerPrimes: List[Option[BigInt]] = { /*TODO IMPLEMENT ME 10*/
        // val lazy_list = for (x <-LazyList.from(1).take(40)) yield EulerPrime(x)
        val euler_list = LazyList.from(1).takeWhile(EulerPrime(_).isDefined).toList
        for(x <- euler_list) yield EulerPrime(x)
    }
    /*END*/

}
