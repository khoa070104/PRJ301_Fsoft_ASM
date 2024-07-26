package com.se4f7.prj301.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class FileUtil {

	public static File getFolderUpload() {
		File folderUpload = new File("uploads");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}


	public static String[] saveFiles(Collection<Part> files) {
		List<String> fileNames = new ArrayList<>();
		for (Part file : files) {
			if (file.getSubmittedFileName() != null) {
				String fileName = saveFile(file);
				fileNames.add(fileName);
			}
		}
		return fileNames.toArray(new String[0]);
	}

	public static String saveFile(Part file) {
		try {
			String originFileName = file.getSubmittedFileName();
			String extension = FilenameUtils.getExtension(originFileName);
			String outputFileName = UUID.randomUUID().toString() + "." + extension;
			String outputFile = getFolderUpload().getAbsolutePath() + File.separator + outputFileName;
			file.write(outputFile);

			return outputFileName;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Boolean removeFiles(String[] fileNames) {
		for (String file : fileNames) {
			removeFile(file);
		}
		return true;
	}

	public static Boolean removeFile(String fileName) {
		File file = new File(getFolderUpload().getAbsolutePath() + File.separator + fileName);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}
}
