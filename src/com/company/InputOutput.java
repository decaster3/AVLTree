

import java.io.*;

/**
 * Created by rinathatipov on 22.09.16.
 */
public class InputOutput {
    private String line = "";
    private String[] array;
    private String line2 = "";
    public String[] read(String str) {
        try {
            FileReader fr = new FileReader(new File(str));
            BufferedReader reader = new BufferedReader(fr);
            while((line = reader.readLine())!= null){
                line2 += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        array = line2.toLowerCase().replace(" ","").split("");
        return array;
    }


    public void write(String fileName, String line) {
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {

                out.print(line);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

}