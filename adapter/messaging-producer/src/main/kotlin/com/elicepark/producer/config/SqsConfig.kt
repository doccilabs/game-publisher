package com.elicepark.producer.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Brian
 * @since 2023/04/08
 */
@Configuration
class SqsConfig(
    @Value("\${cloud.aws.region.static}") private val region: String,
    @Value("\${cloud.aws.credentials.accessKey}") private val accessKey: String,
    @Value("\${cloud.aws.credentials.secretKey}") private val secretKey: String
) {
    // For Amazon SQS Configuration
    @Bean
    fun amazonSQS(): AmazonSQSAsync = AmazonSQSAsyncClientBuilder.standard()
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(accessKey, secretKey)
            )
        )
        .withRegion(region)
        .build()
}