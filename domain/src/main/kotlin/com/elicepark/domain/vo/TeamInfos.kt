package com.elicepark.domain.vo

import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * @author Brian
 * @since 2023/03/28
 */
@Embeddable
data class TeamInfos(
    @get:Column(name = "home_team_id", nullable = false)
    var homeTeamId: String,
    @get:Column(name = "home_team_name", nullable = false)
    var homeTeamName: String,
    @get:Column(name = "away_team_id", nullable = false)
    var awayTeamId: String,
    @get:Column(name = "away_team_name", nullable = false)
    var awawTeamName: String
)