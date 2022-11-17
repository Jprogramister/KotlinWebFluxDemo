package demo.service

import demo.dto.ApplicationDto
import demo.model.Application
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApplicationService {
    fun get(): Flux<Application>
    fun put(app: ApplicationDto): Mono<Application>
    fun delete(id: Int): Mono<Void>
}