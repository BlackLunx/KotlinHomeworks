package matrix

import complex.Complex


class Matrix {
    private lateinit var matrix: Array<Array<Complex>>
    private var n = 0
    private var m = 0

    constructor(n: Int, m: Int) {
        this.n = n
        this.m = m
        matrix = Array(n) { Array(m) {Complex(0.0,0.0 )} }
    }

    constructor(buf:  Array<Array<Complex>>) {
        copyArr(buf)
    }

    internal constructor(mtx: Matrix) {
        copyArr(mtx.matrix)
    }

    private fun add(mtx: Matrix, del: Int) {
        if (mtx.n != n || mtx.m != m) throw Exception()
        for (i in 0 until n) {
            for (j in 0 until m) {
                matrix[i][j] = matrix[i][j].Add( mtx.matrix[i][j].Invert(del))
            }
        }
    }

    fun add(mtx: Matrix) {
        add(mtx, 1)
    }

    fun sub(mtx: Matrix) {
        add(mtx, -1)
    }

    fun mul(mtx: Matrix) {
        if (m != mtx.n) throw Exception()
        val buf = Array(n) { Array(mtx.m) {Complex(0.0,0.0 )} }
        for (i in 0 until n) {
            for (j in 0 until mtx.m) {
                for (k in 0 until m) {
                    buf[i][j] = buf[i][j].Add(matrix[i][k].Mul(mtx.matrix[k][j]))
                }
            }
        }
        copyArr(buf)
    }

    private fun copyArr(buf:  Array<Array<Complex>>) {
        n = buf.size
        m = buf[0].size
        matrix = Array(n) { Array(m) {Complex(0.0,0.0 )} }
        for (i in 0 until n) {
            if (m >= 0) System.arraycopy(buf[i], 0, matrix[i], 0, m)
        }
    }

    fun trans() {
        val buf = Array(m) { Array(n) {Complex(0.0,0.0 )} }
        for (i in 0 until n) {
            for (j in 0 until m) {
                buf[j][i] = matrix[i][j]
            }
        }
        copyArr(buf)
    }

    override fun toString(): String {
        val output = StringBuilder()
        for (i in 0 until n) {
            for (j in 0 until m) {
                output.append(matrix[i][j].toString() + " ")
            }
            output.append("\n")
        }
        return output.toString()
    }
}