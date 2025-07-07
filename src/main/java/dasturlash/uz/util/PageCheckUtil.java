package dasturlash.uz.util;

public class PageCheckUtil {
    public static int page(int page) {
        if (page > 0) return page - 1;
        return 0;
    }
}
