package avaj_launcher;

import avaj_launcher.simulator.Simulator;
import avaj_launcher.simulator.SimulatorData;
import avaj_launcher.util.Logger;
import avaj_launcher.util.Parser;

public class AvajLauncher {
  public static void main(String args[]) {
    if (args.length != 1) {
      System.out.println("scenario file is needed\n");
      return;
    }

    try {
      Logger.initailize("simulation.txt");
      // do simulations
      {
        String fileName = args[0];
        Parser parser = new Parser();
        SimulatorData simulatorData = parser.parse(fileName);
        Simulator simulator = new Simulator(simulatorData);
        simulator.simulate();
      }
      Logger.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
