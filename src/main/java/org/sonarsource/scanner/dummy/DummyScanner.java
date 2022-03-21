package org.sonarsource.scanner.dummy;

import java.util.Map;
import org.sonarsource.scanner.api.EmbeddedScanner;

public class DummyScanner {

  public static void main(String[] args) {
    try {
      EmbeddedScanner embeddedScanner = EmbeddedScanner.create("Dummy Scanner", "1.0", (formattedMessage, level) -> System.out.println(level.name() + " " + formattedMessage));

      embeddedScanner.addGlobalProperties(Map.of("sonar.host.url", "http://localhost:9000", "sonar.login", "admin", "sonar.password", "admin2"));
      embeddedScanner.start();

      String serverVersion = embeddedScanner.serverVersion();
      System.out.println("Server version: " + serverVersion);

      embeddedScanner.execute(Map.of("sonar.projectKey", "dummy"));
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

}
