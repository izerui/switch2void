package com.github.qczone.switch2void.actions

import com.github.qczone.switch2void.settings.AppSettingsState
import com.github.qczone.switch2void.utils.WindowUtils
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.actionSystem.ActionUpdateThread

class OpenFileInVoidAction : AnAction() {
    private val logger = Logger.getInstance(OpenFileInVoidAction::class.java)

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return
        val virtualFile: VirtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        
        val editor: Editor? = e.getData(CommonDataKeys.EDITOR)
        
        val line = editor?.caretModel?.logicalPosition?.line?.plus(1) ?: 1
        val column = editor?.caretModel?.logicalPosition?.column?.plus(1) ?: 1
        
        val filePath = virtualFile.path
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
                    // Use Void's custom URL scheme with line and column
                    arrayOf("open", "void://file$filePath:$line:$column")
                } else {
                    arrayOf(voidPath, "--goto", "$filePath:$line:$column")
                }
            }
            System.getProperty("os.name").lowercase().contains("windows") -> {
                arrayOf("cmd", "/c", "$voidPath", "--goto", "$filePath:$line:$column")
            }
            else -> {
                arrayOf(voidPath, "--goto", "$filePath:$line:$column")
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
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        
        e.presentation.isEnabledAndVisible = project != null && 
                                           virtualFile != null && 
                                           !virtualFile.isDirectory
    }
}
