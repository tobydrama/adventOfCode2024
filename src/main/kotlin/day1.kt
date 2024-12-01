import java.io.File
import kotlin.math.abs

fun main(){
    dayOneTaskOne(File("day1.txt").readLines())
    dayOneTaskTwo(File("day1.txt").readLines())
}

fun dayOneTaskTwo(input:List<String>){
    val leftList = mutableListOf<Int>()
    val rightMap = mutableMapOf<Int, Int>()
    input.forEach{
        val numOne = it.takeWhile {char ->  char != ' ' }.toInt()
        val numTwo = it.dropWhile {char ->  char != ' ' }.dropWhile {char ->  char == ' ' }.toInt()
        leftList.add(numOne)
        val newValue = rightMap.getOrDefault(numTwo,0) + 1
        rightMap[numTwo] = newValue
    }
    val distance = mutableListOf<Int>()
    for (i in input.indices) {
        val leftValue = leftList[i]
        distance.add(leftValue * rightMap.getOrDefault(leftValue,0))
    }
    println("Part two total distance is: ${distance.sumOf { it }}")
}

fun dayOneTaskOne(input:List<String>){
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    input.forEach{
        val numOne = it.takeWhile {char ->  char != ' ' }.toInt()
        val numTwo = it.dropWhile {char ->  char != ' ' }.dropWhile {char ->  char == ' ' }.toInt()
        leftList.add(numOne)
        rightList.add(numTwo)
    }
    leftList.sort()
    rightList.sort()
    val distance = mutableListOf<Int>()
    for (i in input.indices) {
        distance.add(abs(leftList[i] - rightList[i]))
    }
    println("Part one total distance is: ${distance.sumOf { it }}")
}