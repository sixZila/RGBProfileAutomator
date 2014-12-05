package rgb.profile.automator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rgb.profile.automator.AutomatorView.Settings;

public class AutomatorControlCenter {

    private final AutomatorView UI;
    private final TimingAutomator automator;

    public AutomatorControlCenter() {
        UI = new AutomatorView();
        automator = new TimingAutomator();
    }

    public void initialize() {
        UI.initialize();
        UI.setStartButtonAction(new StartAction());
    }

    class StartAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (UI.checkInput()) {
                Settings settings = UI.getFile();
                if (settings != null) {
                    automator.automateTime(settings);
                    UI.notifySuccess();
                }
            }
        }

    }
}
