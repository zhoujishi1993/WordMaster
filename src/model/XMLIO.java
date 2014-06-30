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
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
	public void newUserLexicon(String username) throws Exception {
		// TODO Auto-generated method stub
		File dir = new File(username);
		if(dir.exists()) {
                    return;
                }
                
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
				List<String> types = types(ch);
                                //String type = type(ch);
                                for(String type: types) {
                                    File file = new File(username+"/"+type); 
                                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
                                    writer.write(word.toString()+"\r\n");
                                    writer.close();
                                    writer = null;
                                }
                            }
			}
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
                        dir.delete();
			throw new Exception("单词文件不存在");
		} catch (SAXParseException e2) {
                        dir.delete();
                        throw new Exception("文件格式错误");
                } catch (IOException e3) {
                        dir.delete();
                        throw new Exception("文件IO错误");
                } catch (Exception e4) {
                        dir.delete();
                        throw new Exception("文件格式错误");
                }
	}

	private static List<String> types(String ch) throws Exception {
            String str = "";
            List<String> ret = new ArrayList<String>();
            if(ch.charAt(0)=='(') {
                for(char c: ch.toCharArray()) {
                    if(c==')') {
                        str += c;
                        ret.add(str);
                        break;
                    }
                    str += c;
                }
                return ret;
            }
            for(int i=0; i<ch.length(); i++) {
                if(ch.charAt(i)=='.'&&ch.charAt(i+1)!=',') {
                    ret.add(str);
                    break;
                }
                else if(ch.charAt(i)=='.'&&ch.charAt(i+1)==',') {
                    ret.add(str);
                    str = "";
                    i ++;
                }
                else {
                    str += ch.charAt(i);
                }
            }
            if(ret.size()==0) {
                throw new Exception("文件格式错误");
            }
            return ret;
        }
	
	@Override
	public void writeLexicon(MyLexicon lexicon, String username) {
		// TODO Auto-generated method stub
		if(!(new File(username).exists()) || !(new File(username).isDirectory()))
			return;
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

	@Override
	public List<Word> readInWords(String username, String lexiconName) {
		// TODO Auto-generated method stub
		String str[];
		
		List<Word> ret = new ArrayList<Word>();
		File file = new File(username+"/"+lexiconName);
		if(file.exists() && file.isFile()) {
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
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

}
