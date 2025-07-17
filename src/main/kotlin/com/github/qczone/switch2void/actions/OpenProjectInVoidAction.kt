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

        // Check if Void path is configured
        if (voidPath.isBlank()) {
            com.intellij.openapi.ui.Messages.showErrorDialog(
                project,
                """
                Void editor path is not configured.
                
                Please:
                1. Go to Settings > Tools > Switch2Void
                2. Set the path to your preferred editor executable
                
                Examples:
                • For VSCode: /usr/local/bin/code
                • For Sublime Text: /usr/local/bin/subl
                • For custom editor: /path/to/your/editor
                """.trimIndent(),
                "Void Path Not Configured"
            )
            return
        }

        val command = when {
            System.getProperty("os.name").lowercase().contains("mac") -> {
                if (voidPath == "void" || voidPath.endsWith("Void.app")) {
                    // Use macOS open command for Void.app
                    arrayOf("open", "-a", "Void", projectPath)
                } else {
                    arrayOf(voidPath, projectPath)
                }
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
                2. The editor is properly installed on your system
                3. The configured path points to a valid executable
                
                Current configured path: "$voidPath"
                """.trimIndent(),
                "Error Launching Editor"
            )
        }

        WindowUtils.activeWindow()
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
}
