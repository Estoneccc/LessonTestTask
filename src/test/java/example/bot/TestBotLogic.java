package example.bot;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестов, которые проверяют логику BotLogic
 */
public class TestBotLogic {

    /**
     * Тест на команду /test
     */
    @Test
    public void testCommandTest() {
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);
        User user = new User(0L);


        botLogic.processCommand(user, "/test");
        Assert.assertEquals(State.TEST, user.getState());

        Assert.assertEquals("Вычислите степень: 10^2", fakeBot.getMessages().get(0));
        botLogic.processCommand(user, "99");
        Assert.assertEquals("Вы ошиблись, верный ответ: 100", fakeBot.getMessages().get(1));

        Assert.assertEquals("Сколько будет 2 + 2 * 2", fakeBot.getMessages().get(2));
        botLogic.processCommand(user, "6");
        Assert.assertEquals("Правильный ответ!", fakeBot.getMessages().get(3));

        Assert.assertEquals(1, user.getWrongAnswerQuestions().size());

        Assert.assertEquals("Вычислите степень: 10^2", user.getWrongAnswerQuestions().get(0).getText());

        Assert.assertEquals("Тест завершен", fakeBot.getMessages().get(4));
    }

    /**
     * Тест на команду /notify
     */
    @Test
    public void testCommandNotify() throws InterruptedException {
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);
        User user = new User(0L);

        botLogic.processCommand(user, "/notify");
        Assert.assertEquals(State.SET_NOTIFY_TEXT, user.getState());

        Assert.assertEquals("Введите текст напоминания", fakeBot.getMessages().get(0));
        botLogic.processCommand(user, "test");
        Assert.assertEquals("Через сколько секунд напомнить?", fakeBot.getMessages().get(1));
        Assert.assertEquals(State.SET_NOTIFY_DELAY, user.getState());

        botLogic.processCommand(user, "afefad");
        Assert.assertEquals("Пожалуйста, введите целое число", fakeBot.getMessages().get(2));
        Assert.assertEquals(State.SET_NOTIFY_DELAY, user.getState());

        botLogic.processCommand(user, "1");

        Assert.assertEquals("Напоминание установлено", fakeBot.getMessages().get(3));

        Thread.sleep(1100);
        Assert.assertEquals("Сработало напоминание: 'test'", fakeBot.getMessages().get(4));
    }

    /**
     * Тест на команду /repeat
     */
    @Test
    public void testCommandRepeat() {
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);
        User user = new User(0L);

        botLogic.processCommand(user, "/repeat");
        Assert.assertEquals("Нет вопросов для повторения", fakeBot.getMessages().get(0));

        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "wrongAnswer");
        botLogic.processCommand(user, "/repeat");

        Assert.assertEquals("Вычислите степень: 10^2", user.getCurrentWrongAnswerQuestion().get().getText());
        Assert.assertEquals(State.REPEAT, user.getState());

        botLogic.processCommand(user, "100");

        Assert.assertEquals(0, user.getWrongAnswerQuestions().size());
    }
}
