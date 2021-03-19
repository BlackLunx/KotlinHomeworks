import java.io.File
import java.lang.Integer.max
import kotlinx.coroutines.*

fun ReadWords(path: String): MutableSet<String> {
    val set = mutableSetOf<String>()
    File(path).useLines { lines -> set.addAll(lines) }
    return set
}

fun CountSymbols(string: String): MutableMap<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    string.forEach { s ->
        if (map.contains(s)) {
            map[s] = map[s]!! + 1
        } else {
            map[s] = 1
        }
    }
    return map
}

fun IsUpper(input: MutableMap<Char, Int>, test: MutableMap<Char, Int>): Boolean {
    var upper = false
    input.forEach { (k, v) ->
        if (!test.contains(k)) {
            upper = true
            return@forEach
        }
        if (test[k]!! < v) {
            upper = true
            return@forEach
        }
    }
    return upper
}

fun Play(words: MutableSet<String>, saveFileName: String): Int {
    val mainWord = words.random()
    println("=============================================================\nИгра началась!")
    println("Ваше слово = $mainWord. Можно использовать только те буквы и то количество, которое используется в изначальном слове. Можно использовать не все буквы")
    println("У вас одна минута, слова вводите построчно. Время пошло...")
    Thread.sleep(1000)
    val start = System.currentTimeMillis()
    val seconds = 10L
    val userWords = mutableListOf<String>()
    println(start)
    while (start + 1000L * seconds >= System.currentTimeMillis()) {
        userWords.add(readLine()!!)
    }
    println("Время вышло!")
    val test = CountSymbols(mainWord)
    userWords.removeIf { value -> IsUpper(CountSymbols(value), test) }
    var count = 0
    val countWords = CoroutineScope(newSingleThreadContext("CountThread")).launch {
        userWords.forEach { str ->
            if (userWords.contains(str)) {
                count++
            }
        }
    }

    val saving = CoroutineScope(Dispatchers.IO).launch {
        val output = StringBuilder()
        userWords.forEach { word -> output.append(word + "\n") }
        File(saveFileName).writeText(output.toString())
    }
    saving.join()
    countWords.join()
    return count
}

fun main(args: Array<String>) {
    val wordsFileName = "words.txt"
    val words = ReadWords(wordsFileName)
    val saveFileName = "words_saved.txt"
    var want = false
    var points = 0
    var maxPoints = 0
    do {
        println("Если хотите сыграть, то введите любой символ")
        val input = readLine()!!
        if (input.length > 0) {
            val current = Play(words, saveFileName)
            points += current
            maxPoints = max(maxPoints, current)
            println("Вы набрали: $current очков. Ваш лучший результат: $maxPoints.\nВсего набрано: $points очков.")
        } else {
            want = false
        }
    } while (want)
}