package com.camunda.consulting.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaveProposalDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(SaveProposalDelegate.class);

  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("Saving the proposal {}", execution.getVariable("content"));
  }

}
