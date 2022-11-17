package demo.service

import demo.dto.ApplicationDto
import demo.model.Application
import demo.repository.ApplicationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ApplicationServiceImpl : ApplicationService {

    @Autowired
    lateinit var applicationsRepository: ApplicationRepository

    override fun get(): Flux<Application> {
        return applicationsRepository.findAll()
    }

    override fun put(app: ApplicationDto): Mono<Application> {
        return applicationsRepository.save(Application(app.id, app.userId, app.amount))
    }

    override fun delete(id: Int): Mono<Void> {
        return applicationsRepository.deleteById(id)
    }
}