package es.dionisiocortes.cucumberjunit.bdd;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("es/dionisiocortes/cucumberjunit/bdd")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "es.dionisiocortes.cucumberjunit.bdd")
public class CucumberIT {
}
