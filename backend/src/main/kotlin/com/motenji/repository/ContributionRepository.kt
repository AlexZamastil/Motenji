package com.motenji.repository

import com.motenji.model.Contribution
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContributionRepository : CoroutineCrudRepository<Contribution, Long>{
    suspend fun findAllByGoalId(goalId: Long?): MutableList<Contribution>?
    suspend fun findByContributionId(contributionId: Long?): Contribution?
}
