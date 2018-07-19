package com.opsgenie.onboarding.team.support;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.opsgenie.aws.dynamodb.AmazonDynamoDbAccessor;
import com.opsgenie.onboarding.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "amazonTeamRepository")
public class AmazonTeamRepository implements TeamRepository {

    private final AmazonDynamoDbAccessor<Team> accessor;

    @Autowired
    public AmazonTeamRepository(AmazonDynamoDbAccessor<Team> accessor) {
        this.accessor = accessor;
    }

    @Override
    public List<Team> listTeams() {
        return accessor.scan(Team.class, new DynamoDBScanExpression());
    }

    @Override
    public Team getTeam(String teamId) {
        return accessor.load(Team.class, teamId);
    }

    @Override
    public Team addTeam(Team team) {
        DynamoDB dynamoDB = new DynamoDB(accessor.getClient());

        Table table = dynamoDB.getTable("onboardingTeam");

        // Build the item
        Item item = new Item()
                .withPrimaryKey("id", team.getId())
                .withString("name", team.getName());

        // better empty check ?
        if (team.getUserIds() != null && !team.getUserIds().isEmpty()) {
            item.withStringSet("userIds", team.getUserIds());
        }

        table.putItem(item);

        return getTeam(team.getId());

//        DynamoDBMapper mapper = getDynamoDBMapper();
//        mapper.save(team);
//        return team;
    }

    @Override
    public Team updateTeam(Team team) {
        DynamoDB dynamoDB = new DynamoDB(accessor.getClient());

        Table table = dynamoDB.getTable("onboardingTeam");

        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                .withPrimaryKey("id", team.getId())
                .withAttributeUpdate(new AttributeUpdate("name").put(team.getName()));

        // better empty check ?
        if (team.getUserIds() != null && !team.getUserIds().isEmpty()) {
            updateItemSpec.addAttributeUpdate(new AttributeUpdate("userIds").addElements(team.getUserIds().toArray()));
        } else {
            updateItemSpec.addAttributeUpdate(new AttributeUpdate("userIds").delete());
        }

        table.updateItem(updateItemSpec);

        return getTeam(team.getId());

//        DynamoDBMapper mapper = getDynamoDBMapper();
//        mapper.save(team);
//        return team;
    }

    @Override
    public void deleteTeam(Team team) {
        accessor.delete(team);
    }
}
