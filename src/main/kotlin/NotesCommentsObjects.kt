import exception.NotFoundException

class Notes(
    var title: String = "",
    var text: String = "text",
    val idNotes: Int = 0
)

data class Comment(
    val idComment: Int = 0,
    var text: String = "",
    var active: Boolean = true
)


object CreatNotes {
    private var listNotes: MutableMap<Notes, MutableList<Comment>> = mutableMapOf()
    private var actualList: MutableMap<Notes, List<Comment>> = mutableMapOf()
    private var count = 1
    private var countComment = 1

    fun add(notes: Notes) {
        listNotes[Notes(idNotes = count++, title = notes.title, text = notes.text)] = mutableListOf()
    }

    fun get(): MutableMap<Notes, List<Comment>> {
        listNotes.forEach { entry ->
            actualList[entry.key] = entry.value.filter { it.active }
        }
        return actualList
    }

    fun edit(idNotes: Int, title: String = "notes$idNotes", text: String): Int {
        listNotes.keys.forEach {
            if ((it).idNotes == idNotes) {
                it.text = text
                it.title = title
                return 1
            }
        }
        return 0
    }

    fun creatComment(idNotes: Int, text: String): String {
        listNotes.forEach {
            if (it.key.idNotes == idNotes) {
                it.value.add(Comment(countComment++, text))
                return "ид Комментария $countComment $idNotes заметки"
            }
        }
        return "Нет таковой заметки"
    }

    fun editComment(idComment: Int, text: String): Int {
        (listNotes).values.forEach { it ->
            it.forEach {
                if (it.idComment == idComment && it.active) {
                    it.text = text
                    return 1
                }
            }
        }
        return throw NotFoundException("Коммент не найден")
    }

    fun deleteComment(idComment: Int, active: Boolean = false): Int {
        listNotes.values.forEach { it ->
            it.forEach {
                if (it.idComment == idComment) {
                    it.active = active
                    return 1
                }
            }
        }
        return 0
    }

    fun restoreComment(idComment: Int) = deleteComment(idComment, true)

    fun getComments(idNotes: Int) = get().filterKeys { it.idNotes == idNotes }.values

    fun getById(idNotes: Int) = get().filter { it.key.idNotes == idNotes }

    fun clear() {
        listNotes = mutableMapOf()
        actualList = mutableMapOf()
        count = 1
        countComment = 1
    }
}
