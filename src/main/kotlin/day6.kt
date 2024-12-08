import java.io.File

fun main() {
    daySixTaskOne(File("day6Test.txt").readLines())
    //daySixTaskTwo(File("day6Test.txt").readLines())
}

fun daySixTaskOne(input: List<String>) {
    val area = input.map { it.toMutableList() }.toMutableList()
    var direction = findDirection(area)
    val x = findX(area, direction)
    val y = findY(area, direction)
    val guard = Guard(direction,Position(x,y))

    println()
    area.forEach {
        println(it)
    }

    while (!canLeaveArea(x=x,y=y,direction=direction, area = area)) {
        if (canMoveForwared(x,y,direction, area)) {
            println("a")
            moveForwared(guard, area)
        }else{
            println("b")
            direction = nextDirection(direction)
            area[y][x] = direction
        }
        println()
        area.forEach {
            println(it)
        }
    }
}

class Guard(var direction: Char, var position: Position)

class Position(val x: Int,val y: Int)

fun moveForwared(guard:Guard, area: MutableList<MutableList<Char>>) {
    area[guard.position.y][guard.position.x] = 'X'
    when(guard.direction){
        '^' -> {
            area[guard.position.y-1][guard.position.x] = guard.direction
            guard.position = Position(guard.position.x, guard.position.y-1)
        }
        '>' -> {
            area[guard.position.y][guard.position.x+1] = guard.direction
            guard.position = Position(guard.position.x+1, guard.position.y)
        }
        'V' -> {
            area[guard.position.y+1][guard.position.x] = guard.direction
            guard.position = Position(guard.position.x, guard.position.y+1)
        }
        '<' -> {
            area[guard.position.y][guard.position.x-1] = guard.direction
            guard.position = Position(guard.position.x-1, guard.position.y)
        }
    }
}

fun canLeaveArea(x:Int ,y:Int ,direction: Char, area: MutableList<MutableList<Char>>):Boolean{
    return when(direction){
        '^' -> y == 0
        '>' -> x == area[0].size-1
        'V' -> y == area.size-1
        '<' -> x == 0
        else -> throw Exception("asdasd")
    }
}

fun canMoveForwared(x:Int ,y:Int, direction: Char, area: MutableList<MutableList<Char>>): Boolean{
    return when(direction){
        '^' -> area[y-1][x] != '#'
        '>' -> area[y][x+1] != '#'
        'V' -> area[y+1][x] != '#'
        '<' -> area[y][x-1] != '#'
        else -> throw Exception("asdasd2")
    }
}

fun findX(area: MutableList<MutableList<Char>>, direction: Char): Int {
    area.forEach {chars ->
        val x: Int = chars.indexOf(direction)
        if (x != -1) return x
    }
    throw Exception("Could not find x")
}

fun findY(area: MutableList<MutableList<Char>>, direction: Char): Int {
    area.forEachIndexed {y, chars ->
        val x: Int = chars.indexOf(direction)
        if (x != -1) return y
    }
    throw Exception("Could not find y")
}

fun findDirection(area: MutableList<MutableList<Char>>): Char {
    area.forEach {  val direction = it.find { c ->
        c == '>' || c == 'V' || c == '<' || c == '^' }
        if (direction != null) return direction
    }
    throw Exception("Could not find direction")
}

fun nextDirection(currentDirection: Char): Char{
    return when(currentDirection){
        '^' -> '>'
        '>' -> 'V'
        'V' -> '<'
        '<' -> '^'
        else -> throw Exception("unknown current direction $currentDirection")
    }
}