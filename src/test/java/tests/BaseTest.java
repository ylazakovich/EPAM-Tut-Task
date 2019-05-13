package tests;

import framework.BaseEntity;
import framework.DataFactory;
import framework.User;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class BaseTest extends BaseEntity {
    protected User sender, recipient;
    protected List<User> userList = DataFactory.getUserList();
    protected String subject = "Subject Message";
    protected String message = "Body message";

    @BeforeClass
    public void setParam(){
        sender = new User(userList.get(0).getEmail(), userList.get(0).getPassword());
        recipient = new User(userList.get(0).getEmail(), userList.get(0).getPassword());
    }
}
