package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
@EnableReactiveMongoRepositories
class WebfluxprojectApplication : AbstractReactiveMongoConfiguration() {
    @Value("\${mongodb.connection}")
    private val mongodbConnection: String? = null

    @Bean
    override fun reactiveMongoClient(): MongoClient = MongoClients.create(mongodbConnection)
    override fun getDatabaseName(): String = "demo"
}

fun main(args: Array<String>) {
    runApplication<WebfluxprojectApplication>(*args)
}