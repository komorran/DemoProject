package abstractions;

import com.google.inject.Guice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest<T extends AbstractTestModule, M extends AbstractTestSteps> {

    private T testModule;

    @Inject
    protected M testSteps;

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
