package me.ilies

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class LinkShinkerApplication

fun main(args: Array<String>) {
    SpringApplication.run(LinkShinkerApplication::class.java, *args)
}

