fun main() {
    CreatNotes.add(Notes(title = "notes1", text = "моя первая заметка"))
    CreatNotes.add(Notes(title = "notes2", text = "вторая заметка"))
    CreatNotes.get()
    println()
    CreatNotes.edit(1, text = "bla bla")
    CreatNotes.creatComment(1, "первый комментарий")
    CreatNotes.editComment(2, "изменен")
    // println( CreatNotes.getComments(1))
    CreatNotes.get()
    // println(CreatNotes.deleteComment(2))
    println(CreatNotes.deleteComment(2))
    println(CreatNotes.restoreComment(2))
    println(CreatNotes.getComments(1))
//    println(CreatNotes.restoreComment(2))
//    println( CreatNotes.getComments(1))
}