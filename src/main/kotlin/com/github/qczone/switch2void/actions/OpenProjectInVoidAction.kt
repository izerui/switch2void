package com.github.qczone.switch2void.actions

import com.github.qczone.switch2void.settings.AppSettingsState
import com.github.qczone.switch2void.utils.WindowUtils
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.actionSystem.ActionUpdateThread

class OpenProjectInVoidAction : AnAction() {
    private val logger = Logger.getInstance(OpenProjectInVoidAction::class.java)

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return
        val projectPath = project.basePath ?: return

        val settings = AppSettingsState.getInstance()
        val voidPath = settings.voidPath

        val command = when {
            System.getProperty("os.name").lowercase().contains("mac") -> {
                arrayOf("open", "-a", "$voidPath", projectPath)
            }

            System.getProperty("os.name").lowercase().contains("windows") -> {
                arrayOf("cmd", "/c", "$voidPath", projectPath)
            }

            else -> {
                arrayOf(voidPath, projectPath)
            }
        }
        try {
            logger.info("Executing command: ${command.joinToString(" ")}")
            ProcessBuilder(*command).start()
        } catch (ex: Exception) {
            logger.error("Failed to execute void command: ${ex.message}", ex)
            com.intellij.openapi.ui.Messages.showErrorDialog(
                project,
                """
                ${ex.message}
                
                Please check:
                1. Void path is correctly configured in Settings > Tools > Switch2Void
                2. Void is properly installed on your system
                3. The configured path points to a valid Void executable
                """.trimIndent(),
                "Error"
            )
        }

        WindowUtils.activeWindow()
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
}
