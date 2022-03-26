package com.incloud.hcp.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Metodos utilitarios para el manejo de archivos Zip.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class ZipUtils {
	protected static final Log log = LogFactory.getLog(ZipUtils.class);

	public static String zipFile(String fileName, String zipFileName) {
		String[] fileNames = { fileName };
		return zipFiles(fileNames, zipFileName);
	}

	public static String zipFiles(String[] fileNames, String zipFileName) {
		log.debug("Zipping " + zipFileName);

		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipFileName));

			// Compress the files
			for (int i = 0; i < fileNames.length; i++) {
				FileInputStream in = new FileInputStream(fileNames[i]);

				// Add ZIP entry to output stream.
				out
						.putNextEntry(new ZipEntry(new File(fileNames[i])
								.getName()));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Complete the entry
				out.closeEntry();
				in.close();
			}
			// Complete the ZIP file
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zipFileName;
	}

    public static String zipFiles(File[] files, String zipFileName) {
        log.debug("Zipping " + zipFileName);

        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                    zipFileName));

            // Compress the files
            for (int i = 0; i < files.length; i++) {
                FileInputStream in = new FileInputStream(files[i]);

                // Add ZIP entry to output stream.
                out
                        .putNextEntry(new ZipEntry(files[i].getName()));

                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zipFileName;
    }

    public static File zipFiles(File[] files, File zipFile) {
        log.debug("Zipping " + zipFile.getName());

        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                    zipFile));

            // Compress the files
            for (int i = 0; i < files.length; i++) {
                FileInputStream in = new FileInputStream(files[i]);

                // Add ZIP entry to output stream.
                out
                        .putNextEntry(new ZipEntry(files[i].getName()));

                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zipFile;
    }
    
	public static String unzipFile(String zipFileName, String directory) {
		return unzipFiles(zipFileName, directory)[0];
	}

	public static String[] unzipFiles(String zipFilePath, String unzipDirectory) {
		log.debug("Unzipping " + zipFilePath);
		List fileNames = new ArrayList();
		try {
			ZipFile zipFile = new ZipFile(zipFilePath);
			Enumeration entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (!entry.isDirectory()) {
					String unzipPath = unzipDirectory + entry.getName();
					log.debug("Extracting file: " + unzipPath);
					copyInputStream(zipFile.getInputStream(entry),
							new BufferedOutputStream(new FileOutputStream(
									unzipPath)));
					fileNames.add(unzipPath);
				}
			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String[]) fileNames.toArray(new String[fileNames.size()]);
	}

	public static void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);

		in.close();
		out.close();
	}
	
	public static void zipDir(String directory, String zipName) throws IOException {
        // create a ZipOutputStream to zip the data to
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));
        String path = "";
        zipDir(directory, zos, path);
        // close the stream
        zos.close();
    }
	
	public static void zipDir(String directory, ZipOutputStream zos, String path) throws IOException {
        File zipDir = new File(directory);
        // get a listing of the directory content
        String[] dirList = zipDir.list();
        byte[] readBuffer = new byte[2156];
        int bytesIn = 0;
        // loop through dirList, and zip the files
        for (int i = 0; i < dirList.length; i++) {
            File f = new File(zipDir, dirList[i]);
            if (f.isDirectory()) {
                String filePath = f.getPath();
                zipDir(filePath, zos, path + f.getName() + "/");
                continue;
            }
            FileInputStream fis = new FileInputStream(f);
            try {
                ZipEntry anEntry = new ZipEntry(path + f.getName());
                zos.putNextEntry(anEntry);
                bytesIn = fis.read(readBuffer);
                while (bytesIn != -1) {
                    zos.write(readBuffer, 0, bytesIn);
                    bytesIn = fis.read(readBuffer);
                }
            } finally {
                fis.close();
            }
        }
    }	
}
