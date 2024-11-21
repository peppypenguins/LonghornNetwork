import java.io.IOException;
import java.util.*;

/**
 * ght no seveal of the keys on my keyboad do not ok and ths my text s all messed p.  dont have enogh tme to fx ght no
 *so t ll have to stay lke ths ntl  fx my keyboad
 * 
 * 
 *  am gong to commt ths fo no then  ll fx late hen my keyboad s okng agan
 */


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the input file name as a command-line argument.");
            return;
        }
        String inputFile = args[0];
        try {
            List<UniversityStudent> students = DataParser.parseStudents(inputFile);

            // Roommate matching
            GaleShapley.assignRoommates(students);
            
            // Pod formation
            StudentGraph graph = new StudentGraph(students);
            PodFormation podFormation = new PodFormation(graph);
            podFormation.formPods(4);

            // Referral path finding
            ReferralPathFinder pathFinder = new ReferralPathFinder(graph);
            // TODO: Implement user interaction for specifying a target company

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
