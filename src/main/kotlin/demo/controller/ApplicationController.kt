package demo.controller

import demo.dto.ApplicationDto
import demo.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/application")
class ApplicationController {
    @Autowired
    lateinit var applicationService: ApplicationService

    @GetMapping
    fun getApplications(): Flux<ApplicationDto> {
        return applicationService.get().map { ApplicationDto(it.id, it.name, it.amount) }
    }

    @PutMapping
    fun putApplication(@RequestBody app: ApplicationDto): Mono<ApplicationDto> {
        return applicationService.put(app).map {
            ApplicationDto(it.id, it.name, it.amount)
        }
    }

    @DeleteMapping
    fun deleteApplication(@PathVariable id: Int): Mono<Void> {
        return applicationService.delete(id)
    }
}