package com.java.smarthost.architecture;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class NamingArchitectureTest extends AbstractArchitectureTest {

    @Test
    public void enumNameShouldEndWithEnum() {
        classes().that()
                .areEnums()
                .should()
                .haveSimpleNameEndingWith("Enum").check(allClassesInApplication);
    }

    @Test
    public void serviceClassNameShouldEndWithServiceImpl() {
        classes().that()
                .areAnnotatedWith(Service.class)
                .should()
                .haveSimpleNameEndingWith("ServiceImpl").check(allClassesInApplication);
    }

    @Test
    public void controllerClassNameShouldEndWithController() {
        classes().that()
                .areAnnotatedWith(RestController.class)
                .should()
                .haveSimpleNameEndingWith("Controller").check(allClassesInApplication);
    }
}
