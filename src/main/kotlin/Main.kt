import java.util.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val a = listOf<Int>(1,2,3)
    val b = mutableListOf(1,2,3)
    b.add(4)



    val c = arrayListOf<Int>(1,2,3)
    val d = listOf(1,2,3)
}