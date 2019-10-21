package MyChat;

import org.apache.log4j.Logger;

public class MyMain {
    private static final Logger logger = Logger.getLogger(MyMain.class);

    public static void main(String[] args) {

        for(int i = 0; i < 3; i++) {
            try {
                logger.info(" Результат: " + divide(i));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public static int divide(int x) {
        logger.debug("Метод divide; делим число 2 / " + x);
        if(x == 0) {
            logger.warn(" Переменная x = 0; получили эксепшн");
        }
        return 2 / x;
    }
}
