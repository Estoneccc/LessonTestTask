package example.note;

import example.note.NoteLogic;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Класс для тестов по работе с заметками
 */
public class TestNoteLogic extends TestCase {

    /**
     * Тест для проверки добавления
     */
    @Test
    public void testAdd() {
        NoteLogic noteLogic = new NoteLogic();
        String result = noteLogic.handleMessage("/add note1");
        Assert.assertEquals("Note added!", result);
        Assert.assertEquals("Your notes:\n" + "1. note1", noteLogic.handleMessage("/notes"));
    }

    /**
     * Тест для проверки вывода записей
     */
    @Test
    public void testNotes() {
        NoteLogic noteLogic = new NoteLogic();
        noteLogic.handleMessage("/add note1");
        String result = noteLogic.handleMessage("/notes");
        Assert.assertEquals("Your notes:\n" + "1. note1", result);
    }

    /**
     * Тест для проверки изменения записи
     */
    @Test
    public void testEdit() {
        NoteLogic noteLogic = new NoteLogic();
        noteLogic.handleMessage("/add note1");
        String result = noteLogic.handleMessage("/edit 1 New note");
        Assert.assertEquals("Note edited!", result);
        Assert.assertEquals("Your notes:\n" + "1. New note", noteLogic.handleMessage("/notes"));
    }

    @Test
    public void testDel() {
        NoteLogic noteLogic = new NoteLogic();
        noteLogic.handleMessage("/add note1");
        String result = noteLogic.handleMessage("/del 1");
        Assert.assertEquals("Note deleted!", result);
        Assert.assertEquals("Your notes:\n", noteLogic.handleMessage("/notes"));
    }
}
