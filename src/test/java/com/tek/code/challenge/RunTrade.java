package com.tek.code.challenge;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature/",glue ="com.tek.code.challenge.steps",
        format = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"})
public class RunTrade {
}
