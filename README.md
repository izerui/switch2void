# Switch2Void

[中文文档](README_zh.md)

> 💡 Recommended to use with [Switch2IDEA](https://github.com/qczone/switch2idea) in Void


[![JetBrains Plugins](https://img.shields.io/jetbrains/plugin/v/26309-switch2void?label=JetBrains%20Marketplace&style=for-the-badge&logo=intellij-idea)](https://plugins.jetbrains.com/plugin/26309-switch2void)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/26309-switch2void?style=for-the-badge&logo=intellij-idea)](https://plugins.jetbrains.com/plugin/26309-switch2void)
[![License](https://img.shields.io/badge/license-MIT-blue.svg?style=for-the-badge)](LICENSE)

## 🔍 Introduction
A JetBrains IDE plugin that enhances development efficiency by enabling seamless switching between JetBrains IDE and Void

![Switch2Void Demo](images/switch-show.gif)

## 🌟 Features

- 🚀 Seamless Editor Switching
  - One-click switch between JetBrains IDE and Void
  - Automatically positions to the same void location (line and column)
  - Perfectly maintains editing context without interrupting workflow

- ⌨️ Convenient Shortcut Support
  - macOS:
    - `Option+Shift+P` - Open project in Void
    - `Option+Shift+O` - Open current file in Void
  - Windows:
    - `Alt+Shift+P` - Open project in Void
    - `Alt+Shift+O` - Open current file in Void

- 🔧 Multiple Access Methods
  - Keyboard shortcuts
  - Editor context menu
  - IDE tools menu

## 🛠️ Installation Guide

### Method 1: Install via JetBrains Marketplace
1. Open IDE → `Settings` → `Plugins` → `Marketplace`
2. Search for switch2void
3. Click `Install` to complete installation
4. Click `OK` to apply changes

### Method 2: Local Installation
1. Download the latest plugin package from [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/26309-switch2void)
2. IDE → `Settings` → `Plugins` → `⚙️`→ `Install Plugin from Disk...`
3. Select the downloaded plugin package
4. Click `OK` to apply changes


## 🚀 Usage Guide

### Basic Usage

#### Open Project
- Shortcuts:
  - macOS: `Option+Shift+P` 
  - Windows: `Alt+Shift+P`
- Context Menu: Right-click in project view → `Open Project In Void`
- Tools Menu: `Tools` → `Open Project In Void`

#### Open Current File
- Shortcuts:
  - macOS: `Option+Shift+O` 
  - Windows: `Alt+Shift+O`
- Context Menu: Right-click in editor → `Open File In Void`
- Tools Menu: `Tools` → `Open File In Void`

### Configuration
- In `Settings/Preferences` → `Tools` → `Switch2Void`:
  - Set Void executable path (default is "void")
  - Customize shortcuts through Keymap settings

### Requirements
- [Void](https://voideditor.com) installed
- Compatible with all JetBrains IDEs (version 2022.3 and above)

## 🧑‍💻 Developer Guide

### Build Project
```bash
# Clone repository
git clone https://github.com/izerui/switch2void.git

# Build plugin
cd switch2void
./gradlew buildPlugin  
# Plugin package will be generated in build/distributions/ directory
```

### Contributing
1. Fork this repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Submit a Pull Request

## 🙋 FAQ 

### 1. Why doesn't the shortcut/menu click switch to Void after installation?
Check if the correct Void executable path is configured in `Settings` → `Tools` → `Switch2Void`

### 2. Which IDEs are supported?
Supports all JetBrains IDEs, including: IntelliJ IDEA, PyCharm, WebStorm, GoLand, RustRover, Android Studio, etc.

### 3. Which versions are supported?
The plugin is developed based on JDK 17 and currently only supports JetBrains IDE version 2022.3 and above

### 4. How to modify plugin shortcuts?
Modify in `Settings` → `Keymap` → `Plugins` → `Switch2Void`

## 📄 License
This project is licensed under the [MIT License](LICENSE)


## 📮 Feedback
If you encounter any issues or have suggestions, please provide feedback through:
- [Submit GitHub Issue](https://github.com/qczone/switch2void/issues) 

## 🌟 Star History

[![Star History Chart](https://api.star-history.com/svg?repos=qczone/switch2void&type=Date)](https://star-history.com/#qczone/switch2void&Date)