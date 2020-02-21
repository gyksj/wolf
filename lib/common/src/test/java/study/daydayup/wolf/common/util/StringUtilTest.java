package study.daydayup.wolf.common.util;

import org.junit.Test;
import study.daydayup.wolf.common.util.lang.StringUtil;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/12/10 9:57 上午
 **/
public class StringUtilTest {

    @Test
    public void hasValue() {
        assertTrue("space hasValue", StringUtil.notEmpty(" "));
        assertFalse("null do not hasValue", StringUtil.notEmpty(null));
        assertFalse("empty string do not hasValue", StringUtil.notEmpty(""));
        assertTrue("common string hasValue", StringUtil.notEmpty("abc"));
    }

    @Test
    public void isTrue() {
    }

    @Test
    public void isFalse() {
    }

    @Test
    public void containsSpace() {
    }

    @Test
    public void ltrim() {
        String a = "abcdef";
        String prefix = "abc";

        a = StringUtil.ltrim(a, prefix);
        String expected = "def";

        assertEquals("StringUtil.ltrim fail", expected, a);
    }

    @Test
    public void rtrim() {
        String a = "abcdef";
        String suffix = "def";
        String expected = "abc";

        a = StringUtil.rtrim(a, suffix);
        assertEquals("StringUtil.rtrim fail", expected, a);

    }

    @Test
    public void lowerCamel() {
        String s = "trade_order_name";
        String expect = "tradeOrderName";

        assertEquals("StringUtil.lowerCamel fail", expect, StringUtil.lowerCamel(s));
        assertEquals("StringUtil.lowerCamel fail", expect, StringUtil.camel(s));

        assertEquals("StringUtil.lowerUnderscore fail", s, StringUtil.lowerUnderscore(expect));
        assertEquals("StringUtil.lowerUnderscore fail", s, StringUtil.lowerUnderscore(expect));
    }

    @Test
    public void lowerCamel_dot() {
        String s1 = "trade.order.name";
        String s2 = "trade_order_name";
        String expect = "tradeOrderName";

        assertEquals("StringUtil.lowerCamel fail", expect, StringUtil.lowerCamel(s1, "."));
        assertEquals("StringUtil.lowerCamel fail", expect, StringUtil.camel(s1, "."));

        assertEquals("StringUtil.lowerUnderscore fail", s2, StringUtil.lowerUnderscore(expect));
        assertEquals("StringUtil.lowerUnderscore fail", s2, StringUtil.lowerUnderscore(expect));
    }

    @Test
    public void lowerCamel_minus() {
        String s1 = "trade-order-name";
        String s2 = "trade_order_name";
        String expect = "tradeOrderName";

        assertEquals("StringUtil.lowerCamel fail", expect, StringUtil.lowerCamel(s1, "-"));
        assertEquals("StringUtil.lowerCamel fail", expect, StringUtil.camel(s1, "-"));

        assertEquals("StringUtil.lowerUnderscore fail", s2, StringUtil.lowerUnderscore(expect));
        assertEquals("StringUtil.lowerUnderscore fail", s2, StringUtil.lowerUnderscore(expect));
    }

}