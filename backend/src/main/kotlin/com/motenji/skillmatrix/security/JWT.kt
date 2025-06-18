package com.motenji.skillmatrix.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.motenji.skillmatrix.DTO.JWTDataDTO
import com.motenji.skillmatrix.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWT {
        private val secret = System.getenv("jwt_secret")
        private val expiration = 60 * 60 * 1000
        private val algorithm = Algorithm.HMAC256(secret)

        fun generateToken(user: User): String {
            val now = Date()
            val expipeTime = Date(now.time + expiration)

        return JWT.create()
            .withClaim("user_id", user.id.toString())
            .withIssuedAt(now)
            .withExpiresAt(expipeTime)
            .sign(algorithm)
        }


        fun validateToken(token: String): Boolean {
            try {
                val verification = JWT.require(algorithm).build()
                verification.verify(token)
                return true
            } catch (e: JWTVerificationException) {
                println("JWT verification exception: " + e.message)
                return false
            }

        }

        fun getTokenData(token: String): JWTDataDTO {
            val verification = JWT.require(algorithm).build()
            val decoded = verification.verify(token)
            return JWTDataDTO(decoded.getClaim("user_id").asString(), "user")
        }
}