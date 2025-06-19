package com.motenji.skillmatrix.repository

import com.motenji.skillmatrix.model.Contribution
import com.motenji.skillmatrix.model.Goal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContributionRepository : JpaRepository<Contribution, Long>{
    fun findAllByGoal(goal: Goal): MutableList<Contribution>
    fun findByContributionId(contributionId: Long): Contribution?
}
