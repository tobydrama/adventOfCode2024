import java.io.File

fun main() {
    dayFourTaskOne(File("day4.txt").readLines())
    dayFourTaskTwo(File("day4.txt").readLines())
}

fun dayFourTaskTwo(input: List<String>) {
    var numsOfXmas = 0
    val mapInput = input.toList()
    val boolMap = input.map { it.map { _ -> false }.toMutableList() }.toMutableList()
    for (y in input.indices) {
        for (x in 0 until input[y].length - 2) {
            val masmas = diagonalRight2(word = "MAS", x = x, y = y, input = mapInput) && diagonalLeft2(word = "MAS", x = x + 2, y = y, input = mapInput)
            val samsam = diagonalRight2(word = "SAM", x = x, y = y, input = mapInput) && diagonalLeft2(word = "SAM", x = x + 2, y = y, input = mapInput)
            val sammas = diagonalRight2(word = "MAS", x = x, y = y, input = mapInput) && diagonalLeft2(word = "SAM", x = x + 2, y = y, input = mapInput)
            val massam = diagonalRight2(word = "SAM", x = x, y = y, input = mapInput) && diagonalLeft2(word = "MAS", x = x + 2, y = y, input = mapInput)

            if (masmas || samsam || sammas || massam) {
                markx(x = x, y = y, boolMap = boolMap)
                numsOfXmas++
            }
        }
    }
    mapInput.forEachIndexed { y, s ->
        println()
        s.forEachIndexed { x, c ->
            if (boolMap[y][x]) print(c) else print('.')
        }
    }
    println()
    println(numsOfXmas)
}

fun diagonalLeft2(word: String, x: Int, y: Int, input: List<String>): Boolean {
    if (x > 1 && y < input.size - 1) {
        return input[y][x] == word[0] && input[y + 1][x - 1] == word[1] && input[y + 2][x - 2] == word[2]
    }
    return false
}

fun diagonalRight2(word: String, x: Int, y: Int, input: List<String>): Boolean {
    if (y < input.size - 2 && x < input[y].length - 2) {
        return input[y][x] == word[0] && input[y + 1][x + 1] == word[1] && input[y + 2][x + 2] == word[2]
    }
    return false
}

fun markx(x: Int, y: Int, boolMap: MutableList<MutableList<Boolean>>) {
    boolMap[y][x] = true
    boolMap[y][x + 2] = true
    boolMap[y + 1][x + 1] = true
    boolMap[y + 2][x] = true
    boolMap[y + 2][x + 2] = true
}

fun dayFourTaskOne(input: List<String>) {
    var numsOfXmas = 0
    val mapInput = input.toList()
    val boolMap = input.map { it.map { char -> false }.toMutableList() }.toMutableList()
    for (y in input.indices) {
        for (x in 0 until input[y].length) {
            var nums = 0
            val xmasFound = listOf(straight(boolMap = boolMap, word = "XMAS", x = x, y = y, input = mapInput), vertical(boolMap = boolMap, word = "XMAS", x = x, y = y, input = mapInput), diagonalLeft(boolMap = boolMap, word = "XMAS", x = x, y = y, input = mapInput), diagonalRight(boolMap = boolMap, word = "XMAS", x = x, y = y, input = mapInput))
            val xmasBackw = listOf(straight(boolMap = boolMap, word = "SAMX", x = x, y = y, input = mapInput), vertical(boolMap = boolMap, word = "SAMX", x = x, y = y, input = mapInput), diagonalLeft(boolMap = boolMap, word = "SAMX", x = x, y = y, input = mapInput), diagonalRight(boolMap = boolMap, word = "SAMX", x = x, y = y, input = mapInput))
            nums = xmasFound.sumOf { it } + xmasBackw.sumOf { it }

            numsOfXmas += nums
        }
    }
    mapInput.forEachIndexed { y, s ->
        println()
        s.forEachIndexed { x, c ->
            if (boolMap[y][x]) print(c) else print('.')
        }
    }
    println()
    println(numsOfXmas)
}

fun straight(boolMap: MutableList<MutableList<Boolean>>, word: String, x: Int, y: Int, input: List<String>): Int {
    if (x < input[y].length - 3) {
        val isXmas = input[y][x] == word[0] && input[y][x + 1] == word[1] && input[y][x + 2] == word[2] && input[y][x + 3] == word[3]
        if (isXmas) {
            boolMap[y][x] = true
            boolMap[y][x + 1] = true
            boolMap[y][x + 2] = true
            boolMap[y][x + 3] = true
            return 1
        }
    }
    return 0
}

fun vertical(word: String, x: Int, y: Int, input: List<String>, boolMap: MutableList<MutableList<Boolean>>): Int {
    if (y < input.size - 3) {
        val isXmas = input[y][x] == word[0] && input[y + 1][x] == word[1] && input[y + 2][x] == word[2] && input[y + 3][x] == word[3]
        if (isXmas) {
            boolMap[y][x] = true
            boolMap[y + 1][x] = true
            boolMap[y + 2][x] = true
            boolMap[y + 3][x] = true
            return 1
        }
    }
    return 0
}

fun diagonalLeft(word: String, x: Int, y: Int, input: List<String>, boolMap: MutableList<MutableList<Boolean>>): Int {
    if (x > 2 && y < input.size - 3) {
        val isXmas = input[y][x] == word[0] && input[y + 1][x - 1] == word[1] && input[y + 2][x - 2] == word[2] && input[y + 3][x - 3] == word[3]
        if (isXmas) {
            boolMap[y][x] = true
            boolMap[y + 1][x - 1] = true
            boolMap[y + 2][x - 2] = true
            boolMap[y + 3][x - 3] = true
            return 1
        }
    }
    return 0
}

fun diagonalRight(word: String, x: Int, y: Int, input: List<String>, boolMap: MutableList<MutableList<Boolean>>): Int {
    if (y < input.size - 3 && x < input[y].length - 3) {
        val isXmas = input[y][x] == word[0] && input[y + 1][x + 1] == word[1] && input[y + 2][x + 2] == word[2] && input[y + 3][x + 3] == word[3]
        if (isXmas) {
            boolMap[y][x] = true
            boolMap[y + 1][x + 1] = true
            boolMap[y + 2][x + 2] = true
            boolMap[y + 3][x + 3] = true
            return 1
        }
    }
    return 0
}