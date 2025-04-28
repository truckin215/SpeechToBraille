1. External Depdendencies:
Requires instlation and linking of javaFX. Can be done using eclipse plugin i believe.
the project files include javaFX in the libs, and just requries the VM option:
--module-path "C:\Users\conno\Documents\School\Software\SpeechToBraille\SpeechtoBraille\libs\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml 
replacing the location with the location on your pc.
All other dependencies are maven dependencies and should be downloaded from pom. if not please install:
###
    		<groupId>com.assemblyai</groupId>
    		<artifactId>assemblyai-java</artifactId>
    		<version>4.0.1</version>

     		<groupId>org.junit.jupiter</groupId>
       		<artifactId>junit-jupiter-api</artifactId>
       		<version>5.9.3</version>
      	    <scope>test</scope>

            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.3</version>
            <scope>test</scope>

            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-launcher</artifactId>
            <version>1.9.3</version>
            <scope>test</scope>

  	        <groupId>com.pi4j</groupId>
  	        <artifactId>pi4j-core</artifactId>
  	        <version>2.3.0</version>

2. Run Configuration
runconfig is stored in SpeechtoBraille/.idea/runConfigurations
here is a copy:
<component name="ProjectRunConfigurationManager">
  <configuration default="false" name="main" type="Application" factoryName="Application">
    <option name="MAIN_CLASS_NAME" value="speech.Controller" />
    <module name="SpeechToBraille" />
    <option name="VM_PARAMETERS" value="--module-path &quot;C:\Users\conno\Documents\School\Software\SpeechToBraille\SpeechtoBraille\libs\javafx-sdk-24.0.1\lib&quot; --add-modules javafx.controls,javafx.fxml " />
    <method v="2">
      <option name="Make" enabled="true" />
    </method>
  </configuration>
</component>

Not will likley need updating of vm parameters to local location.

3. Deviations from proposal

* Speech to text was switched to external API AsembleyAI over using java CMU Sphinx 
  This was mainly due to the fact that I actually wanted to complete the project not spend a year writing an AI model
* Graphical User interface does not monitor rasberryPI status, allow writing in input, adjust output speed, display errors
  The UI was the last thing implemented so it by far has the most cut due to time constraints.

4. Design Patterns
Some design patterns used are:
Facade:
	The Gui Controller provides a simple interface between the view layer and the more complex subsystems.
Observer:
	The Gui acts as a observer geting updated on the state of the transcriber, and braille outputs
Singleton:
	This is a little bit of stretch but the GUI controller does act as a single global controller with enforced one instance.

5. Test Design
Test cases were designed almost exclusively after the creation of each method, focusing on validating external components like the transcriber and simple, easily verifiable functions.

In truth, the project did not follow strict Test-Driven Development (TDD); rather, it followed more of a Test-Verified Development approach, where testing was used primarily to confirm functionality after implementation.