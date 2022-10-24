package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class T02DOMParsingTest {

	public void parse() throws ParserConfigurationException, SAXException, IOException {
		
		// XML문서를 생성하기 위한 DocumentBuilder 객체 생성
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		// Document 객체 생성
		// 이미 만들어진 Document 객체를
		// 이미 만들어진 객체를 내가 만든 객체에 넣는 작업
		Document document = builder.parse(new File("./src/new_book.xml"));
		
		// DOM Document 객체로부터 루트엘리먼트 객체 가져오기
		Element root = document.getDocumentElement();
		System.out.println("루트엘리먼트 태그명 : " + root.getTagName());
		
		// 하위 엘리먼트 접근방법1 : getElementsByTagName() 이용 -> 이 방법이 쉽고 편하다.
		// 여러개의 태그 결과값이 나올 수 있기 떄문에 NodeList에 담았다.
		NodeList bookNodeList = root.getElementsByTagName("book");
		// index값으로 넣으면 Node로 리턴된다.
		Node firstBookNode = bookNodeList.item(0); // 첫번째 항목
		// Node는 xml 파일의 최상위 객체다. - Object와 비슷하다.
		Element firstBookElement = (Element) firstBookNode;
		
		// 속성 접근방법1 : 엘리먼트 객체의 getAttribute() 이용
		System.out.println("엘리먼트 객체의 getAttribute() 이용 => " 
				+ firstBookElement.getAttribute("isbn"));
		
		// 속성 접근방법2 : 노드 객체의 getAttributes() 이용
		NamedNodeMap nodeMap = firstBookNode.getAttributes();
		System.out.println("노드 객체의 getAttributes() 이용 => "
				+ nodeMap.getNamedItem("isbn").getNodeValue());
		
		// 하위 엘리먼트 접근방법2 : getChildNodes() 이용
		NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
		// <title>는 0이여야하는데 1로 해야지 나온다.
		// 모든게 Node이기 때문에 공백, 엔터, 주석 등등 다 Node로 인식하기 때문에
		// <title>전에 엔터 또한 Node로 인식하기 때문이다.
		Node titleNode = firstBookChildNodeList.item(1);
		Element titleElement = (Element) titleNode;
		System.out.println("titleElement.getTagName() =>" 
				+ titleElement.getTagName());
		System.out.println("titleElement.getTextContent() =>" 
				+ titleElement.getTextContent());
		
		// 전체 출력하기
		System.out.println("-------------------------------------------------------");
		System.out.printf("%8s %8s %15s %10s %8s \n" 
				,"ISBN", "분류", "제목", "저자", "가격");
		System.out.println("-------------------------------------------------------");
		for(int i=0; i<bookNodeList.getLength(); i++) {
			Element element = (Element) bookNodeList.item(i);
			String isbn = element.getAttribute("isbn");
			String kind = element.getAttribute("kind");
			// 하나밖에 없겠지만 리턴 타입은 List다
			String title = element.getElementsByTagName("title").item(0).getTextContent();
			String author = element.getElementsByTagName("author").item(0).getTextContent();
			String price = element.getElementsByTagName("price").item(0).getTextContent();
			System.out.printf("%8s %8s %15s %10s %8s \n", isbn, kind, title, author, price);
		}
		
	}

	public static void main(String[] args) throws Exception, SAXException, IOException {
		new T02DOMParsingTest().parse();
	}
	
}
