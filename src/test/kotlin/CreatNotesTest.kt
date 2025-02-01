import exception.NotFoundException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertContains

class CreatNotesTest {

    @Before
    fun clearBeforeTest() {
        CreatNotes.clear()
    }

    @Test
    fun add() {
        CreatNotes.add(Notes("Проверка связи"))
        assertTrue( CreatNotes.get().isNotEmpty())
    }

    @Test
    fun get() {
        CreatNotes.add(Notes("Проверка связи"))
        assertTrue( CreatNotes.get().isNotEmpty())
    }

    @Test
    fun edit() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.edit(1,"Изменил","Изменил")
        assertEquals("Изменил", CreatNotes.getById(1).keys.last().text)
    }

    @Test
    fun creatComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(1,"Comment")
        assertEquals("Comment",CreatNotes.get().values.last().last().text)
    }

    @Test
    fun editComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(1,"Comment")
        CreatNotes.editComment(1,"Изменил")
        assertEquals("Изменил",CreatNotes.get().values.last().last().text )

    }

    @Test
    fun getById() {
        CreatNotes.add(Notes("Проверка связи"))
        assertTrue(CreatNotes.getById(1).isNotEmpty())
    }

    @Test
    fun getComments() {
        CreatNotes.add(Notes("Проверка связи"))
       CreatNotes.creatComment(0,"Comment")
        val comment = CreatNotes.getComments(1)
        assertTrue(comment.isNotEmpty())
    }

    @Test
    fun deleteComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(1,"Comment")
        assertEquals(1,CreatNotes.deleteComment(1))
    }

    @Test
    fun restoreComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(1,"Comment")
        assertEquals(1,CreatNotes.restoreComment(1))
    }

    @Test(expected = NotFoundException::class)
    fun shouldThrow() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(1,"Comment")
        CreatNotes.editComment(0,"Comment")
    }

    @After
    fun cleanUpStreams() {
        System.setOut(null)
    }
}