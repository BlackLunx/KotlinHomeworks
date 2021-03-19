package complex

import kotlin.math.sqrt

class Complex(var real: Double, var im: Double) {
    fun Add(complex: Complex): Complex {
        return Complex(real + complex.real, im + complex.im)
    }

    fun Sub(complex: Complex): Complex {
        return Complex(real - complex.real, im - complex.im)
    }

    fun Mul(complex: Complex): Complex {
        val tmp = Complex(0.0, 0.0)
        tmp.real = real * complex.real - complex.im * im
        tmp.im = real * complex.im + complex.real * im
        return tmp
    }

    fun Div(complex: Complex): Complex {
        val denominator = complex.real * complex.real + complex.im * complex.im
        val numeratorReal = real * complex.real + complex.im * im
        val numeratorIm = complex.real * im - real * complex.im
        return Complex(numeratorReal / denominator, numeratorIm / denominator)
    }

    fun Scale(scale: Double): Complex {
        return Complex(real * scale, im * scale)
    }

    fun Invert(scale: Int): Complex {
        return Complex(real * scale, im * scale)
    }

    fun Len(): Double {
        return sqrt(real * real + im * im)
    }

    override fun equals(other: Any?): Boolean {
        if (other == this) {
            return true
        }
        if (other == null || other.javaClass != this.javaClass) {
            return false
        }
        val obj = other as Complex
        return obj.real == real && obj.im == im
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }

    override fun toString(): String {
        val output = StringBuilder()
        output.append("Real: ")
        output.append(real)
        output.append(" Im: ")
        output.append(im)
        return output.toString()
    }
}