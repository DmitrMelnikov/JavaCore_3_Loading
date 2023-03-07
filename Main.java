import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void openZip(String pathZip, String pathDir) {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathZip))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                // распаковка
                FileOutputStream fout = new FileOutputStream(pathDir + "//" + entry.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String filePath) {

        GameProgress gameProgress = null;
        // откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {// десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);

    }

    public static void main(String[] args) {

        File dir = new File("F://Games//savegames");

        if (dir.isDirectory()) {
            openZip(dir.getPath() + "//save.zip", dir.getPath());
            openProgress(dir.getPath() + "//save.dat");

        }


    }
}