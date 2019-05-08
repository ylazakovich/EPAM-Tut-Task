package framework;

public class BaseTest extends BaseEntity {

    // TODO may be for future base test

    protected String formatLogMsg(final String message) {
        return String.format("%1$s %2$s", this.getClass().getName(), message);
    }
}
