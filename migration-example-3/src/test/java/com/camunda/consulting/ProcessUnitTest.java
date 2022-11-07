package com.camunda.consulting;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.camunda.consulting.delegates.UserNotificationDelegate;

@ExtendWith(ProcessEngineExtension.class)
@Deployment(resources = "TwitterQAProcess-complicated.bpmn")
public class ProcessUnitTest {

  @Test
  public void testScriptTaskFirstTime() {
    ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcessComplicated")
        .startBeforeActivity(findId("Increase counter")).execute();
    assertThat(processInstance).isActive().variables().containsEntry("counter", 1);
  }

  @Test
  public void testScriptTaskSecondTime() {
    ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcessComplicated")
        .startBeforeActivity(findId("Increase counter")).setVariable("counter", 1).execute();
    assertThat(processInstance).isActive().variables().containsEntry("counter", 2);
  }

  @Test
  public void testTaskNameChange() {
    Mocks.register("userNotificationDelegate", new UserNotificationDelegate());
    ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcessComplicated")
        .startBeforeActivity(findId("Review tweet")).setVariable("initiator", "demo").execute();

    assertThat(processInstance).isActive().task().hasName("Review tweet from demo");
  }
  
  @Test
  public void testImplicitConditionImprovementAllowed() {
    Mocks.register("userNotificationDelegate", new UserNotificationDelegate());
    
    identityService().setAuthenticatedUserId("demo");
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("TwitterQAProcessComplicated");
    assertThat(processInstance).isWaitingAt(findId("Write tweet")).task().isAssignedTo("demo");
    
    complete(task(), withVariables("content", "Not good enough"));
    assertThat(processInstance).isWaitingAt(findId("Review tweet")).task().hasCandidateGroup("management").hasName("Review tweet from demo");
    
    complete(task(), withVariables("tweetApproved", "maybe"));
    
    assertThat(processInstance).isWaitingAt(findId("Amend tweet")).hasPassed(findId("Increase counter"));
  }
}
