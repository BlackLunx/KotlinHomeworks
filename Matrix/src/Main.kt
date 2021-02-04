import matrix.Matrix

fun main(args: Array<String>) {
    val mtx = Matrix(Array(3) { IntArray(3) { 1 } })
    println(mtx)
    val array = Array(3) { IntArray(3) }
    for (i in 0..2) {
        for (j in 0..2) {
            array[i][j] = i * j
        }
    }
    val test = Matrix(array)
    println(test)
    test.add(mtx)
    println(test)
    test.mul(mtx)
    println(test)
}