package com.camunda.consulting.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserNotificationDelegate implements JavaDelegate, TaskListener {

  private static final Logger LOG = LoggerFactory.getLogger(UserNotificationDelegate.class);

  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("Notification for user ");
  }

  public void notify(DelegateTask delegateTask) {
    LOG.info("Notification for user ");    
  }

}
