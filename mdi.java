import lexical.LexicalAnalysis;
import syntatic.SyntaticAnalysis;

public class mdi {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java mdi [QML file]");
            return;
        }

        try (LexicalAnalysis l = new LexicalAnalysis(args[0])) {

            SyntaticAnalysis s = new SyntaticAnalysis(l);
            s.start();
            System.out.println("Sim");
            
        } catch (Exception e) {
            System.err.println("Internal error: " + e.getMessage());
        }
    }

}
