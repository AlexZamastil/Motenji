package com.motenji.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.motenji.DTO.JWTDataDTO
import com.motenji.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    private val secret =  "asdsadsad"// System.getenv("JWT_SECRET")
    private val expirationLength = 60 * 60 * 1000
    private val algorithm = Algorithm.HMAC256(secret)

    fun generateToken(user: User): String {
        val now = Date()
        val expirationTime = Date(now.time + expirationLength)

        return JWT.create()
            .withClaim("user_id", user.id)
            .withClaim("username", user.nickname)
            .withIssuedAt(now)
            .withExpiresAt(expirationTime)
            .sign(algorithm)
    }

    fun validateToken(token: String): Boolean {
        try {
            JWT.require(algorithm).build().verify(token)
            return true
        } catch (e: JWTVerificationException) {
            e.printStackTrace()
            return false
        }
    }

    fun getTokenData(token: String): JWTDataDTO? {
        try {
            val decoded = JWT.require(algorithm).build().verify(token)
            return JWTDataDTO(decoded.getClaim("user_id").asLong() ,decoded.getClaim("username").asString(), "user")
        } catch (e: JWTVerificationException) {
            e.printStackTrace()
            return null
        }
    }

    fun isTokenExpired(token: String): Boolean {
        try{
            val decoded = JWT.require(algorithm).build().verify(token)
            val temp = decoded.expiresAt
            return temp.before(Date())
        }
        catch (e: JWTVerificationException) {
            e.printStackTrace()
            return true
        }
    }
}