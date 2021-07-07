package config;

import org.aeonbits.owner.ConfigFactory;

public class App {

  public static config.AppConfig config = ConfigFactory.create(
      config.AppConfig.class, System.getProperties());
}
