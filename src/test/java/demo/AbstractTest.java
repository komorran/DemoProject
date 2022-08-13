package demo;

import com.google.inject.Guice;
import demo.modules.AbstractTestModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractTest<T extends AbstractTestModule> {

    private T testModule;

    protected abstract T getModule();

    @BeforeAll
    public void configure() {
        this.testModule = getModule();
    }

    @BeforeEach
    public void setup() {
        Guice.createInjector(testModule)
                .injectMembers(this);
    }
}
