package framework;

public abstract class BaseTest extends BaseEntity {
//    public abstract void run();
//
//    @Test
//    protected void xTest() {
//        info(getLoc("loc.test.start"));
//        run();
//        info("loc.test.end");
//        logger.makeSeparator();
//    }

    protected String formatLogMsg(final String message) {
        return String.format("%1$s %2$s", this.getClass().getName(), message);
    }
}
