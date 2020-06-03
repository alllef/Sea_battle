package sample;

/*
https://cs.uwaterloo.ca/~j2avery/post/2019-12-10-javafx/
Adding libraries
Click on File-Project Structure, then click on Libraries.
Click + and add details for your JavaFX installation.

My experience: Select all *jar in C:\APPS\JDK\14-javafx\lib folder. Important: Do not select src.zip

My experience: It should look something like this. Click OK.
    Classes:
        c:\APPS\JDK\14-javafx\lib\javafx.base.jar
        c:\APPS\JDK\14-javafx\lib\javafx.controls.jar
        c:\APPS\JDK\14-javafx\lib\javafx.graphics.jar
        c:\APPS\JDK\14-javafx\lib\javafx.media.jar
        c:\APPS\JDK\14-javafx\lib\javafx.swing.jar
        c:\APPS\JDK\14-javafx\lib\javafx.web.jar
        c:\APPS\JDK\14-javafx\lib\javafx-swt.jar

My experience: Press + above the windows with the list of classes, select and add just
    c:\APPS\JDK\14-javafx\lib\src.zip

My experience: It should look something like this. Click OK.
    Classes:
        c:\APPS\JDK\14-javafx\lib\javafx.base.jar
        ...
    Sources:
        c:\APPS\JDK\14-javafx\lib\src.zip!\javafx.base
        ...

My experience: It appears, the IDE places --add-modules ... automatically
        C:\APPS\JDK\14-zulu\bin\java.exe --add-modules javafx.base,javafx.graphics --add-reads javafx.base=ALL-UNNAMED --add-reads javafx.graphics=ALL-UNNAMED ...

PRB: Caused by: java.lang.IllegalAccessError: superclass access check failed: class com.sun.javafx.scene.control.ControlHelper (in unnamed module @0x745dec66) cannot access class com.sun.javafx.scene.layout.RegionHelper (in module javafx.graphics) because module javafx.graphics does not export com.sun.javafx.scene.layout to unnamed module @0x745dec66
WO 1: To fix this, we need to modify the Run Configuration
so that the Java Virtual Machine (JVM) can find the Java FX libraries at runtime.
    Click on Run-Edit Configurations. If you see an entry for HelloFX, go ahead and select it, otherwise click on the + sign and add a configuration for an Application.
    It should look like this:
Add the following to the VM options, using the environment variable that we setup earlier to point to your JAVA_FX installation directory. Click OK when finished.
    --module-path ${JAVA_FX_HOME}/lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.web
My experience:
    --module-path c:\APPS\JDK\14-javafx\lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.web

Also see.
https://metanit.com/java/javafx/1.2.php
 */
