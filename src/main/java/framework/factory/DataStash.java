package framework.factory;

import framework.factory.sql.SqlManager;
import framework.factory.users.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataStash {
    private static final String DATA_TYPE = PropertyReader.getProperty("dataType");
    private static final String DATA_PATH = "src/main/resources/";

    private static final String XML = "xml";
    private static final String SQL = "sql";
    private static final String CSV = "csv";

    private static final List<User> users = new ArrayList<>();

    private static String userName;
    private static String password;

    private static List<User> getUserList() {
        switch (DATA_TYPE.toLowerCase()) {
            case SQL:
                getListBySQL();
                break;
            case XML:
                break;
            case CSV:
                break;
        }

        return users;
    }

    public static List<User> getListBySQL() {
        for (int i = 1; i <= SqlManager.getSizeDB(); i++) {
            SqlManager.selectLine(i);
            users.add(new User(SqlManager.getEmail(), SqlManager.getPassword()));
        }
        return users;
    }

    public static List<User> getListByXML() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = docFactory.newDocumentBuilder();
            document = builder.parse(new File(DATA_PATH + "users.xml"));
            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    userName = eElement.getElementsByTagName("email").item(0).getTextContent();
                    password = eElement.getElementsByTagName("password").item(0).getTextContent();
                    users.add(new User(userName, password));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static List<User> getUsers() {
        return users;
    }

}
