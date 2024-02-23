import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ApproveButtonListener implements ActionListener {
    private String inputFile;
    private String outputFile;

    public ApproveButtonListener(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            SalesProcess.filterUnapprovedRows(inputFile, outputFile);
            System.out.println("Filtered rows copied to " + outputFile);
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
