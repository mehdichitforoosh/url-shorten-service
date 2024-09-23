package com.example.url_shorten_service.service.generator

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant


@Service
class UniqueIdGenerator {

    private val UNUSED_BITS = 0L // Sign bit, Unused
    private val EPOCH_BITS = 32
    private val NODE_ID_BITS = 6 // values from 0 to 63
    private val SEQUENCE_BITS = 3 // which means at the same time in milliseconds 8 ids can be generated
    private val MAX_SEQUENCE = (1L shl SEQUENCE_BITS) - 1L
    private val MAX_NODE_ID = (1L shl NODE_ID_BITS) - 1L

    @Value("\${id-generator.node-id}")
    private var nodeId: Long = 0

    @Value("\${id-generator.custom-epoch}")
    private var customEpoch: Long = 1_726_852_965_000L  // Fri Sep 20 2024 17:22:45 GMT+0000

    private var lastTimestamp: Long = -1L
    private var sequence: Long = -1L

    @PostConstruct
    fun validateProperty() {
        require(!(nodeId < 0 || nodeId > MAX_NODE_ID)) { "NodeId must be between 0 and $MAX_NODE_ID" }
        require(customEpoch >= 0) { "Custom epoch must be greater or equals to 0" }
    }

    @Synchronized         // Critical section which needs synchronization
    fun generateId(): Long {
        var currentTimestamp = getTimestamp()
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) and MAX_SEQUENCE
            if (sequence == 0L) {
                currentTimestamp = waitNextMillis()
            }
        } else {
            sequence = 0L
        }
        lastTimestamp = currentTimestamp
        return (UNUSED_BITS shl (EPOCH_BITS + NODE_ID_BITS + SEQUENCE_BITS)
                or currentTimestamp shl (NODE_ID_BITS + SEQUENCE_BITS)
                or (nodeId shl SEQUENCE_BITS)
                or sequence)
    }

    private fun getTimestamp(): Long {
        // Get current timestamp in milliseconds and reduce custom epoch
        val timestamp = Instant.now().toEpochMilli() - customEpoch
        // This is a long with the lower bits which their length equals to EPOCH_BITS are set to 1
        val mask = (1L shl EPOCH_BITS) - 1L
        // return lower 32 bits as a long value
        return timestamp and mask
    }

    private fun waitNextMillis(): Long {
        var currentTimestamp: Long
        do {
            currentTimestamp = getTimestamp()
        } while (currentTimestamp == lastTimestamp)
        return currentTimestamp
    }

}