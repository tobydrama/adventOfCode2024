import java.io.File

fun main(){
    dayTwoTaskOne(File("day2.txt").readLines())
    dayTwoTaskTwo(File("day2.txt").readLines())
}

fun dayTwoTaskTwo(input:List<String>){
    var numOfSafe = 0

    input.forEach {
        val nums = it.split(' ').map { num -> num.toInt() }
        val isSafe = isSafePartTwo(nums)
        if (isSafe){numOfSafe++}
    }

    println("Part two total safe reports are: $numOfSafe")
}

fun isSafePartTwo(nums: List<Int>):Boolean {

    if (isSafe(nums)) {
        println("$nums is safe true no remove")
        return true
    }
    for (i in nums.indices) {
        val nums2 = nums.toMutableList()
        nums2.removeAt(i)
        if (isSafe(nums2)) {
            println("$nums is safe true removed index $i value ${nums[i]}")
            return  true
        }
    }
    println("$nums is safe false")
    return false
}

fun dayTwoTaskOne(input:List<String>){
    var numOfSafe = 0

    input.forEach {
        val nums = it.split(' ').map { num -> num.toInt() }
        if (isSafe(nums = nums)){numOfSafe++}
    }

    println("Part one total safe reports are: $numOfSafe")
}

fun isSafe(nums: List<Int>):Boolean {
    val diffs = mutableListOf<Int>()
    for (i in 0..nums.size-2){
        diffs.add(kotlin.math.abs(nums[i] - nums[i + 1]))
    }
    return (nums.sorted().distinct() == nums || nums.sortedDescending().distinct() ==nums) && !diffs.any { it > 3 }
}