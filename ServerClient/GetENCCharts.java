package ServerClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.*;
import java.io.*;

public class GetENCCharts {
	public static void main(String[] args) throws IOException {

        String zeelandURL =   "https://vaarweginformatie.nl/fdd/main/wicket/resource/org.apache.wicket.Application/downloadfileResource?fileId=2770319516";
        String nederlandURL = "http://vaarweginformatie.nl/fdd/main/wicket/resource/org.apache.wicket.Application/downloadfileResource?fileId=2692343839";
        String chartDirectory = "../Charts";
        
        getUrl(zeelandURL, "Zeeland.zip");

        unzip("Zeeland.zip",chartDirectory);

        getUrl(nederlandURL, "Netherlands.zip");

        unzip("Netherlands.zip", chartDirectory);

        File currDir = new File(chartDirectory);

        unpack(currDir,currDir);
        System.out.println("All charts are done.");
        clean();
    }

    private static void unpack(File toDir, File currDir) {
        for (File file : currDir.listFiles()) {
            if (file.isDirectory()) {
                unpack(toDir, file);
                file.delete();
            } else {
                file.renameTo(new File(toDir, file.getName()));
            }
        }
    }
    
    private static void clean(){
        File files = new File("."); 
        for(File file : files.listFiles()){
            if(file.getName().endsWith(".zip")){
                file.delete();
            }
        }       
    }
    
    //source https://www.codejava.net/java-se/file-io/programmatically-extract-a-zip-file-using-java
    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if(entry.getName().endsWith(".pdf") || entry.getName().endsWith(".TXT")){                                   //Filter out .pdf and .txt files and continue to next entry.
                entry = zipIn.getNextEntry();
                continue;
            }
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        final int BUFFER_SIZE = 4096;
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
	public static void getUrl(String url, String outputFile) {
		try {
			Files.copy(
                new URL(url).openStream(), Paths.get(outputFile));
		}
		catch(Exception e) {
            System.out.println(e);
            System.exit(420);
        }
		System.out.println("Charts where succesfully downloaded");
    }
}
