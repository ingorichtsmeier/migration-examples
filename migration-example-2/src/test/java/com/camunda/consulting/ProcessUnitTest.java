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
@Deployment(resources = "TwitterQAProcess-complex.bpmn")
public class ProcessUnitTest {

  @Test
  public void testScriptTaskFirstTime() {
    ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcessComplex")
        .startBeforeActivity(findId("Increase counter")).execute();
    assertThat(processInstance).isActive().variables().containsEntry("counter", 1);
  }

  @Test
  public void testScriptTaskSecondTime() {
    ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcessComplex")
        .startBeforeActivity(findId("Increase counter")).setVariable("counter", 1).execute();
    assertThat(processInstance).isActive().variables().containsEntry("counter", 2);
  }

  @Test
  public void testTaskNameChange() {
    Mocks.register("userNotificationDelegate", new UserNotificationDelegate());
    ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcessComplex")
        .startBeforeActivity(findId("Review tweet")).setVariable("initiator", "demo").execute();

    assertThat(processInstance).isActive().task().hasName("Review tweet from demo");
  }
}
