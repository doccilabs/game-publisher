package com.elicepark.domain.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
@Configuration
class QueryDslConfig(@PersistenceContext private val entityManager: EntityManager) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}