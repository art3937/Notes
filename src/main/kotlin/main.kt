fun main(){
CreatNotes.add(Notes(title = "notes1", text = "моя первая заметка"))
    CreatNotes.add(Notes(title = "notes2", text = "вторая заметка"))
    CreatNotes.edit(1, text = "kdvlsk")
    println( CreatNotes.creatComment(2,"Comment for one note"))
   println( CreatNotes.creatComment(1,"Еще один коммент в заметку 1"))
    println( CreatNotes.creatComment(2,"коммент в заметку 2"))
    println( CreatNotes.editComment(1, text = "Изменил коммент"))
    CreatNotes.get().forEach{println(it)}
println()
    CreatNotes.deleteComment(1)
    CreatNotes.get().forEach{println(it)}
    println()
    CreatNotes.restoreComment(1)
    CreatNotes.get().forEach{println(it)}
    println()
    println(CreatNotes.getComments(1))
    println(CreatNotes.getById(1))
}