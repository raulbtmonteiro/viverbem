package com.senac.viverbem.service;

import com.senac.viverbem.TestDataUtil;
import com.senac.viverbem.domain.activity.dto.ActivityDTO;
import com.senac.viverbem.domain.activity.dto.ActivityPostDTO;
import com.senac.viverbem.domain.address.dto.AddressDTO;
import com.senac.viverbem.domain.user.dto.UserDTO;
import com.senac.viverbem.domain.user.dto.UserPostDTO;
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
    private UserService userService;

    @Autowired
    public ActivityServiceIntegrationTest(ActivityService underTest, UserService userService){
        this.underTest = underTest;
        this.userService = userService;
    }

    @Test
    public void testThatActivityCanBeCreatedAndRecalled(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserPostDTO userA = TestDataUtil.createTestUserA(addressA);
        userService.saveUser(userA);
        UserDTO savedUserA = new UserDTO(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityPostDTO activity = TestDataUtil.createTestActivityA(addressB, savedUserA.getId());
        underTest.saveActivity(activity);
        ActivityDTO savedActivity = new ActivityDTO(activity,savedUserA);
        Optional<ActivityDTO> result = underTest.getActivityById(activity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedActivity);
    }

    @Test
    public void testThatMultipleActivitiesCanBeCreatedAndRecalled(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserPostDTO userA = TestDataUtil.createTestUserA(addressA);
        userService.saveUser(userA);
        UserDTO savedUserA = new UserDTO(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityPostDTO activityA = TestDataUtil.createTestActivityA(addressB, savedUserA.getId());
        underTest.saveActivity(activityA);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        UserPostDTO userB = TestDataUtil.createTestUserB(addressC);
        userService.saveUser(userB);
        UserDTO savedUserB = new UserDTO(userB);
        AddressDTO addressD = TestDataUtil.createTestAddressD();
        ActivityPostDTO activityB = TestDataUtil.createTestActivityB(addressD, savedUserB.getId());
        underTest.saveActivity(activityB);
        AddressDTO addressE = TestDataUtil.createTestAddressE();
        UserPostDTO userC = TestDataUtil.createTestUserC(addressE);
        userService.saveUser(userC);
        UserDTO savedUserC = new UserDTO(userC);
        AddressDTO addressF = TestDataUtil.createTestAddressF();
        ActivityPostDTO activityC = TestDataUtil.createTestActivityC(addressF, savedUserC.getId());
        underTest.saveActivity(activityC);

        ActivityDTO savedActivityA = new ActivityDTO(activityA,savedUserA);
        ActivityDTO savedActivityB = new ActivityDTO(activityB,savedUserB);
        ActivityDTO savedActivityC = new ActivityDTO(activityC,savedUserC);

        List<ActivityDTO> result = underTest.getAllActivities();
        assertThat(result)
                .hasSize(3)
                .containsOnly(savedActivityA, savedActivityB, savedActivityC);

    }

    @Test
    public void testThatActivityCanBeUpdated(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserPostDTO userA = TestDataUtil.createTestUserA(addressA);
        userService.saveUser(userA);
        UserDTO savedUserA = new UserDTO(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityPostDTO activity = TestDataUtil.createTestActivityA(addressB, savedUserA.getId());
        underTest.saveActivity(activity);
        activity.setTitle("Updated title");
        underTest.saveActivity(activity);
        ActivityDTO savedActivity = new ActivityDTO(activity,savedUserA);
        Optional<ActivityDTO> result = underTest.getActivityById(activity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Updated title");
        assertThat(result.get()).isEqualTo(savedActivity);
    }

    @Test
    public void testThatActivityCanBeCreatedAndDeleted(){
        AddressDTO addressA = TestDataUtil.createTestAddressA();
        UserPostDTO userA = TestDataUtil.createTestUserA(addressA);
        userService.saveUser(userA);
        UserDTO savedUserA = new UserDTO(userA);
        AddressDTO addressB = TestDataUtil.createTestAddressB();
        ActivityPostDTO activityA = TestDataUtil.createTestActivityA(addressB, savedUserA.getId());
        underTest.saveActivity(activityA);
        AddressDTO addressC = TestDataUtil.createTestAddressC();
        UserPostDTO userB = TestDataUtil.createTestUserB(addressC);
        userService.saveUser(userB);
        UserDTO savedUserB = new UserDTO(userB);
        AddressDTO addressD = TestDataUtil.createTestAddressD();
        ActivityPostDTO activityB = TestDataUtil.createTestActivityB(addressD, savedUserB.getId());
        underTest.saveActivity(activityB);

        underTest.deleteActivity(activityA.getId());

        ActivityDTO savedActivityB = new ActivityDTO(activityB,savedUserB);

        List<ActivityDTO> result = underTest.getAllActivities();
        assertThat(result)
                .hasSize(1)
                .containsOnly(savedActivityB);

    }
}
