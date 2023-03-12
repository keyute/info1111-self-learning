package io.keyute.level_b

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.Duration

fun main() {
    val users = mutableMapOf<Int, User>()
    val reminders = mutableListOf<Job>()
    println("Manage your users here")
    while (true) {
        println("What would you like to do?")
        println("1. Create a user")
        println("2. Print all users")
        println("3. Edit a user")
        println("4. Remind users who they are")
        println("5. Exit")
        if (reminders.any { it.isActive }) {
            println("6. Cancel all reminders")
        }
        print("Your choice: ")
        val input = readlnOrNull()?.toIntOrNull()?.takeIf { it in 1..6 }
        if (input == null) {
            println("Invalid input")
        } else {
            when (input) {
                1 -> {
                    val user = constructUser() ?: continue
                    if (users.containsKey(user.hashCode())) {
                        println("User already exists!")
                        continue
                    }
                    users[user.hashCode()] = user
                    println("User added! User iD is ${user.hashCode()}")
                }
                2 -> if (users.isEmpty()) {
                    println("There are no users!")
                } else {
                    users.values.forEach { it.print() }
                }
                3 -> {
                    print("What is the user's ID? ")
                    val id = readlnOrNull()?.toIntOrNull() ?: -1
                    if (id <= 0 || !users.containsKey(id)) {
                        println("You have entered an invalid ID!")
                        continue
                    }
                    val newUser = constructUser() ?: continue
                    users.remove(id)
                    users[newUser.hashCode()] = newUser
                    println("User edited! User new iD is ${newUser.hashCode()}")
                }
                4 -> {
                    if (users.isEmpty()) {
                        println("There are no users!")
                        continue
                    }
                    print("How many seconds should pass before reminding users? ")
                    val seconds = readlnOrNull()?.toLongOrNull() ?: -1
                    if (seconds <= 0) {
                        println("You have entered an invalid value!")
                        continue
                    }
                    val job = CoroutineScope(Dispatchers.IO).launch {
                        delay(Duration.ofSeconds(seconds))
                        users.values.forEach { it.print() }
                    }
                    reminders.add(job)
                }
                5 -> {
                    println("Goodbye!")
                    return
                }
                6 -> {
                    reminders.forEach { it.cancel() }
                    reminders.clear()
                }
            }
        }
    }
}

private fun constructUser(): User? {
    print("What is the user's name? ")
    val name = readlnOrNull() ?: ""
    if (name.isBlank()) {
        println("You didn't enter a name!")
        return null
    }
    print("What is the user's age? ")
    val age = readlnOrNull()?.toIntOrNull() ?: -1
    if (age <= 0) {
        println("You have entered an invalid age!")
        return null
    }
    return User(name, age)
}