package org.mycompany.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Test;

public class UploadFileUtilsTest {

	final String uploadPath = "src/main/webapp/WEB-INF/uploadStore";
	final String originalName = "uploadFileUtilTestFile";
	final byte[] fileData = new String("afsefaeae").getBytes();
	
	@After
	public void removeFiles() {
		deleteTestFiles(uploadPath);
	}


	@Test
	public void uploadFileTest() {
		try {
			String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, originalName, fileData);
			File file = new File(uploadPath + uploadedFileName);
			assertTrue(file.exists());
			System.out.println("path: " + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
private void deleteTestFiles(String path) {
		
		File file = new File(path);
		File[] tempFile = file.listFiles();
		
		if (tempFile.length > 0) {
			for (int i = 0; i < tempFile.length; i++) {
				if (!tempFile[i].isFile()) {
					deleteTestFiles(tempFile[i].getPath());
				}
				else {
					if (tempFile[i].getName().endsWith(originalName)) {
						tempFile[i].delete();
					}
				}
			}
		}
		
	}

}
