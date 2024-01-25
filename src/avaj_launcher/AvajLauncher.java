package avaj_launcher;

import java.io.IOException;

import avaj_launcher.simulator.ScenarioParser;
import avaj_launcher.simulator.Simulator;
import avaj_launcher.simulator.SimulatorData;
import avaj_launcher.utils.logger.LogStrategyFactory;
import avaj_launcher.utils.logger.Logger;

public class AvajLauncher {
  private static void initializeLogger() {
    try {
      Logger.initailize(
          LogStrategyFactory.getFileLogStrategy("simulation.txt"),
          LogStrategyFactory.getPrintStreamLogStrategy(System.err),
          LogStrategyFactory.getPrintStreamLogStrategy(System.err),
          LogStrategyFactory.getPrintStreamLogStrategy(System.err));
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  public static void main(String args[]) {
    if (args.length != 1) {
      System.err.println("Avaj-launcher: scenario file is needed\n");
      return;
    }
    initializeLogger();

    try {
      String fileName = args[0];
      ScenarioParser parser = new ScenarioParser();
      SimulatorData simulatorData = parser.parse(fileName);
      Simulator simulator = new Simulator(simulatorData);

      simulator.simulate();
    } catch (Exception e) {
      Logger.err(e.getMessage());
    } finally {
      Logger.close();
    }
  }
}
