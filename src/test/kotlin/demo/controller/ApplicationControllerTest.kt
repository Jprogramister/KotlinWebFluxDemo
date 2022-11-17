package demo.controller

import BaseIntegrationTest
import demo.dto.ApplicationDto
import demo.model.Application
import demo.repository.ApplicationRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.math.BigDecimal

class ApplicationControllerTest : BaseIntegrationTest() {
    @Autowired var client: WebTestClient? = null
    @Autowired var repo: ApplicationRepository? = null

    @Test
    fun given_newApplicationDto_when_put_then_repoFindById() {
        val body = ApplicationDto(1, "userId", BigDecimal.ZERO)
        client!!.put()
            .uri("/application")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(body), ApplicationDto::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(body.id)
            .jsonPath("$.userId").isEqualTo(body.userId)
            .jsonPath("$.amount").isEqualTo(body.amount)

        Assertions.assertTrue(repo!!.findById(body.id).block() != null)
    }

    @Test
    fun given_ApplicationDatabase_when_get_then_ApplicationDto() {
        repo!!.save(Application(1, "one", BigDecimal.ZERO)).block()
        repo!!.save(Application(2, "two", BigDecimal.ZERO)).block()
        repo!!.save(Application(3, "three", BigDecimal.ZERO)).block()

        client!!.get()
            .uri("/application")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.length()").isEqualTo(3)
    }
}