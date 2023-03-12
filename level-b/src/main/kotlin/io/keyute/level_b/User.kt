package io.keyute.level_b

import java.util.UUID

data class User(val name: String, val age: Int) {
    fun print() {
        println("User ${hashCode()} has the name $name and is $age years old")
    }
}