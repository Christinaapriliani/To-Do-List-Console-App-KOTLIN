import java.io.File

class TaskManager {
    private val tasks = mutableListOf<String>()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        File("tasks.txt").forEachLine { tasks.add(it) }
    }

    private fun saveTasks() {
        File("tasks.txt").writeText(tasks.joinToString("\n"))
    }

    fun addTask(task: String) {
        tasks.add(task)
        saveTasks()
    }

    fun deleteTask(taskNumber: Int) {
        if (taskNumber >= 1 && taskNumber <= tasks.size) {
            tasks.removeAt(taskNumber - 1)
            saveTasks()
        }
    }

    fun displayTasks() {
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. $task")
        }
    }

    fun start() {
        while (true) {
            println("\nTodo List App\n")
            displayTasks()
            print("\nWhat would you like to do? (add/delete/quit): ")
            val action = readLine()?.toLowerCase()

            when (action) {
                "add" -> {
                    print("Enter a new task: ")
                    val newTask = readLine() ?: ""
                    addTask(newTask)
                }
                "delete" -> {
                    print("Enter the task number to delete: ")
                    val taskNumber = readLine()?.toIntOrNull() ?: 0
                    deleteTask(taskNumber)
                }
                "quit" -> break
                else -> println("Invalid action. Please choose add, delete, or quit.")
            }
        }
    }
}

fun main() {
    val taskManager = TaskManager()
    taskManager.start()
}
