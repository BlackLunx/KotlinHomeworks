package matrix


class Matrix {
    private lateinit var matrix: Array<IntArray>
    private var n = 0
    private var m = 0

    constructor(n: Int, m: Int) {
        this.n = n
        this.m = m
        matrix = Array(n) { IntArray(m) }
    }

    constructor(buf: Array<IntArray>) {
        copyArr(buf)
    }

    internal constructor(mtx: Matrix) {
        copyArr(mtx.matrix)
    }

    private fun add(mtx: Matrix, del: Int) {
        if (mtx.n != n || mtx.m != m) throw Exception()
        for (i in 0 until n) {
            for (j in 0 until m) {
                matrix[i][j] += mtx.matrix[i][j] * del
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
        val buf = Array(n) { IntArray(mtx.m) }
        for (i in 0 until n) {
            for (j in 0 until mtx.m) {
                for (k in 0 until m) {
                    buf[i][j] += matrix[i][k] * mtx.matrix[k][j]
                }
            }
        }
        copyArr(buf)
    }

    private fun copyArr(buf: Array<IntArray>) {
        n = buf.size
        m = buf[0].size
        matrix = Array(n) { IntArray(m) }
        for (i in 0 until n) {
            if (m >= 0) System.arraycopy(buf[i], 0, matrix[i], 0, m)
        }
    }

    fun trans() {
        val buf = Array(m) { IntArray(n) }
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