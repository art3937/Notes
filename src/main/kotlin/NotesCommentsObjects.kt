import exception.NotFoundException

data class Notes(
    val title: String = "",
    val text: String = "text",
    val listComment: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val idComment: Int = 0,
    var text: String = "",
    var active: Boolean = true
)


object CreatNotes {
    private var listNotes: MutableMap<Int, Notes> = mutableMapOf()
    private var count = 0

    fun add(notes: Notes) {
        listNotes[count++] = notes
    }

    fun get() {
        listNotes.forEach { println(it) }
    }

    fun edit(idNotes: Int, title: String = "notes$idNotes", text: String) {
        listNotes[idNotes] =
            listNotes[idNotes]?.copy(title = title, text = text) ?: throw NotFoundException("Нет заметки")
    }

    fun creatComment(idNotes: Int, text: String) {
        listNotes[idNotes]?.listComment?.add(Comment(text = text, idComment = count++))
            ?: throw NotFoundException("Нет заметки")
    }

    fun editComment(idComment: Int, text: String, status: Boolean = true): Int {
        var result = 1
        listNotes.forEach { it ->
            it.value.listComment.forEach {
                if (idComment == it.idComment) {
                    it.text = text
                    it.active = status
                } else result = 0
            }
        }
        return if (result == 1) result else throw NotFoundException("Комментарий не найден")
    }

    fun getById(idNotes: Int) = listNotes[idNotes]

    fun getComments(idNotes: Int) = listNotes[idNotes]?.listComment?.filter { it.active }

    fun deleteComment(idComment: Int, status: Boolean = false): Int {
        var result = 1
        listNotes.forEach { it ->
            it.value.listComment.forEach {
                if (idComment == it.idComment) {
                    it.active = status
                } else result = 0
            }
        }
        return if (result == 1) result else throw NotFoundException("Комментарий не найден")
    }

    fun restoreComment(idComment: Int) = deleteComment(idComment, true)

    fun clear() {
        listNotes = mutableMapOf()
        count = 0
    }
}
