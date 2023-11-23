package example.container;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестов по работе с контейнером
 */
public class TestContainer extends TestCase {

    /**
     * Тест для проверки добавления предмета в контейнер
     */
    @Test
    public void testAdd() {
        Container container = new Container();
        Item item = new Item(123);
        Assert.assertEquals(true, container.add(item));
        Assert.assertEquals(1, container.size());
        Assert.assertEquals(true, container.contains(item));
        Assert.assertEquals(item, container.get(0));
    }

    @Test
    public void testRemove() {
        Container container = new Container();
        Item itemOne = new Item(1);
        Item itemTwo = new Item(2);
        Item itemThree = new Item(3);
        container.add(itemOne);
        container.add(itemTwo);
        container.add(itemThree);
        Assert.assertEquals(true, container.remove(itemTwo));
        Assert.assertEquals(2, container.size());
        Assert.assertEquals(false, container.contains(itemTwo));
    }
}
