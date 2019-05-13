package framework.utils;

import framework.BaseEntity;
import framework.User;
import framework.utils.SqlManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class UserListGenerator extends BaseEntity{
    private static final String DATA_PATH = "src/main/resources/";

    public static List<User> getUserListBySQL(List<User> users) {
        String field_1 = "email";
        String field_2 = "password";
        String table = "users";
        try {
            for (int i = 1; i <= SqlManager.getSizeTable(field_1, table); i++) {
                SqlManager.selectUser(i, field_1, field_2, table);
                users.add(new User(SqlManager.getField_1(), SqlManager.getField_2()));
            }
        } catch (SQLException e) {
            logger.error("loc.err.db.no.rs");
        }
        return users;
    }

    public static List<User> getUserListByXML(List<User> users) {
        String userName, password;

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = docFactory.newDocumentBuilder();
            document = builder.parse(new File(DATA_PATH + "datastorage/users.xml"));
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

        } catch (ParserConfigurationException e) {
            logger.error(logger.getLogLoc("loc.err.parse.xml.doc"));
        } catch (SAXException e) {
            logger.error(logger.getLogLoc("loc.err.sax.xml"));
        } catch (IOException e) {
            logger.error(logger.getLogLoc("loc.err.xml"));
        }
        return users;
    }

    public static List<User> getUserListByCSV(List<User> users) {
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(DATA_PATH + "datastorage/users.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                users.add(new User(data[0], data[1]));
            }
            br.close();
        } catch (FileNotFoundException e) {
            logger.error(logger.getLogLoc("loc.err.read.csv"));
        } catch (IOException e) {
            logger.error(logger.getLogLoc("loc.err.csv"));
        }
        return users;
    }
}
