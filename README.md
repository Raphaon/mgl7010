You are a Java expert assistant trained to generate working Java code that strictly respects

the given interfaces, type signatures, and JUnit 5 tests.  



Given the Java interfaces and test cases below, produce the \*\*full Java implementation classes\*\*

required by the project. Your output must:



&nbsp; • Only include valid Java code for implementation classes (no test code).

&nbsp; • Place each class in the correct package as dictated by the interfaces/tests.

&nbsp; • Match all method signatures exactly (no extra methods).

&nbsp; • Respect visibility (public/non-public) and data encapsulation.

&nbsp; • Throw appropriate exceptions where required.

&nbsp; • Initialize collections to avoid NullPointerExceptions.  

&nbsp; • Compute GPA and credits according to the tests.

&nbsp; • Use only standard Java SE libraries; do not add external dependencies.



Ensure the generated code compiles and passes all given tests.



---



\### Interfaces \& Requirements



// INCLUDE here all the interfaces from the `ca.uqam.mgl7010.tp1.types` package

// For example:





package ca.uqam.mgl7010.tp1.types;



public interface Personne {

String getNom();

String getPrenom();

String getNas();

}







… (include all others like PersonneEtudiante, Programme, Cours, Note, etc.)



---



\### Tests



Include the contents of the test file `TesterCreationPersonnesEtudiantes.java` in this section

so the model can reference assertions on behavior, expected values, and edge cases.



---



\### Implementation Classes to Generate



Generate the following implementations so that all tests pass:



&nbsp; • `ca.uqam.mgl7010.tp1.implementations.PersonneImpl`

&nbsp; • `ca.uqam.mgl7010.tp1.implementations.PersonneEtudiantImpl`

&nbsp; • `ca.uqam.mgl7010.tp1.implementations.ProgrammeImpl`

&nbsp; • `ca.uqam.mgl7010.tp1.implementations.CoursImpl`

&nbsp; • `ca.uqam.mgl7010.tp1.implementations.InscriptionProgrammeImpl`

&nbsp; • `ca.uqam.mgl7010.tp1.implementations.InscriptionCoursImpl`

&nbsp; • Any other required concrete classes referenced by tests



Each class must:



&nbsp; \* Implement the appropriate interface.

&nbsp; \* Follow the tested behavior (constructors, getters, setters).

&nbsp; \* Respect JUnit 5 test expectations.

&nbsp; \* Correctly handle nulls and invalid input where appropriate.



---



\### Additional Notes



&nbsp; \* When dealing with grades and credit calculations, convert grade letters

&nbsp;   to numeric values according to the test expectations (e.g., A+ → 4.3, A → 4.0, etc.)

&nbsp; \* Tests may use Instant or other date/time APIs — be consistent with those.



---



End the prompt by producing \*\*only the Java code for implementation classes\*\*.  

Do not output tests, explanations, or commentary — just the Java source code.



