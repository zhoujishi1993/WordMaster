package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TXTIO
 * @author fxiangyi@gmail.com
 * @version 2.0
 */

public class TXTIO implements IO {

	private final static String fileName = "dictionary.txt";
	
	@Override
	public List<String> readInLexicon() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> readInLexicon(String username) {
		File dir = new File(username);
		if(!dir.isDirectory())
			return null;
		
		List<String> ret = new ArrayList<String>();
		File[] file = dir.listFiles();
		for(File f: file) {
			ret.add(f.getName());
		}
		return ret;
	}

	@Override
	public void newUserLexicon(String username) {
		// TODO Auto-generated method stub
		
		char check = '#';
		char c;
		
		File dir = new File(username);
		if(dir.exists())
			return;
		
		dir.mkdir();
		
		
		File target = new File(username+"/"+"a");
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while((line=reader.readLine())!=null) {
				if((c=line.charAt(0))!=check) {
					if(writer!=null)
						writer.close();
					File file = new File(username+"/"+c);
					writer = new BufferedWriter(new FileWriter(file));
					check = c;
					writer.write(line+" false false false"+"\r\n");
				} else {
					writer.write(line+" false false false"+"\r\n");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Word> readInWords(String username) {
		return null;
		// TODO Auto-generated method stub
	}
	
	public List<Word> readInWords(String username, String lexiconName) {
		// TODO Auto-generated method stub
		String str[];
		
		List<Word> ret = new ArrayList<Word>();
		File file = new File(username+"/"+lexiconName);
		if(file.exists() && file.isFile()) {
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while((line=reader.readLine())!=null) {
					str = line.split("[' ']+");
					ret.add(new Word(str[0], str[1], s2b(str[2]), s2b(str[3]), s2b(str[4])));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	static boolean s2b(String str) {
		if(str.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public void writeLexicon(MyLexicon lexicon, String username) {
		// TODO Auto-generated method stub
		File file = new File(username+"/"+lexicon.name);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File Create Failed..");
				return;
			}
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<Word> it = lexicon.words.iterator();
			while(it.hasNext()) {
				bw.write(it.next().toString()+"\r\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File Writer Open Fail");
			return;
		}
	}

	public static void main(String args[]) {
		TXTIO textio = new TXTIO();
		textio.newUserLexicon("default");
		List<Word> words = new ArrayList<Word>();
		textio.readInLexicon("default");
	}
}
