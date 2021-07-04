package rationals

import java.math.BigInteger

public infix fun BigInteger.divBy(b: BigInteger): Rational {
    return Rational(this, b)
}

public infix fun Int.divBy(i: Int): Rational {
    return Rational(this.toLong(), i.toLong())
}

public infix fun Long.divBy(l: Long): Rational {
    return Rational(this, l)
}

class Rational(private val n: BigInteger, private val d: BigInteger) : Comparable<Rational> {
    constructor(n: Long, d: Long) : this(BigInteger.valueOf(n), BigInteger.valueOf(d))
    init {
        if (d.equals(0)) {
            throw IllegalArgumentException()
        }
    }

    operator fun unaryMinus(): Rational {
        return this * Rational(-1, 1)
    }

    operator fun times(other: Rational): Rational {
        return Rational(this.n * other.n, this.d * other.d)
    }

    operator fun div(other: Rational): Rational {
        return Rational(this.n * other.d, this.d * other.n)
    }

    operator fun plus(value: Rational): Rational {
        return Rational(this.n * value.d + this.d * value.n, this.d * value.d)
    }

    operator fun minus(other: Rational): Rational {
        return Rational(this.n * other.d - this.d * other.n, this.d * other.d)
    }

    override fun compareTo(other: Rational): Int {
        val left = this.n * other.d
        val rigth = this.d * other.n
        return if (left > rigth)
            1
        else if (rigth > left)
            -1
        else
            0
    }

    override fun equals(other: Any?): Boolean {
        return if (other != null)
            if (other is Rational)
                this.toString() == other.toString()
             else
                false;
        else {
            false
        }
    }

    override fun toString(): String {
        var localN = this.n
        var localD = this.d
        val gcd = this.n.gcd(this.d)
        if (!gcd.equals(BigInteger.valueOf(1))) {
            localN /= gcd;
            localD /= gcd;
        }

        val c = if (localN * localD < BigInteger.valueOf(0)) "-" else ""
        return if (localD.abs() == BigInteger.valueOf(1))
            "$c${localN.abs()}"
        else
            "$c${localN.abs()}/${localD.abs()}"
    }
}

fun String.toRational(): Rational {
    val list = this.split('/')
    return when (list.size) {
        2 -> Rational(list[0].toBigInteger(),list[1].toBigInteger())
        1 -> Rational(list[0].toBigInteger(),BigInteger.valueOf(1))
        else -> {
            Rational(0,0)
        }
    }
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )
}