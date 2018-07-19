package com.opsgenie.onboarding.user.support;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.opsgenie.aws.dynamodb.AmazonDynamoDbAccessor;
import com.opsgenie.onboarding.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "amazonUserRepository")
public class AmazonUserRepository implements UserRepository {

    private final AmazonDynamoDbAccessor accessor;

    @Autowired
    public AmazonUserRepository(AmazonDynamoDbAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public List<User> listUsers() {
        DynamoDBMapper mapper = getDynamoDBMapper();
        return mapper.scan(User.class, new DynamoDBScanExpression());
    }

    @Override
    public User getUser(String userId) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        return mapper.load(User.class, userId);
    }

    @Override
    public User addUser(User user) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        mapper.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        mapper.save(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        DynamoDBMapper mapper = getDynamoDBMapper();
        mapper.delete(user);
    }

    private DynamoDBMapper getDynamoDBMapper() {
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
//                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING)
                .build();

        return new DynamoDBMapper(accessor.getClient(), mapperConfig);
    }
}
