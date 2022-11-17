package demo.repository

import demo.model.Application
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ApplicationRepository : ReactiveCrudRepository<Application, Int> {
    fun findByName(name : String) : Flux<Application>
}