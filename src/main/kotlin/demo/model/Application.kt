package demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection="applications")
data class Application (
    @Id
    val id: Int,
    val name: String,
    val amount: BigDecimal
)