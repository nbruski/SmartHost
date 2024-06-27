package com.java.smarthost.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public class AbstractArchitectureTest {

    protected final JavaClasses allClassesInApplication = new ClassFileImporter().importPackages(
            "com.java.smarthost");
}
