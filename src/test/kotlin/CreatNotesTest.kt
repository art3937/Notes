import exception.NotFoundException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertContains

class CreatNotesTest {
    private var output: ByteArrayOutputStream? = ByteArrayOutputStream()

    @Before
    fun clearBeforeTest() {
        CreatNotes.clear()
        System.setOut(output?.let { PrintStream(it) })
    }

    @Test
    fun add() {
        CreatNotes.add(Notes("Проверка связи"))
        assertEquals("Проверка связи", CreatNotes.getById(0)?.title ?: NotFoundException("Нет заметки") )
    }

    @Test
    fun get() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.get()
        assertTrue(output.toString().isNotEmpty())
    }

    @Test
    fun edit() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.edit(0,"Изменил","text")
        assertEquals("Изменил", CreatNotes.getById(0)?.title ?: NotFoundException("Нет заметки") )
    }

    @Test
    fun creatComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(0,"Comment")
        assertEquals("Comment", CreatNotes.getById(0)?.listComment?.get(0)?.text ?: NotFoundException("Нет Заметки") )
    }

    @Test
    fun editComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(0,"Comment")
        CreatNotes.editComment(1,"Изменил")
        assertEquals("Изменил", CreatNotes.getById(0)?.listComment?.get(0)?.text ?: NotFoundException("Нет Заметки") )

    }

    @Test
    fun getById() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.getById(0)?.text?.let { assertTrue(it.isNotEmpty()) }
    }

    @Test
    fun getComments() {
        CreatNotes.add(Notes("Проверка связи"))
       CreatNotes.creatComment(0,"Comment")
        val comment = CreatNotes.getComments(0)
        comment?.let { assertTrue(it.isNotEmpty()) }
    }

    @Test
    fun deleteComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(0,"Comment")
        assertEquals(1,CreatNotes.deleteComment(1))
    }

    @Test
    fun restoreComment() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(0,"Comment")
        assertEquals(1,CreatNotes.restoreComment(1))
    }

    @Test(expected = NotFoundException::class)
    fun shouldThrow() {
        CreatNotes.add(Notes("Проверка связи"))
        CreatNotes.creatComment(1,"Comment")
    }

    @After
    fun cleanUpStreams() {
        System.setOut(null)
    }
}