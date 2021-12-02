package automaton.constructor.controller.module.executor

import automaton.constructor.model.module.executor.Executor
import automaton.constructor.model.module.executor.SteppingStrategy
import automaton.constructor.model.module.problems
import tornadofx.*

class ExecutorController(val executor: Executor, val view: View) : Controller() {
    val automaton = executor.automaton

    fun toggleRun() {
        if (executor.started) {
            executor.stop()
        } else {
            if (!tryStart()) return
            executor.run()
            val executionResult = executor.status
            executor.stop()
            information("Execution result", "Input ${executionResult!!.displayName.toLowerCase()}")
        }
    }

    fun step(steppingStrategy: SteppingStrategy) {
        if (executor.started) executor.step(steppingStrategy)
        else tryStart()
    }

    private fun tryStart(): Boolean {
        if (automaton.problems.isNotEmpty()) {
            error("Execution failed", automaton.problems.joinToString("\n") { it.message })
            return false
        }
        executor.start()
        return true
    }
}
