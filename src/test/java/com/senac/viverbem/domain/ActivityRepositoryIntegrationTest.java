package com.senac.viverbem.domain;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.activity.ActivityRepository;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.user.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ActivityRepositoryIntegrationTest {

    private ActivityRepository underTest;

    @Autowired
    public ActivityRepositoryIntegrationTest(ActivityRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatActivityCanBeCreatedAndRecalled(){
        AddressModel address = TestDataUtil.createTestAddressA();
        UserModel user = TestDataUtil.createTestUserA(address);
        ActivityModel activity = TestDataUtil.createTestActivityA(address, user);
        underTest.save(activity);
        Optional<ActivityModel> result = underTest.findById(activity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(activity);
    }

    @Test
    public void testThatMultipleActivitiesCanBeCreatedAndRecalled(){
        AddressModel addressA = TestDataUtil.createTestAddressA();
        UserModel userA = TestDataUtil.createTestUserA(addressA);
        ActivityModel activityA = TestDataUtil.createTestActivityA(addressA, userA);
        underTest.save(activityA);
        AddressModel addressB = TestDataUtil.createTestAddressB();
        UserModel userB = TestDataUtil.createTestUserB(addressB);
        ActivityModel activityB = TestDataUtil.createTestActivityB(addressB, userB);
        underTest.save(activityB);
        AddressModel addressC = TestDataUtil.createTestAddressC();
        UserModel userC = TestDataUtil.createTestUserC(addressC);
        ActivityModel activityC = TestDataUtil.createTestActivityC(addressC, userC);
        underTest.save(activityC);

        List<ActivityModel> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsOnly(activityA, activityB, activityC);

    }

    @Test
    public void testThatActivityCanBeUpdated(){
        AddressModel address = TestDataUtil.createTestAddressA();
        UserModel user = TestDataUtil.createTestUserA(address);
        ActivityModel activity = TestDataUtil.createTestActivityA(address, user);
        underTest.save(activity);
        activity.setTitle("Updated title");
        underTest.save(activity);
        Optional<ActivityModel> result = underTest.findById(activity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Updated title");
        assertThat(result.get()).isEqualTo(activity);
    }

    @Test
    public void testThatActivityCanBeCreatedAndDeleted(){
        AddressModel addressA = TestDataUtil.createTestAddressA();
        UserModel userA = TestDataUtil.createTestUserA(addressA);
        ActivityModel activityA = TestDataUtil.createTestActivityA(addressA, userA);
        underTest.save(activityA);
        AddressModel addressB = TestDataUtil.createTestAddressB();
        UserModel userB = TestDataUtil.createTestUserB(addressB);
        ActivityModel activityB = TestDataUtil.createTestActivityB(addressB, userB);
        underTest.save(activityB);

        underTest.deleteById(activityA.getId());

        List<ActivityModel> result = underTest.findAll();
        assertThat(result)
                .hasSize(1)
                .containsOnly(activityB);

    }
}
