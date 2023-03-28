package com.elicepark.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * @author Brian
 * @since 2023/03/28
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(
    @get:CreatedDate
    @get:Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @get:LastModifiedDate
    @get:Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,
    @get:Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null,
    @get:Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
)