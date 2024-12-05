import java.io.File

fun main(){
    dayThreeTaskOne(File("day3.txt").readLines())
    dayThreeTaskTwo(File("day3.txt").readLines())
}

fun dayThreeTaskOne(input: List<String>) {
    var nums = 0
    input.forEach { line: String ->
        val regex = Regex("mul\\((-?\\d+),(-?\\d+)\\)")
        val matches = regex.findAll(line).map { it.groupValues }
        matches.forEach {
            val num1 = it[1].toInt()
            val num2 = it[2].toInt()
            nums += num1*num2
        }
    }
    println(nums)
}

fun dayThreeTaskTwo(input: List<String>) {
    var nums = 0
    var canMult = true
    input.forEach { line: String ->
        val regex = Regex("mul\\((-?\\d+),(-?\\d+)\\)|do\\(\\)|don't\\(\\)")
        val matches = regex.findAll(line).map { it.groupValues }
        matches.forEach {
            if (it[0]=="do()") canMult=true
            if (it[0]=="don't()") canMult=false
            if (it[0][0]=='m' && canMult) {
                val num1 = it[1].toInt()
                val num2 = it[2].toInt()
                nums += num1*num2
            }
        }
    }
    println(nums)
}