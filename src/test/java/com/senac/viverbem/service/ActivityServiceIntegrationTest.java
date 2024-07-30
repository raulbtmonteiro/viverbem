package com.senac.viverbem.service;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.activity.ActivityDTO;
import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.user.UserDTO;
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
public class ActivityServiceIntegrationTest {

    private ActivityService underTest;

    @Autowired
    public ActivityServiceIntegrationTest(ActivityService underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatActivityCanBeCreatedAndRecalled(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserDTO userA = TestDataUtil.createTestUserA(addressA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityDTO activity = TestDataUtil.createTestActivityA(addressB, userA);
        underTest.saveActivity(activity);
        Optional<ActivityDTO> result = underTest.getActivityById(activity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(activity);
    }

    @Test
    public void testThatMultipleActivitiesCanBeCreatedAndRecalled(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserDTO userA = TestDataUtil.createTestUserA(addressA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityDTO activityA = TestDataUtil.createTestActivityA(addressB, userA);
        underTest.saveActivity(activityA);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        UserDTO userB = TestDataUtil.createTestUserB(addressC);
        AddressDTO addressD = TestDataUtil.createTestAddressD();
        ActivityDTO activityB = TestDataUtil.createTestActivityB(addressD, userB);
        underTest.saveActivity(activityB);
        AddressDTO addressE = TestDataUtil.createTestAddressE();
        UserDTO userC = TestDataUtil.createTestUserC(addressE);
        AddressDTO addressF = TestDataUtil.createTestAddressF();
        ActivityDTO activityC = TestDataUtil.createTestActivityC(addressF, userC);
        underTest.saveActivity(activityC);

        List<ActivityDTO> result = underTest.getAllActivities();
        assertThat(result)
                .hasSize(3)
                .containsOnly(activityA, activityB, activityC);

    }

    @Test
    public void testThatActivityCanBeUpdated(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserDTO userA = TestDataUtil.createTestUserA(addressA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityDTO activity = TestDataUtil.createTestActivityA(addressB, userA);
        underTest.saveActivity(activity);
        activity.setTitle("Updated title");
        underTest.saveActivity(activity);
        Optional<ActivityDTO> result = underTest.getActivityById(activity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Updated title");
        assertThat(result.get()).isEqualTo(activity);
    }

    @Test
    public void testThatActivityCanBeCreatedAndDeleted(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserDTO userA = TestDataUtil.createTestUserA(addressA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityDTO activityA = TestDataUtil.createTestActivityA(addressB, userA);
        underTest.saveActivity(activityA);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        UserDTO userB = TestDataUtil.createTestUserB(addressC);
        AddressDTO addressD = TestDataUtil.createTestAddressD();
        ActivityDTO activityB = TestDataUtil.createTestActivityB(addressD, userB);
        underTest.saveActivity(activityB);

        underTest.deleteActivity(activityA.getId());

        List<ActivityDTO> result = underTest.getAllActivities();
        assertThat(result)
                .hasSize(1)
                .containsOnly(activityB);

    }
}
