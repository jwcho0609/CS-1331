//I worked on the homework assignment alone, using only course materials.

import java.io.*;
import java.util.Scanner;

public class Grep {
    public static void main(String[] args) {
        try {
            if (args.length < 2 || args.length > 3) {
                throw new IllegalArgumentException();
            }
            if (args[0].equals("-i")) {
                File f1 = new File(args[2]);
                String g = grep(f1, args[1], false);
                System.out.println(g);
            } else {
                File f1 = new File(args[1]);
                String g = grep(f1, args[0], true);
                System.out.println(g);
            }
        } catch (InvalidSearchStringException isse) {
            System.out.println("Not able to find string.");
        } catch (IOException ioe) {
            System.out.println("IOException occurred.");
        /*} catch (FileNotFoundException fnf) {
            System.out.println("File not found."); */
        }
    }

    public static String grep(File file, String str, boolean bool) throws FileNotFoundException, InvalidSearchStringException {
        if (str.contains("\n")) {
            throw new InvalidSearchStringException();
        }
        if (file.isDirectory()) {
            String end = "";
            File[] fileList = file.listFiles();
            for (File fil : fileList) {
                end += grep(fil, str, bool);
            }
            return end;
        } else {
            Scanner scan = new Scanner(file);
            String fin = "";
            int count = 1;
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                if (bool) {
                    if (s.contains(str)) {
                        fin = fin + file.toString() + ":" + count + ":" + s + "\n";
                    }
                    count++;
                } else {
                    s = s.toLowerCase();
                    str = str.toLowerCase();
                    if (s.contains(str)) {
                        fin = fin + file.toString() + ":" + count + ":" + s + "\n";
                    }
                    count++;
                }
            }
            return fin;
        }
    }
}