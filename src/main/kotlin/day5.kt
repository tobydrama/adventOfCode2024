import java.io.File
import kotlin.math.roundToInt

fun main() {
    //dayFiveTaskOne(File("day5.txt").readLines())
    dayFiveTaskTwo(File("day5.txt").readLines())
}

fun dayFiveTaskTwo(input: List<String>){
    val rulesList = mutableListOf<Pair<Int,Int>>()
    val updateList = mutableListOf<MutableList<Int>>()
    val rules = input.takeWhile { it != ""}
    val updates = input.takeLastWhile { it != ""}
    rules.forEach {
        val num1 = it.takeWhile { c -> c != '|' }.toInt()
        val num2 = it.takeLastWhile { c -> c != '|' }.toInt()
        rulesList.add(Pair(num1,num2))
    }
    updates.forEach {
        updateList.add(it.split(',').map{ num-> num.toInt() }.toMutableList())
    }
    val notValidUpdateList = mutableListOf<MutableList<Int>>()
    for (update in updateList){
        if(!passesRules(update, rulesList)) notValidUpdateList.add(update)
    }
    notValidUpdateList.forEach {
        while(!passesRules2(update=it, rules = rulesList)){}
        println(it)
    }
    val sumMiddlePages = notValidUpdateList.sumOf { it[(it.size / 2)] }
    println("Sum of middle pages part two are $sumMiddlePages")
}

fun passesRules2(update: MutableList<Int>, rules: MutableList<Pair<Int, Int>>): Boolean{
    for (rule in rules){
        if (update.contains(rule.first) && update.contains(rule.second)){
            val indexOfFirst = update.indexOf(rule.first)
            val indexOfSecond = update.indexOf(rule.second)
            if (indexOfFirst>indexOfSecond){
                println("swap because of $rule")
                update[indexOfFirst] = rule.second
                update[indexOfSecond] = rule.first
                return false
            }
        }
    }
    return true
}

fun dayFiveTaskOne(input: List<String>){
    val rulesList = mutableListOf<Pair<Int,Int>>()
    val updateList = mutableListOf<MutableList<Int>>()
    val rules = input.takeWhile { it != ""}
    val updates = input.takeLastWhile { it != ""}
    rules.forEach {
        val num1 = it.takeWhile { c -> c != '|' }.toInt()
        val num2 = it.takeLastWhile { c -> c != '|' }.toInt()
        rulesList.add(Pair(num1,num2))
    }
    updates.forEach {
        updateList.add(it.split(',').map{ num-> num.toInt() }.toMutableList())
    }
    val validUpdateList = mutableListOf<MutableList<Int>>()
    for (update in updateList){
        if(passesRules(update, rulesList)) validUpdateList.add(update)
    }
    val sumMiddlePages = validUpdateList.sumOf { it[(it.size / 2)] }
    println("Sum of middle pages are $sumMiddlePages")
}

fun passesRules(update: MutableList<Int>, rules: MutableList<Pair<Int, Int>>):Boolean{
    for (rule in rules){
        if (update.contains(rule.first) && update.contains(rule.second)){
            val indexOfFirst = update.indexOf(rule.first)
            val indexOfSecond = update.indexOf(rule.second)
            if (indexOfFirst>indexOfSecond){
                return false
            }
        }
    }
    return true
}