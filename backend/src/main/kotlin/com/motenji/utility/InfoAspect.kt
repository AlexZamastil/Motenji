package com.motenji.utility

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class InfoAspect {
    @Around("execution(* com.motenji.service..*(..))")
    fun printMethodInfo(joinPoint: ProceedingJoinPoint): Any? {
        val startTime = System.nanoTime()
        val methodRun = joinPoint.proceed()
        val elapsedTime = System.nanoTime() - startTime
        println("Method: ${joinPoint.signature} with arguments ${joinPoint.args} elapsedTime: ${elapsedTime/1000000} ms")
        return methodRun
    }
}