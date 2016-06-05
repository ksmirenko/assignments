package patterns.composite

/* Example of the usage of composite pattern
   in file system
   created by Guzel Garifullina
*/
import java.util.*
/*
   Composite lets clients treat individual objects and
   compositions of objects uniformly.(from Design Patterns by E. Gamma, R. Helm, R. Johnson,J. Vlissides)
 */

interface Data{
    fun doubleClick()
}
//Primitive class
class File() : Data {
    private var name : String = "Untitled"
    fun getName() : String {
        return name
    }
    fun setName(name : String) {
        this.name = name
    }
    override fun doubleClick() {
        println("${this.getName()} file is Opened in a Program ")
    }
}

//Composite Class
class Folder() : Data {
    private var name = "Untitled"
    fun getName(): String {
        return name
    }
    fun setName(name : String) {
        this.name = name
    }
    private val folder : ArrayList<Data> = ArrayList()

    override fun doubleClick() {
        println("${this.getName()} folder is Opened")
        for(data in folder) {
            data.doubleClick()
        }
    }
    fun add(data : Data) {
        folder.add(data)
    }

    fun remove(data : Data) {
        folder.remove(data)
    }
}

//Client Program
fun main(args: Array<String>) {
    val f1 = Folder()
    f1.setName("Folder1")

    val f2 = Folder()
    f2.setName("Folder2")

    val f3 = Folder()
    f3.setName("Folder3")

    val file1 = File()
    file1.setName("File1")

    val file2 = File()
    file2.setName("File2")

    val  file3 = File()
    file3.setName("File3")

    val file4 = File()
    file4.setName("File4")

    f1.add(file1)
    f2.add(file2)

    f3.add(f2)
    f3.add(file3)
    f3.add(file4)

    f1.doubleClick()
    println()
    f2.doubleClick()
    println()
    f3.doubleClick()
}
