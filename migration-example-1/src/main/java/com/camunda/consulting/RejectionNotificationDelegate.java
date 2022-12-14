package com.camunda.consulting;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RejectionNotificationDelegate implements JavaDelegate {

  private static final Logger LOG = LoggerFactory.getLogger(RejectionNotificationDelegate.class);

  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("execute {} of process instance {}", 
        execution.getCurrentActivityName(), 
        execution.getProcessInstanceId());
  }

}
