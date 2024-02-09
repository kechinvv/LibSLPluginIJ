# LibSL Support Plugin

![Build](https://github.com/kechinvv/LibSLPluginIJ/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/23222-libsl-support.svg)](https://plugins.jetbrains.com/plugin/23222-libsl-support)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23222-libsl-support.svg)](https://plugins.jetbrains.com/plugin/23222-libsl-support)

## ToDo list
### Basic features
- [x] Basic highlighter
    - [ ] "import..." statement highlight
- [ ] Find Usages
    - [x] functions and procs
    - [x] types and automatons
    - [x] automaton scope variables
    - [x] actions
    - [ ] local and global variables
    - [ ] annotations
- [ ] Completion
- [ ] Formatter
### Unique features
- [ ] Taint configs generation
- [ ] Templates generation
- [ ] Mining specification
### Dev
- [ ] CI/CD
- [ ] Refactoring
- [ ] Null safety

# Description
Plugin for [LibSL](https://www.researchgate.net/publication/325074393_LibSL_Language_for_Specification_of_Software_Libraries) supporting.

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "LibSL Support"</kbd> >
  <kbd>Install</kbd>
  
- Manually:

  Download the [latest release](https://github.com/kechinvv/LibSLPluginIJ/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
