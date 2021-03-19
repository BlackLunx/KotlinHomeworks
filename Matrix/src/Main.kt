import complex.Complex
import matrix.Matrix

fun main(args: Array<String>) {
    val mtx = Matrix(Array(3) { Array(3) { Complex(1.0,0.5 ) } })
    println(mtx)
    val array = Array(3) { Array(3) { Complex(0.0,0.0 ) } }
    for (i in 0..2) {
        for (j in 0..2) {
            array[i][j] = Complex(i.toDouble(), j.toDouble())
        }
    }
    val test = Matrix(array)
    println(test)
    test.add(mtx)
    println(test)
    test.mul(mtx)
    println(test)
}