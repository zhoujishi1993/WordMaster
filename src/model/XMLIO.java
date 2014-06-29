package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLIO implements IO {

	private final static String fileName = "dictionary.xml";
	
	@Override
	public List<String> readInLexicon() {
		// TODO Auto-generated method stub
		File dir = new File("default");
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
		File dir = new File(username);
		if(dir.exists())
			return;
		
		dir.mkdir();
		
		Element element = null;
		File f = new File(fileName);
		
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		
		String check = "#";
		BufferedWriter writer = null;
		
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			Document dt = db.parse(f);
			element = dt.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			for(int i=0; i<childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if("word".equals(node.getNodeName())) {
					Word word;
					String en = "", ch = "";
					NodeList detail = node.getChildNodes();
					for(int j=0; j<detail.getLength(); j++) {
						Node n = detail.item(j);
						if("english".equals(n.getNodeName()))
							en = n.getTextContent();
						if("chinese".equals(n.getNodeName()))
							ch = n.getTextContent();
					}
					word = new Word(en, ch, false, false, false);
					String type = type(ch);
					if(!type.equals(check)) {
						if(writer!=null)
							writer.close();
						File file = new File(username+"\\"+type);
						if(!file.exists()) {
							writer = new BufferedWriter(new FileWriter(file));
							writer.write("<?xml version='1.0' encoding='gbk'?>\r\n<words>\r\n");
						} else {
							writer = new BufferedWriter(new FileWriter(file, true));
						}
						//writer = new BufferedWriter(new FileWriter(file, true));
						check = type;
						writer.write(word.toString()+"\r\n");
					} else {
						writer.write(word.toString()+"\r\n");
					}
				}
			}
			List<String> list = readInLexicon();
			for(String str: list) {
				File file = new File(username+"\\"+str);
				writer = new BufferedWriter(new FileWriter(file, true));
				writer.write("</words>");
				writer.close();
			}
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String type(String ch) {
		String ret = "";
		if(ch.length()!=0 && ch.charAt(0)=='(') {
			for(char c: ch.toCharArray()) {
				ret += c;
				if(c==')')
					break;
			}
			return ret;
		}
		for(char c: ch.toCharArray()) {
			if(c=='.')
				break;
			ret += c;
		}
		return ret;
	}
	
	@Override
	public void writeLexicon(MyLexicon lexicon, String username) {
		// TODO Auto-generated method stub
		if(!(new File(username).exists()) || !(new File(username).isDirectory()))
			return;
		String fileName = username+"/"+lexicon.name;
		BufferedWriter bw;
		File file = new File(fileName);
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write("<?xml version='1.0' encoding='gbk'?>\r\n<words>\r\n");
			for(Word w: lexicon.words)
				bw.write(w.toString()+"\r\n");
			bw.write("</words>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Word> readInWords(String username, String lexicon) {
		// TODO Auto-generated method stub
		if(!(new File(username).exists()) || !(new File(username).isDirectory()))
			return null;
		
		List<Word> ret = new ArrayList<Word>();
		
		String fileName = username+"/"+lexicon;
		
		Element element = null;
		File f = new File(fileName);
		
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			Document dt = db.parse(f);
			element = dt.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			for(int i=0; i<childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if("word".equals(node.getNodeName())) {
					Word word;
					String en = "", ch = "";
					NodeList detail = node.getChildNodes();
					for(int j=0; j<detail.getLength(); j++) {
						Node n = detail.item(j);
						if("english".equals(n.getNodeName()))
							en = n.getTextContent();
						if("chinese".equals(n.getNodeName()))
							ch = n.getTextContent();
					}
					word = new Word(en, ch, false, false, false);
					ret.add(word);
				}
			}
			return ret;
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String args[]) {
		XMLIO io = new XMLIO();
		io.newUserLexicon("default");
		List<Word> words = io.readInWords("default", "n");
		for(Word w: words)
			System.out.println(w.getChinese()+ " "+ w.getEnglish());
	}*/

}
