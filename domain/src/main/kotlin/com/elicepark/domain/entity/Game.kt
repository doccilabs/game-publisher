package com.elicepark.domain.entity

import com.elicepark.domain.enums.Status
import com.elicepark.domain.vo.TImeInfos
import com.elicepark.domain.vo.TeamInfos
import java.time.LocalDate
import javax.persistence.*

/**
 * @author Brian
 * @since 2023/03/28
 */
@Entity
@Table(name = "games")
class Game(
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    @get:Embedded
    var teamInfos: TeamInfos = TeamInfos(),
    @get:Embedded
    var timeInfos: TImeInfos = TImeInfos(),
    @get:Column(name = "register_date", nullable = false)
    var registerDate: LocalDate = LocalDate.now(),
    @get:Column(name = "status", nullable = false)
    var status: Status = Status.READY
) : BaseEntity()