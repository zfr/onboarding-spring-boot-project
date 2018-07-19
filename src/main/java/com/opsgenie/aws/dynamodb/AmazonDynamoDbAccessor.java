package com.opsgenie.aws.dynamodb;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class AmazonDynamoDbAccessor<T> {

    private AmazonDynamoDB client;

    @PostConstruct
    public void init() {
        final EndpointConfiguration endpointConfiguration = new EndpointConfiguration("http://localhost:3966", "us-west-2");
        client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

    @PreDestroy
    public void destroy() {
        if (client != null) {
            client.shutdown();
        }
    }

    public AmazonDynamoDB getClient() {
        return client;
    }

    public List<T> scan(Class<T> clazz, DynamoDBScanExpression scanExpression) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        return new ArrayList<>(mapper.scan(clazz, scanExpression));
    }

    public T load(Class<T> clazz, Object hashKey) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        return mapper.load(clazz, hashKey);
    }

    public void delete(T entity) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        mapper.delete(entity);
    }

    private DynamoDBMapper getDynamoDBMapper() {
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
//                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING)
                .build();

        return new DynamoDBMapper(client, mapperConfig);
    }
}
