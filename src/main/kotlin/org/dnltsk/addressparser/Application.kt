package org.dnltsk.addressparser

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}

@Configuration
class ApplicationConfig{

	@Bean
	fun getObjectMapper(): ObjectMapper{
		return ObjectMapper().findAndRegisterModules()
	}

}
