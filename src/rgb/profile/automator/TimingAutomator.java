package rgb.profile.automator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import rgb.profile.automator.AutomatorView.Settings;

public class TimingAutomator {

    private final int TIMING_DIVISION = 14;
    private final String FILE_LOCATION = "C:/Users/MC/Downloads/example1.lght"; //File Location
    private final String REGEX = "TimeMark=\"\\d+\\.\\d+\"";

    public void automateTime(Settings settings) {
        FileReader fileReader = null;
        FileOutputStream fileWriter = null;
        try {

            File file = settings.getFile();
            //Read the file
            fileReader = new FileReader(settings.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Gets the increments based on the number of nodes.
            double increment = 1d / settings.getNodes();

            String outputFileName = file.getAbsolutePath().replace(".lght", "-changed.lght");

            String line = null;
            String input = "";
            int ctr = 0;

            //Traverse the file
            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
                if (line.contains("TimeMark")) {
                    System.out.print("Replaced: ");
                    line = line.replaceAll(REGEX, "TimeMark=\"" + (increment * ctr) + "\"");
                    System.out.println(line);

                    ctr = (ctr + 1) % (settings.getNodes() + 1); //increment counter
                }
                input += line + "\n";
            }

            fileWriter = new FileOutputStream(outputFileName);
            fileWriter.write(input.getBytes());
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                fileReader.close();
                fileWriter.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
