import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

class MongoDbInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        var mongoDBContainer: MongoDBContainer

        init {
            mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
        }
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        mongoDBContainer.start()
        TestPropertyValues.of(
            "mongodb.connection=" + mongoDBContainer.connectionString
        ).applyTo(applicationContext.getEnvironment())
    }
}