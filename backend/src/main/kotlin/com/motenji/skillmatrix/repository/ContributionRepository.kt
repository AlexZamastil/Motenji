package com.motenji.skillmatrix.repository

import com.motenji.skillmatrix.model.Contribution
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContributionRepository : JpaRepository<Contribution, Long>{

}
