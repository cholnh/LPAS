package view.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private File file;
	private FileReader fr;
	private BufferedReader br;

	private FileWriter fw;
	private BufferedWriter bw;

	public String openFile(File s_file) {
		String result = null;
		try {
			this.file = s_file;
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			result = "";
			while ((line = br.readLine()) != null) {
				if (!line.endsWith("\n"))
					line += "\n";
				result += line;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public File saveFile(File s_file, String text) {
		try {
			File file = s_file;
			if(!(s_file.getName().endsWith(".kb"))){
				file = new File(s_file.getAbsolutePath() + ".kb");
			}
			fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw, 1024);
			text = text.replace("\n", "\r\n");
			bw.write(text);

			bw.flush();
			bw.close();
			
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
