package lexico;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Cacovsky
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestCodeBufferBlockComment.class,
    TestCodeBufferInlineComment.class,
    TestCodeBufferSimpleCode.class,
    TestDelimiterAutomaton.class,
    TestIdentifierAutomaton.class,
    TestLexico.class,
    TestNumericAutomaton.class,
    TestOperatorAutomaton.class,
    TestStringAutomaton.class})
public class CompilostTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}