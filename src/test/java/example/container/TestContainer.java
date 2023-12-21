package example.container;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестов по работе с контейнером
 */
public class TestContainer {

    /**
     * Тест для проверки добавления предмета в контейнер
     */
    @Test
    public void testAdd() {
        Container container = new Container();
        Item item = new Item(123);
        Assert.assertTrue(container.add(item));
        Assert.assertEquals(1, container.size());
        Assert.assertTrue(container.contains(item));
    }

    /**
     * Тест для проверки удаления предмета из контейнера
     */
    @Test
    public void testRemove() {
        Container container = new Container();
        Item itemOne = new Item(1);
        Item itemTwo = new Item(2);
        container.add(itemOne);
        container.add(itemTwo);
        Assert.assertTrue(container.remove(itemTwo));
        Assert.assertEquals(1, container.size());
        Assert.assertFalse(container.contains(itemTwo));
    }
}