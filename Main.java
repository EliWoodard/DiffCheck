import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
        
    public static void main(String[] args) {
        String list1File = "Inputted-Lists/List1.txt";
        String list2File = "Inputted-Lists/List2.txt";
        String outputFile = "Outputted-List/emails-not-added.txt";
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        Map<String, Boolean> emailMap = new HashMap<>();
        
        // Read List1.txt into list1
        try (BufferedReader reader = new BufferedReader(new FileReader(list1File))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list1.add(line.replaceAll("\\s", "").toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Read List2.txt into list2
        try (BufferedReader reader = new BufferedReader(new FileReader(list2File))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list2.add(line.replaceAll("\\s", "").toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Add all emails in list1 to emailMap with value false
        for (String email : list1) {
            emailMap.put(email, false);
        }
        
        // Mark all emails in list2 as true in emailMap if they exist
        for (String email : list2) {
            if (emailMap.containsKey(email)) {
                emailMap.put(email, true);
            }
        }
        
        // Write all emails in emailMap with value false to outputFile
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (Map.Entry<String, Boolean> entry : emailMap.entrySet()) {
                if (!entry.getValue()) {
                    writer.write(entry.getKey() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        System.out.println("Email diff check complete. See " + outputFile + " for results.");
    }
}
