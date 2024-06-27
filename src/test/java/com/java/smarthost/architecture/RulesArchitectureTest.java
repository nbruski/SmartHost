package com.java.smarthost.architecture;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class RulesArchitectureTest extends AbstractArchitectureTest {

    @Test
    public void shouldNotBeCyclesInApplication() {
        slices().matching("com.java.smarthost").should().beFreeOfCycles();
    }

    @Test
    public void classesShouldNotThrowGenericExceptions() {
        NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS.check(allClassesInApplication);
    }

    @Test
    public void servicesShouldNotBeAccessedDirectlyExceptForTests() {
        noClasses()
                .that().haveSimpleNameNotEndingWith("Test")
                .should()
                .dependOnClassesThat()
                .areAnnotatedWith(Service.class)
                .check(allClassesInApplication);
    }
}
