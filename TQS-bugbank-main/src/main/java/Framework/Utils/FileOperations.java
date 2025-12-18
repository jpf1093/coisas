package Framework.Utils;

import java.io.*;
import java.util.Properties;

public class FileOperations {

    private static final String DIR_PATH_PROPERTIES =
            System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "main" + File.separator + "resources" +
                    File.separator + "Properties" + File.separator;
   //pegar dados dos arquivos de propriedades
    public static Properties getProperties (String fileName) {

        InputStream inputStream = null;

        Properties prop = new Properties();

        try {

            File file = new File(DIR_PATH_PROPERTIES + fileName + ".properties");
            inputStream = new FileInputStream(file);
            prop.load(inputStream);
            return prop;
        }catch (Exception e) {

            System.out.println("N�o foi poss�vel carregar o aqrquivo de propriedade");
        }

        return prop;
    }
   // salvar dados em arquivos de propriedades
    public static void setProperties(String fileName, String propKey, String propValue) {

        Properties prop = getProperties(fileName);
        try {
            OutputStream outputStream = new FileOutputStream(DIR_PATH_PROPERTIES + fileName + ".properties");
            prop.setProperty(propKey, propValue);
            prop.store(outputStream, null);

        }catch (IOException e) {

            e.printStackTrace();
        }
    }
}
