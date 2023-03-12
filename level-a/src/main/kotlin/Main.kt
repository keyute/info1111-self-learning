package io.keyute.level_a

fun main() {
    println("Hello, world!")
    print("What is your name? ")
    val name = readlnOrNull()
    if (name?.isBlank() == true || name == null) {
        println("You didn't enter a name!")
        return
    } else {
        println("Hello, $name!")
    }
    while (true) {
        print("What is your favourite number? ")
        readlnOrNull()?.toIntOrNull()?.let { number ->
            (1..number).forEach {
                println("I love $name $it times!")
            }
            return
        } ?: run {
            println("You didn't enter a number!")
        }
    }
}