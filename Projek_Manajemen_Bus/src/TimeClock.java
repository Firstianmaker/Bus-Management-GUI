import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

class TimeClock extends JPanel {
    private JLabel timeLabel;

    public TimeClock() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        timeLabel = new JLabel();
        updateClock();

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();

        add(timeLabel);
    }

    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText(sdf.format(new Date()));
    }
}