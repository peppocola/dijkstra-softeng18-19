package it.uniba.example.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.uniba.example.utils.math.MathUtils;
import it.uniba.example.utils.strings.StringUtils;

@RunWith(Suite.class)
@SuiteClasses({ MathUtils.class, StringUtils.class })
public class TestSuiteUtils {

}
