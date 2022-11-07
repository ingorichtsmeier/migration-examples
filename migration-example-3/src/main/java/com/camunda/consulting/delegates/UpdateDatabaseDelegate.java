package com.camunda.consulting.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateDatabaseDelegate implements JavaDelegate {

  private static final Logger LOG = LoggerFactory.getLogger(UpdateDatabaseDelegate.class);

  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("Update the database");
  }

}
